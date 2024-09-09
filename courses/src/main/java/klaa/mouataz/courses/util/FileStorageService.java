package klaa.mouataz.courses.util;

import klaa.mouataz.courses.model.Course;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private String fileUploadPath="./uploads";
    public String saveFile(@NonNull MultipartFile file,@NonNull Course course) throws IOException {

        final String fileUploadSubPath="covers"+ File.separator;
        return uploadFile(file,fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile file, @NonNull String fileUploadSubPath) throws IOException {
        String finalPath=fileUploadPath+File.separator+fileUploadPath;
        File targetFolder=new File(finalPath);
        if(!targetFolder.exists()){
            boolean created=targetFolder.mkdirs();
        }
        String targetFilePath=finalPath+File.separator+System.currentTimeMillis()+"."+file.getName();
        Path path= Paths.get(targetFilePath);
        Files.write(path,file.getBytes());

        return targetFilePath;
    }
}
