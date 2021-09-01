package icu.junyao.msmService.controller;

import icu.junyao.msmService.service.MsmService;
import icu.junyao.msmService.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wu
 */
@RestController
@RequestMapping("/eduMsm/msm")
@RequiredArgsConstructor
public class MsmController {

    private final MsmService msmService;

    private final RedisTemplate<String,String> redisTemplate;



    /**
     * 发送短信的方法
     * @param phone
     * @return
     */
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) throws Exception {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}
