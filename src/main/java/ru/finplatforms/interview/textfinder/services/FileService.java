package ru.finplatforms.interview.textfinder.services;

import java.nio.file.Path;
import java.util.List;

public interface FileService {

    List<Path> getSortedTxtFileListFromDirectory(String rootDirectory);

    String getSummaryTxtFileContents(String rootDirectory);

    boolean pathIsNotDirectory(String pathName);

    void saveSummaryTxtFile(String rootDirectory);
}
