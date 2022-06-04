package ru.finplatforms.interview.textfinder.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import ru.finplatforms.interview.textfinder.services.FileService;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final DirectoryHolder directoryHolder;
    private final FileService fileService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String showFiles(Model model) {
        String pathName = directoryHolder.getPathName();

        if (pathName == null) {
            return "fileDetailsView";
        }

        List<Path> txtFileList = fileService.getSortedTxtFileListFromDirectory(pathName);
        fileService.createSummaryTxtFileFromList(pathName, txtFileList);

        model.addAttribute("txtFiles", txtFileList);
        model.addAttribute("summaryFileContents", fileService.getSummaryFileContents(pathName));
        return "fileDetailsView";
    }

    @GetMapping("/directory")
    public String newDirectoryForm(Model model) {
        model.addAttribute("directory", directoryHolder);
        return "newDirectoryView";
    }

    @PostMapping("/directory")
    public String saveNewDirectory(@ModelAttribute DirectoryHolder newDirectory, Model model) {
        if (!Files.isDirectory(Path.of(newDirectory.getPathName()))) {
            model.addAttribute("directory", newDirectory);
            model.addAttribute("error", "directory does not exist, try again");
            return "newDirectoryView";
        }
        directoryHolder.setPathName(newDirectory.getPathName());
        return "redirect:/";
    }
}