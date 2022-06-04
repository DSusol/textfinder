package ru.finplatforms.interview.textfinder.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
class FileServiceImpl implements FileService {

    private final String SUMMARY_FILE = "Summary.txt";
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
    public void createSummaryTxtFileFromList(String pathName, List<Path> sourceFiles) {
        List<String> lines = new ArrayList<>();

        for (Path file: sourceFiles) {
            Files.lines(file).forEach(lines::add);
        }

        Files.write(Paths.get(pathName + SUMMARY_FILE), lines);
    }

    @SneakyThrows
    @Override
    public String getSummaryFileContents(String pathName) {
        StringBuilder contents = new StringBuilder();
        Files.lines(Paths.get(pathName + SUMMARY_FILE))
                .forEach(line -> contents.append(line).append("\n"));
        return contents.toString();
    }
}