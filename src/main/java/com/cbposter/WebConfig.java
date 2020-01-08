package com.cbposter;

import com.cbposter.utils.exception.RException;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.http.port}")
    private int serverHttpPort;

    @Value("${server.ssl.lock}")
    private String sslLock;

    /**
     * 解决跨域问题
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "accessToken")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);
    }

    /**
     * sslLock:
     * (正式环境)true -> 开启ssl锁，http端口访问会定向到https端口访问
     * (开发环境)false -> 关闭ssl锁，http访问与https访问同时支持
     *
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        if ("true".equals(sslLock)) {
            //http -> https跳转
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
                @Override
                protected void postProcessContext(Context context) {
                    SecurityConstraint constraint = new SecurityConstraint();
                    constraint.setUserConstraint("CONFIDENTIAL");
                    SecurityCollection collection = new SecurityCollection();
                    collection.addPattern("/*");
                    constraint.addCollection(collection);
                    context.addConstraint(constraint);
                }
            };
            tomcat.addAdditionalTomcatConnectors(createHttpConnector());
            return tomcat;
        } else if ("false".equals(sslLock)) {
            // http、https分离支持
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
            tomcat.addAdditionalTomcatConnectors(createHttpConnector());
            return tomcat;
        } else {
            throw new RException("启动异常，ssl开关配置有误");
        }




    }


    /**
     * 创建http连接
     *
     * @return
     */
    private Connector createHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(serverHttpPort);
        connector.setSecure(false);
        return connector;
    }

    /**
     * 创建http连接，并将http请求定向至https
     *
     * @return
     */
    private Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //需要重定向的http端口
        connector.setPort(serverHttpPort);
        connector.setSecure(false);
        //设置重定向到https端口
        connector.setRedirectPort(serverPort);
        return connector;
    }
}
