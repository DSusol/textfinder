package ru.finplatforms.interview.textfinder;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.finplatforms.interview.textfinder.services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseFullContextTest {

    protected static final String SUMMARY_FILE = "Summary.txt";
    protected static final String S = File.separator;
    protected static final String BASE_DIRECTORY = "." + S + "testfoder" + S;

    private static final List<String> directories = new ArrayList<>();
    private final Random random = new Random();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FileService fileService;

    @BeforeAll
    static void testSetUp() throws IOException {
        Files.walk(Paths.get(BASE_DIRECTORY))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);

        String dir1 = BASE_DIRECTORY + S + "subfld1" + S;
        String dir11 = BASE_DIRECTORY + S + "subfld1" + S + "subfld11" + S;
        String dir2 = BASE_DIRECTORY + S + "subfld2" + S;

        directories.addAll(List.of(BASE_DIRECTORY, dir1, dir11, dir2));
    }


    protected void writeFileIntoRandomDirectory(String fileName, List<String> lines) throws IOException {
        String randomDirectory = directories.get(random.nextInt(directories.size()));
        Files.write(Paths.get(randomDirectory + fileName), lines);
    }

    public String getSummaryFileContents(String pathName) {
        return fileService.getSummaryFileContents(pathName);
    }
}