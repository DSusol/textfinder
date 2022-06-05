package ru.finplatforms.interview.textfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

class TextfinderITest extends BaseFullContextTest {

    @Test
    void when_sending_root_directory_should_generate_sorted_summatytxt_file() throws Exception {
        //given
        writeFileIntoRandomDirectory("1file.txt", List.of("file1"));
        writeFileIntoRandomDirectory("2file.txt", List.of("Line2", "Line2"));
        writeFileIntoRandomDirectory("3file.txt", List.of("aaa", "bbb", "ccc"));
        writeFileIntoRandomDirectory("file.text", List.of("Line4", "Line4", "Line4", "Line4"));

        //when
        mockMvc.perform(post("/directory")
                .param("pathName", BASE_DIRECTORY));

        mockMvc.perform(get("/save"));

        //then
        Path summaryFile = Path.of(BASE_DIRECTORY + S + SUMMARY_FILE);
        assertThat(Files.exists(summaryFile)).isTrue();
        assertThat(getSummaryFileContents(BASE_DIRECTORY))
                .isEqualTo("file1\nLine2\nLine2\naaa\nbbb\nccc\n");
    }
}
