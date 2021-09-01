package icu.junyao.ossService.controller;

import icu.junyao.ossService.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wu
 */
@RestController
@RequestMapping("/eduOss/fileOss")
@RequiredArgsConstructor
public class OssController {

    private final OssService ossService;


    /**
     * 上传头像的方法
     *
     * @param file
     * @return
     */
    @PostMapping
    @RequestMapping("/teachAvatar")
    public R uploadOssFileTeachAvatar(@RequestPart("file") MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }

    /**
     * 上传头像的方法
     *
     * @param file
     * @return
     */
    @PostMapping
    @RequestMapping("/courseCover")
    public R uploadOssFileCourseCover(@RequestPart("file") MultipartFile file) {
        String url = ossService.uploadFileCover(file);
        return R.ok().data("url", url);
    }
}
