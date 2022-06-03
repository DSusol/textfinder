package ru.finplatforms.interview.textfinder.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.finplatforms.interview.textfinder.utils.FileTypeChecker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final String SUMMARY_FILE = "Summary.txt";
    private final FileTypeChecker fileTypeChecker;

    @SneakyThrows
    @Override
    public List<Path> getSortedFileListFromDirectory(String rootDirectory) {
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
    public void createSingleTxtFileFromList(String pathName, List<Path> sourceFiles) {
        List<String> lines = new ArrayList<>();

        for (Path file : sourceFiles) {
            Files.lines(file).forEach(lines::add);
        }

        Files.write(Paths.get(pathName + SUMMARY_FILE), lines);
    }

    @SneakyThrows
    @Override
    public String getSummaryFileContents(String pathName) {
        StringBuilder content = new StringBuilder();
        Files.lines(Paths.get(pathName + SUMMARY_FILE))
                .forEach(line -> content.append(line).append("\n"));
        return content.toString();
    }
}