package ru.finplatforms.interview.textfinder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.finplatforms.interview.textfinder.services.FileService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final Directory directory;
    private final FileService fileService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String showFiles(Model model) {
        String pathName = directory.getPathName();

        if (pathName == null) {
            return "filedetails";
        }

        List<Path> fileList = fileService.getSortedFileListFromDirectory(pathName);
        fileService.createSummaryTxtFileFromList(pathName, fileList);

        model.addAttribute("files", fileList);
        model.addAttribute("resultFile", fileService.getSummaryFileContents(pathName));
        return "filedetails";
    }

    @GetMapping("/directory")
    public String newDirectoryForm(Model model) {
        model.addAttribute("directory", directory);
        return "newdirectory";
    }

    @PostMapping("/directory")
    public String saveNewDirectory(@ModelAttribute Directory directory, Model model) {
        if (!Files.isDirectory(Path.of(directory.getPathName()))) {
            model.addAttribute("error", "directory does not exist, try again");
            return "newdirectory";
        }
        this.directory.setPathName(directory.getPathName());
        return "redirect:/";
    }
}