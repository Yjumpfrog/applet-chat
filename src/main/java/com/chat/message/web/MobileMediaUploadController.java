package com.chat.message.web;

import com.chat.message.config.OSSFactory;
import com.chat.message.util.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/mobile/media")
public class MobileMediaUploadController {


    /**
     * @param multipartFile 文件传输对象
     * @param requestId     请求ID，为后续反写业务ID时使用
     * @param memberId      会员ID
     * @param sort          排序值
     * @return
     */
    @RequestMapping(value = "/upload")
    public R upload(@RequestParam("file") MultipartFile multipartFile,
                    @RequestParam(required = false, value = "requestId") String requestId,
                    @RequestParam("memberId") Long memberId,
                    @RequestParam(required = false, value = "sort") Integer sort
    ) {
        //上传文件
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        try {
            String url = OSSFactory.build().uploadSuffix(multipartFile.getBytes(), suffix);
            return R.ok().put("url", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error();
    }
}
