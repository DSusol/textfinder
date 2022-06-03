package ru.finplatforms.interview.textfinder.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Profile({"extensionBasedFileTypes", "default"})
public class ExtensionBasedFileTypeChecker implements FileTypeChecker {

    @Override
    public boolean isTextFile(Path file) {
        String txtFileRegex = "(.*)\\.txt$";
        return file.getFileName().toString().matches(txtFileRegex);
    }
}