package com.projects.simplescript;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.projects.simplescript.controller.LoginController;
import com.projects.simplescript.model.Storage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

@PropertySource("classpath:application.yml")
public class FxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(SimpleScriptApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        String key = applicationContext.getEnvironment().getProperty("config.key");
        String val = applicationContext.getEnvironment().getProperty("config.value");
        String fin = applicationContext.getEnvironment().getProperty("config.fin");
        String app = applicationContext.getEnvironment().getProperty("config.app");
        String title = applicationContext.getEnvironment().getProperty("javafx.title");
        Storage storage = Storage.getInstance();
        Map<String,Object> config = new HashMap<>();
        config.put("key", key);
        config.put("val", val);
        config.put("fin", fin);
        config.put("app", app);
        config.put("title", title);
        storage.setConfig(config);
        Storage.checkConfig();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

}
