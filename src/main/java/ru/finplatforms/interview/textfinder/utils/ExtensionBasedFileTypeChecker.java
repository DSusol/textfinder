package ru.finplatforms.interview.textfinder.utils;

import java.nio.file.Path;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"extensionBasedFileTypes", "default"})
class ExtensionBasedFileTypeChecker implements FileTypeChecker {

    @Override
    public boolean isTextFile(Path file) {
        String txtFileRegex = "(.*)\\.txt$";
        return file.getFileName().toString().matches(txtFileRegex);
    }
}