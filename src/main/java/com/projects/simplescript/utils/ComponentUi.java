package com.projects.simplescript.utils;


import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ComponentUi {
    public static Optional<ButtonType> showAlert(AlertType type , String title, String msg) {
		Alert alert = new Alert(type, title+"\n"  + msg );
		return alert.showAndWait();
	}
}
