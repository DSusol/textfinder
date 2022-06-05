package ru.finplatforms.interview.textfinder.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.finplatforms.interview.textfinder.utils.FileTypeChecker;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileTypeChecker fileTypeChecker;

    @SneakyThrows
    @Override
    public List<Path> getSortedTxtFileListFromDirectory(String rootDirectory) {
        try (Stream<Path> paths = Files.walk(Paths.get(rootDirectory))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(fileTypeChecker::isTextFile)
                    .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                    .collect(Collectors.toList());
        }
    }

    @SneakyThrows
    @Override
    public String getSummaryTxtFileContents(String rootDirectory) {
        List<String> lines = new ArrayList<>();
        List<Path> sourceFiles = getSortedTxtFileListFromDirectory(rootDirectory);

        for (Path file: sourceFiles) {
            Files.lines(file).forEach(lines::add);
        }
        return String.join("\n", lines);
    }

    @Override
    public boolean pathIsNotDirectory(String pathName) {
        return !Files.isDirectory(Path.of(pathName));
    }

    @SneakyThrows
    @Override
    public void saveSummaryTxtFile(String rootDirectory) {
        String contents = getSummaryTxtFileContents(rootDirectory);
        rootDirectory = appendFileSeparatorIfMissing(rootDirectory);
        Files.write(Paths.get(rootDirectory + "Summary.txt"), Collections.singleton(contents));
    }

    private String appendFileSeparatorIfMissing(String pathName) {
        if (pathName.lastIndexOf(File.separator) != (pathName.length() - 1)) {
            pathName += File.separator;
        }
        return pathName;
    }
}