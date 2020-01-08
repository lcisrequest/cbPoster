package com.cbposter.service;

import com.cbposter.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:15
 */
@Service
public class PictureSynthesis {
    @Autowired
    ImgMergeWrapperTest imgMergeWrapperTest;

    public void test() throws IOException {
        imgMergeWrapperTest.testTemplate();
    }


}
