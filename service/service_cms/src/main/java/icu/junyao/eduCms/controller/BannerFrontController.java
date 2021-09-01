package icu.junyao.eduCms.controller;


import icu.junyao.eduCms.entity.CrmBanner;
import icu.junyao.eduCms.service.CrmBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台banner显示
 * </p>
 *
 * @author wu
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/eduCms/bannerFront")
@RequiredArgsConstructor
public class BannerFrontController {

    private final CrmBannerService bannerService;



    /**
     * 查询所有banner
     * @return
     */
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}

