package com.cbposter;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:25
 */


import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器.
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
        // 忽略资源
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") })
public class DruidStatFilter extends WebStatFilter {
}
