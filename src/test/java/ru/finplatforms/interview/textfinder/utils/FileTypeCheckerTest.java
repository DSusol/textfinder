package ru.finplatforms.interview.textfinder.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FileTypeCheckerTest {

    private final FileTypeChecker extensionBasedChecker = new ExtensionBasedFileTypeChecker();
    private final FileTypeChecker probeFileChecker = new ProbeFileTypeChecker();

    @ParameterizedTest(name = "Run {index}: filename = {0}")
    @MethodSource("fileNameArgumentProvider")
    void extension_based_checker_should_identify_text_files(String fileName, boolean response) {
        assertThat(extensionBasedChecker.isTextFile(Paths.get(fileName))).isEqualTo(response);
    }

    @ParameterizedTest(name = "Run {index}: filename = {0}")
    @MethodSource("fileNameArgumentProvider")
    void probe_file_type_checker_should_identify_text_files(String fileName, boolean response) {
        assertThat(probeFileChecker.isTextFile(Paths.get(fileName))).isEqualTo(response);
    }

    static Stream<Arguments> fileNameArgumentProvider() {
        return Stream.of(
                arguments("filename.txt", true),
                arguments("filename.png", false),
                arguments("filename.jpg", false),
                arguments("filename.exe", false)
        );
    }
}