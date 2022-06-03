package ru.finplatforms.interview.textfinder;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TextfinderITests {

    public static final String BASE_DIRECTORY = "." + File.separator + "testfoder";
    public static final String s = File.separator;

    Random random = new Random();
    List<String> directories = new ArrayList<>();
//    private Path txtFile1;
//    private Path txtFile2;
//    private Path txtFile3;
//    private Path file4;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    static void cleanUp() throws IOException {
        Files.walk(Paths.get(BASE_DIRECTORY))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    @Order(1)
    void when_sending_root_directory_should_generate_summatytxt_file() throws IOException {
        //given
        fileInitialization();
        writeFile("1file.txt", List.of("file1"));
        writeFile("2file.txt", List.of("Line2", "Line2"));
        writeFile("3file.txt", List.of("aaa", "bbb", "ccc"));
        writeFile("file.text", List.of("Line4", "Line4", "Line4", "Line4"));

//        List<String> file1Text = List.of("file1");
//        List<String> file2Text = List.of("Line2", "Line2");
//        List<String> file3Text = List.of("aaa", "bbb", "ccc");
//        List<String> file4Text = List.of("Line4", "Line4", "Line4", "Line4");


        //when

        //then

    }

    private void fileInitialization() throws IOException {
        String dir1 = BASE_DIRECTORY + s + "subfld1" + s;
        String dir11 = BASE_DIRECTORY + s + "subfld1" + s + "subfld11" + s;
        String dir2 = BASE_DIRECTORY + s + "subfld2" + s;

        directories.addAll(List.of(dir1, dir11, dir2));

//        txtFile1 = Paths.get(dir2 + "1file.txt");
//        txtFile2 = Paths.get(dir11 + "2file.txt");
//        txtFile3 = Paths.get(dir1 + "3file.txt");
//        file4 = Paths.get(dir1 + "file.txtfile");

//        createDirectories(List.of(dir1, dir11, dir2));
//        createFiles(List.of(txtFile1, txtFile2, txtFile3, file4));
    }

    private void writeFile(String fileName, List<String> lines) throws IOException {
        String randomDirectory = directories.get(random.nextInt(directories.size()));
        Files.write(Paths.get(randomDirectory + "file5.txt"), lines);
    }

//    private void createDirectories(List<String> directories) throws IOException {
//        for (String dir : directories) {
//            Files.createDirectories(Paths.get(dir));
//        }
//    }
//
//    private void writeTextIntoFile(Path file, List<String> lines) throws IOException {
//        Files.write(file, lines);
//    }
}
