package ru.finplatforms.interview.textfinder.services;

import java.nio.file.Path;
import java.util.List;

public interface FileService {

    List<Path> getSortedFileListFromDirectory(String rootDirectory);

    void createSummaryTxtFileFromList(String pathName, List<Path> sourceFiles);

    String getSummaryFileContents(String pathName);
}
