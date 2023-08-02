package com.projects.simplescript.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.projects.simplescript.model.Simple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Converter {

    public static ObservableList<String> convertListToObservableListSimple(List<Simple> list) {

        ObservableList<String> observableList = list.stream()
                .map(Simple::getName)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        return observableList;
    }
}
