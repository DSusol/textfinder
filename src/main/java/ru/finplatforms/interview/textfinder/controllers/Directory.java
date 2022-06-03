package ru.finplatforms.interview.textfinder.controllers;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Directory {

    @Getter
    private String pathName;

    public void setPathName(String pathName) {
        if (pathName.lastIndexOf(File.separator) != (pathName.length() - 1)) {
            pathName += File.separator;
        }
        this.pathName = pathName;
    }
}
