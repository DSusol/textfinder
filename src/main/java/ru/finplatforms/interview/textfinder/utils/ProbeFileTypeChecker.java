package ru.finplatforms.interview.textfinder.utils;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Profile("contentProbeFileType")
public class ProbeFileTypeChecker implements FileTypeChecker {

    @SneakyThrows
    @Override
    public boolean isTextFile(Path file) {
        String requiredFileType = "text/plain";
        String actualFileType = Files.probeContentType(file);
        return actualFileType.equals(requiredFileType);
    }
}
