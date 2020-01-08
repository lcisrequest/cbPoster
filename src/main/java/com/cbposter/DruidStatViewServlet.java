package com.cbposter;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:27
 */
import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * druid数据源状态监控.
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {
        // IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name = "allow", value = ""),
        // IP黑名单 (存在共同时，deny优先于allow)
        @WebInitParam(name = "deny", value = ""),
        // 用户名
        @WebInitParam(name = "loginUsername", value = "rtpacs"),
        // 密码
        @WebInitParam(name = "loginPassword", value = "81977100"),
        // 禁用HTML页面上的“Reset All”功能
        @WebInitParam(name = "resetEnable", value = "false") })
@SuppressWarnings("serial")
public class DruidStatViewServlet extends StatViewServlet {

}