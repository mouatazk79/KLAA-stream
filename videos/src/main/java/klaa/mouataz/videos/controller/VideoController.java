package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.model.Video;
import klaa.mouataz.videos.repos.ByteBufferBackedInputStream;
import klaa.mouataz.videos.repos.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;


@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {


    private final VideoRepository storage;

    @SuppressWarnings("java:S1215")
    @PostMapping()
    String saveMovie(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws  IOException {
        storage.put(name, file);
      //  log.info(Information.getMemoryInformation(storage.getTotalNoHeapMemoryUsage()));
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
































//    long start = 0;
//    @GetMapping("/video/{filename}")
//    public Mono<Void> streamVideo(@PathVariable String filename,
//                                  @RequestHeader(value = "Range", required = false) String rangeHeader,
//                                  ServerHttpRequest request,
//                                  ServerHttpResponse response) throws IOException {
//
//        Resource videoResource = resourceLoader.getResource("classpath:assets/" + filename);
//
//        if (!videoResource.exists() || !videoResource.isReadable()) {
//            response.setStatusCode(HttpStatus.NOT_FOUND);
//            return response.setComplete();
//        }
//
//        File videoFile = videoResource.getFile();
//        long videoSize = videoFile.length();
//        HttpHeaders headers = response.getHeaders();
//        headers.setContentType(MediaType.valueOf("video/mp4"));
//
//        long end = videoSize - 1;
//
//        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
//            String[] ranges = rangeHeader.substring(6).split("-");
//            try {
//                start = Long.parseLong(ranges[0]);
//                if (ranges.length > 1) {
//                    end = Long.parseLong(ranges[1]);
//                }
//            } catch (NumberFormatException e) {
//                start = 0;
//                end = videoSize - 1;
//            }
//        }
//
//        if (end > videoSize - 1) {
//            end = videoSize - 1;
//        }
//
//        long contentLength = end - start + 1;
//        headers.add("Content-Range", "bytes " + start + "-" + end + "/" + videoSize);
//        headers.setContentLength(contentLength);
//
//        response.setStatusCode(HttpStatus.PARTIAL_CONTENT);
//
//        return response.writeWith(Mono.fromSupplier(() -> {
//            try {
//                RandomAccessFile randomAccessFile = new RandomAccessFile(videoFile, "r");
//                randomAccessFile.seek(start);
//                FileChannel fileChannel = randomAccessFile.getChannel();
//                return new ZeroCopyHttpOutputMessage();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }));
//    }
}
