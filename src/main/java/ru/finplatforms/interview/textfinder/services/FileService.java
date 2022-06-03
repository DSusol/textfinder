package ru.finplatforms.interview.textfinder.services;

import java.nio.file.Path;
import java.util.List;

public interface FileService {

    List<Path> getSortedFileListFromDirectory(String rootDirectory);

    void createSingleTxtFileFromList(String pathName, List<Path> sourceFiles);

    String getSummaryFileContents(String pathName);
}
