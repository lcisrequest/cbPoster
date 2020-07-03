package com.cbposter;

import com.cbposter.fxmlview.LoginFxmlView;
import com.cbposter.splash.DemoSplash;
import com.cbposter.utils.BaseRepositoryFactoryBean;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableJpaRepositories(
        basePackages = {"com.cbposter"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
@EnableJpaAuditing
public class CbposterApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(CbposterApplication.class, LoginFxmlView.class, new DemoSplash(), args);
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        stage.setTitle("这里是标题");
        stage.setWidth(400);
        stage.setHeight(300);
    }

}
