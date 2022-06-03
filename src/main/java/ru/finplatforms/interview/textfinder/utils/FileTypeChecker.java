package ru.finplatforms.interview.textfinder.utils;

import java.nio.file.Path;

public interface FileTypeChecker {

    boolean isTextFile(Path file);
}
