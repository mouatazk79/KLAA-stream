package klaa.mouataz.videos.service;

import jakarta.annotation.PostConstruct;
import klaa.mouataz.videos.model.VideoInfo;
import klaa.mouataz.videos.repos.VideoInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class VideoService {

    private static final String DIR = "Z:/springprojects/KLAAschool/videos/src/main/resources/assets";
    private static  String HSL_DIR = "Z:/springprojects/KLAAschool/videos/src/main/resources/hsl_dir";
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

            // Save the uploaded file to /videos/ directory (use Unix-style path)
            Path path = Paths.get(DIR, filename).normalize();
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Saved video to: " + path.toString());

            // Save video information in the database
            VideoInfo newVideo = VideoInfo.builder()
                    .name(info.getName())
                    .courseId(info.getCourseId())
                    .path(filename)
                    .build();
            videoInfoRepository.save(newVideo);

            // Process the video (generate HLS files)
            processVideo(newVideo.getId(), newVideo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in processing video", e);
        }
    }

    public void processVideo(String videoId, String filePath) {
        Path videoPath = Paths.get("/app/assets/", filePath);

        // Define output directories for each resolution
        String output360p = HSL_DIR + "/" + videoId + "/360p/";
        String output720p = HSL_DIR + "/" + videoId + "/720p/";
        String output1080p = HSL_DIR + "/" + videoId + "/1080p/";

        try {
            // Create directories for the HLS output files
            Files.createDirectories(Paths.get(output360p));
            Files.createDirectories(Paths.get(output720p));
            Files.createDirectories(Paths.get(output1080p));
            Path outputPath = Paths.get(HSL_DIR, videoId);
            Files.createDirectories(outputPath);

            // Reset HSL_DIR to the container path (inside Docker)
            HSL_DIR = "/app/hsl_dir";

            // Build the ffmpeg command dynamically
            StringBuilder ffmpegCmd = new StringBuilder();
            ffmpegCmd.append("ffmpeg -i ")
                    .append("/app/assets/"+filePath)
                    .append(" -c:v libx264 -c:a aac ")
                    .append(" -map 0:v -map 0:a -s:v:0 640x360 -b:v:0 800k -hls_segment_filename '")
                    .append("/app/hsl_dir/"+videoId+"/360p/").append("fileSequence%d.ts' -hls_time 10 -hls_list_size 0 ")
                    .append("/app/hsl_dir/"+videoId+"/360p/").append("prog_index.m3u8 ")

                    .append("-map 0:v -map 0:a -s:v:1 1280x720 -b:v:1 2800k -hls_segment_filename '")
                    .append("/app/hsl_dir/"+videoId+"/720p/").append("fileSequence%d.ts' -hls_time 10 -hls_list_size 0 ")
                    .append("/app/hsl_dir/"+videoId+"/720p/").append("prog_index.m3u8 ")

                    .append("-map 0:v -map 0:a -s:v:2 1920x1080 -b:v:2 5000k -hls_segment_filename '")
                    .append("/app/hsl_dir/"+videoId+"/1080p/").append("fileSequence%d.ts' -hls_time 10 -hls_list_size 0 ")
                    .append("/app/hsl_dir/"+videoId+"/1080p/").append("prog_index.m3u8 ")

                    .append("-var_stream_map \"v:0,a:0 v:1,a:0 v:2,a:0\" ")
                    .append("-master_pl_name ").append(HSL_DIR).append("/").append(videoId).append("/master.m3u8 ");

            System.out.println("Executing FFmpeg command: " + ffmpegCmd.toString());

            ProcessBuilder processBuilder = new ProcessBuilder("docker", "exec", "b2c-ffmpeg", "/bin/bash", "-c", ffmpegCmd.toString());
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("Video processing failed with exit code " + exitCode);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Video processing failed due to an IOException", ex);
        } catch (InterruptedException e) {
            throw new RuntimeException("Video processing was interrupted", e);
        }
    }


}