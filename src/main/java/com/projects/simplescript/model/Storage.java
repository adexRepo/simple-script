package com.projects.simplescript.model;

import java.util.Map;

import com.projects.simplescript.utils.Validator;

import lombok.Data;

@Data
public class Storage {
    private static Storage instance;
    private Map<String,Object> config;

    private Storage() {}

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }

        return instance;
    }

    public static void checkConfig(){
        String key = (String) instance.getConfig().get("key");
        String val = (String) instance.getConfig().get("val");
        String fin = (String) instance.getConfig().get("fin");
        String app = (String) instance.getConfig().get("app");
        String title = (String) instance.getConfig().get("title");

        boolean javaFXConfig = Validator.checkCredential(key, val, fin);
        boolean springConfig = Validator.checkAppType(title, key, app);
        if (!(javaFXConfig && springConfig)) {
            throw new RuntimeException("Application FAILED To Run");
        }
    }


    /* ---------------------------------- BASIC --------------------------------- */


}
