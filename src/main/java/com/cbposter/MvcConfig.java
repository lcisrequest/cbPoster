package com.cbposter;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


/**
 * @Auther: lc
 * @Date: 2020/7/9 10:30
 */
@Configuration
@ConfigurationProperties(prefix = "mvc")
public class MvcConfig extends WebMvcConfigurationSupport {

    private String resourcePath;

    private String webUploadPath;

//    private String ctimagePath;

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getWebUploadPath() {
        return webUploadPath;
    }

    public void setWebUploadPath(String webUploadPath) {
        this.webUploadPath = webUploadPath;
    }

//    public String getCtimagePath() {
//        return ctimagePath;
//    }
//
//    public void setCtimagePath(String ctimagePath) {
//        this.ctimagePath = ctimagePath;
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("login");
        registry.addViewController("/debug").setViewName("debug");
        registry.addViewController("/").setViewName("index");
        */
        registry.addViewController("/debug").setViewName("debug");
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (resourcePath != null && resourcePath.length() > 0) {
            registry.addResourceHandler("/**").addResourceLocations(String.format("file:%s", resourcePath));//.setCachePeriod(3600 * 24);
        }

        registry.addResourceHandler("/debug/**").addResourceLocations("classpath:/static/debug/").setCachePeriod(3600 * 24);

        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

        registry.addResourceHandler("/upload/**").addResourceLocations(String.format("file:%s", webUploadPath));

        super.addResourceHandlers(registry);
    }

}