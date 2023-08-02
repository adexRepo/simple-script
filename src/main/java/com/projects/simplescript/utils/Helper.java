package com.projects.simplescript.utils;


import java.util.List;
import java.util.Optional;

import com.projects.simplescript.model.Simple;

public class Helper {
    public static Integer getIdFromSimpleList(List<Simple> list, String name){
        Optional<Integer> optionalId = list.stream()
            .filter(simple -> simple.getName().equals(name))
            .map(Simple::getId)
            .findFirst();

        return optionalId.orElse(-1);
    }
}
