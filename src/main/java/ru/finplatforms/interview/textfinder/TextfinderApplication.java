package ru.finplatforms.interview.textfinder;

import static java.lang.System.exit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.finplatforms.interview.textfinder.services.FileServiceImpl;
import ru.finplatforms.interview.textfinder.utils.ExtensionBasedFileTypeChecker;

@SpringBootApplication
public class TextfinderApplication {

    public static void main(String[] args) {
        if (args.length != 0) {
            FileServiceImpl service = new FileServiceImpl(new ExtensionBasedFileTypeChecker());
            String rootDirectory = args[0];

            if (service.pathIsNotDirectory(rootDirectory)) {
                System.out.println("invalid directory provided");
            } else {
                service.saveSummaryTxtFile(rootDirectory);
                System.out.println("summary file is saved in '" + rootDirectory + "'");
            }

            exit(0);
        }
        SpringApplication.run(TextfinderApplication.class, args);
    }
}