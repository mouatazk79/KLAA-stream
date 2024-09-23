package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.model.Video;
import klaa.mouataz.videos.repos.ByteBufferBackedInputStream;
import klaa.mouataz.videos.repos.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {


    private final VideoRepository storage;
    private static final int CHUNK_SIZE = 1024 * 512;

    @SuppressWarnings("java:S1215")
    @PostMapping()
    String saveMovie(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws  IOException {
        storage.put(name, file);
        return "Video saved successfully.";
    }

    @GetMapping("/{name}")
    ResponseEntity<Resource> getMovieByName(@PathVariable String name) {
        return storage.pull(name)
                .map(Video::videoByteBuffer)
                .map(ByteBuffer::slice)
                .map(ByteBufferBackedInputStream::new)
                .map(InputStreamResource::new)
                .<ResponseEntity<Resource>>map(resource ->
                        ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                .body(resource))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    @GetMapping("/stream")
    public ResponseEntity<Resource> streamMovie(
//            @PathVariable("videoId") String videoId,
            @RequestHeader(value = "Range",required = false)String range
            ){
        System.out.println(range);

        Path path= Paths.get("Z:/springprojects/KLAAschool/videos/src/main/resources/assets/What_Is_A_Woman.mp4");
        Resource resource=new FileSystemResource(path);
        String content="application/octet-stream";
        long fileLength=path.toFile().length();

        if (range==null){
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(content))
                    .body(resource);
        }
        long rangeStart;
        long rangeEnd;

        String[] ranges = range.replace("bytes=", "").split("-");
        rangeStart=Long.parseLong(ranges[0]);

        rangeEnd=rangeStart+CHUNK_SIZE-1;
        if (rangeEnd>=fileLength){
            rangeEnd=fileLength-1;
        }
        System.out.println("******************** "+rangeStart);
        System.out.println("******************** "+rangeEnd);

        InputStream inputStream;
        try {
            inputStream= Files.newInputStream(path);
            inputStream.skip(rangeStart);
            long contentLength=rangeEnd-rangeStart+1;

            byte[] bytes=new byte[(int)contentLength];
            int read=inputStream.read(bytes,0,bytes.length);
            System.out.println("******************** "+read);

            HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("X-Content-Type-Options", "nosniff");
            headers.setContentLength(contentLength);

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .contentType(MediaType.parseMediaType(content))
                    .body(new ByteArrayResource(bytes));

        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }




    }




















//    @GetMapping("/stream-video")
//    public ResponseEntity<InputStreamResource> streamVideo( HttpServletResponse response)  {
//        File videoFile = new File("Z:/springprojects/KLAAschool/videos/src/main/resources/assets/What_Is_A_Woman.mp4");
//        if (!videoFile.exists()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        try (FileInputStream fis = new FileInputStream(videoFile);
//             FileChannel fileChannel = fis.getChannel()) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + videoFile.getName() + "\"");
//            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + videoFile.getName() + "\"");
//            response.setContentLengthLong(videoFile.length());
//            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
//            while (fileChannel.read(buffer) > 0) {
//                buffer.flip();
//                response.getOutputStream().write(buffer.array(), 0, buffer.limit());
//                response.flushBuffer();
//                buffer.clear();
//            }
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/stream-video")
//    public ResponseEntity<StreamingResponseBody> streamVideo(  @RequestHeader(value = "Range", required = false) String range) throws IOException {
//
//        File videoFile = new File("Z:/springprojects/KLAAschool/videos/src/main/resources/assets/What_Is_A_Woman.mp4");
//        long videoLength = videoFile.length();
//        long rangeStart = 0;
//        long rangeEnd = videoLength - 1;
//
//        // Parse Range header if available
//        if (range != null && range.startsWith("bytes=")) {
//            String[] ranges = range.split("=")[1].split("-");
//            rangeStart = Long.parseLong(ranges[0]);
//            if (ranges.length > 1) {
//                rangeEnd = Long.parseLong(ranges[1]);
//            }
//        }
//
//        long contentLength = rangeEnd - rangeStart + 1;
//        RandomAccessFile videoStream = new RandomAccessFile(videoFile, "r");
//        videoStream.seek(rangeStart);
//
//        // Send video data as soon as it becomes available using StreamingResponseBody
//        StreamingResponseBody responseBody = outputStream -> {
//            byte[] buffer = new byte[CHUNK_SIZE];
//            long bytesRead = 0;
//
//            while (bytesRead < contentLength) {
//                // Read the chunk from the file
//                int bytesToRead = (int) Math.min(CHUNK_SIZE, contentLength - bytesRead);
//                int read = videoStream.read(buffer, 0, bytesToRead);
//                if (read == -1) {
//                    break;
//                }
//
//                // Write the chunk to the output stream immediately
//                outputStream.write(buffer, 0, read);
//                outputStream.flush();  // Flush after each chunk to enable real-time delivery
//
//                bytesRead += read;
//            }
//            videoStream.close();
//        };
//
//        return ResponseEntity.status(range != null ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
//                .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
//                .header(HttpHeaders.ACCEPT_RANGES, "bytes")
//                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength))
//                .header(HttpHeaders.CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + videoLength)
//                .body(responseBody);
//    }
}




