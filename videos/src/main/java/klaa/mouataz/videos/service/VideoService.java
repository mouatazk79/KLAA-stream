package klaa.mouataz.videos.service;

import jakarta.annotation.PostConstruct;
import klaa.mouataz.videos.model.VideoInfo;
import klaa.mouataz.videos.repos.VideoInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class VideoService {
    String DIR="Z:/springprojects/KLAAschool/videos/src/main/resources/assets";

    String HSL_DIR="Z:/springprojects/KLAAschool/videos/src/main/resources/hsl_dir";


    private final VideoInfoRepository videoInfoRepository;


    @PostConstruct
    public void init() {

        File file = new File(DIR);


        try {
            Files.createDirectories(Paths.get(HSL_DIR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Folder Created:");
        } else {
            System.out.println("Folder already created");
        }

    }

    @Transactional
    public void save(VideoInfo info, MultipartFile file) {

        try {


            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();



            Path path = Paths.get(DIR, filename);


            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            VideoInfo newVideo=VideoInfo.builder()
                    .name(info.getName())
                    .courseId(info.getCourseId())
                    .path(path.toString())
                    .build();



            videoInfoRepository.save(newVideo);
            processVideo(newVideo.getId(),newVideo.getPath());


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in processing video ");
        }


    }



    public String processVideo(String videoId,String filePath) {


        Path videoPath = Paths.get(filePath);


        String output360p = HSL_DIR + videoId + "/360p/";
        String output720p = HSL_DIR + videoId + "/720p/";
        String output1080p = HSL_DIR + videoId + "/1080p/";

        try {
            Files.createDirectories(Paths.get(output360p));
            Files.createDirectories(Paths.get(output720p));
            Files.createDirectories(Paths.get(output1080p));

            Path outputPath = Paths.get(HSL_DIR, videoId);

            Files.createDirectories(outputPath);


            String ffmpegCmd = "ffmpeg  -i " +
                    videoPath.toString() +
                    " -c:v libx264 -c:a aac" +
                    " " +
                    "-map 0:v -map 0:a -s:v:0 640x360 -b:v:0 800k " +
                    "-map 0:v -map 0:a -s:v:1 1280x720 -b:v:1 2800k " +
                    "-map 0:v -map 0:a -s:v:2 1920x1080 -b:v:2 5000k " +
                    "-var_stream_map \"v:0,a:0 v:1,a:0 v:2,a:0\" " +
                    "-master_pl_name " + HSL_DIR + videoId + "/master.m3u8 " +
                    "-f hls -hls_time 10 -hls_list_size 0 " +
                    "-hls_segment_filename \"" + HSL_DIR + videoId + "/v%v/fileSequence%d.ts\" " +
                    "\"" + HSL_DIR + videoId + "/v%v/prog_index.m3u8\"";


            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", ffmpegCmd);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exit = process.waitFor();
            if (exit != 0) {
                throw new RuntimeException("video processing failed!!");
            }

            return videoId;


        } catch (IOException ex) {
            throw new RuntimeException("Video processing fail!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
