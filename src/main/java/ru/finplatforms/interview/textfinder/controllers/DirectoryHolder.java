package ru.finplatforms.interview.textfinder.controllers;

import java.io.File;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class DirectoryHolder {

    @Getter
    private String pathName;

    public void setPathName(String pathName) {
        if (pathName.lastIndexOf(File.separator) != (pathName.length() - 1)) {
            pathName += File.separator;
        }
        this.pathName = pathName;
    }
}
