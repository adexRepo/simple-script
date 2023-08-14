package com.projects.simplescript;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class SimpleScriptApplication {


	public static void main(String[] args) throws Exception {
		Application.launch(FxApplication.class, args);
	}


}
