package com.cbposter.web;

import com.cbposter.utils.Result;
import com.cbposter.service.PictureSynthesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:15
 */
@RestController
@RequestMapping("/cbPoster")
public class TestController {

    @Autowired
    PictureSynthesis pictureSynthesis;

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public Result test() throws IOException {
        pictureSynthesis.test();
        return Result.ok();
    }


}
