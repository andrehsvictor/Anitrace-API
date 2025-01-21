package andrehsvictor.anitrace.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

    public String read(String path) {
        try {
            if (path.startsWith("file:")) {
                Path filePath = Paths.get(path.replace("file:", ""));
                return Files.readString(filePath);
            } else if (path.startsWith("classpath:")) {
                Path filePath = new ClassPathResource(path.replace("classpath:", "")).getFile().toPath();
                return Files.readString(filePath);
            } else {
                throw new RuntimeException("Invalid path");
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while trying to read the file");
        }
    }
}
