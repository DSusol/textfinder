package ru.finplatforms.interview.textfinder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.finplatforms.interview.textfinder.services.FileService;

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
        fileService.createSingleTxtFileFromList(pathName, fileList);

        model.addAttribute("files", fileList);
        model.addAttribute("resultFile", fileService.getSummaryFileContents(pathName));
        return "filedetails";
    }

    @GetMapping("/directory")
    public String newDirectory(Model model) {
        model.addAttribute("directory", directory);
        return "newdirectory";
    }

    @PostMapping("/directory")
    public String saveOrUpdate(@ModelAttribute Directory directory) {
        this.directory.setPathName(directory.getPathName());
        return "redirect:/";
    }
}