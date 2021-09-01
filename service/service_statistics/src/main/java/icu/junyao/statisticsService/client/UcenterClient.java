package icu.junyao.statisticsService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wu
 */
@Component
@FeignClient("service-uCenter")
public interface UcenterClient {


    /**
     *     查询某一天注册人数
     * @param day
     * @return
     */
    @GetMapping("/eduUserCenter/member/countRegister/{day}")
    R countRegister(@PathVariable("day") String day);
}
