package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.model.VideoInfo;
import klaa.mouataz.videos.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {


    private final VideoService videoService;
//    private final VideoRepository storage;
    private static final int CHUNK_SIZE = 1024 * 512;
//
//    @SuppressWarnings("java:S1215")
//    @PostMapping()
//    String saveMovie(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws  IOException {
//        storage.put(name, file);
//        return "Video saved successfully.";
//    }

//    @GetMapping("/{name}")
//    ResponseEntity<Resource> getMovieByName(@PathVariable String name) {
//        return storage.pull(name)
//                .map(Video::videoByteBuffer)
//                .map(ByteBuffer::slice)
//                .map(ByteBufferBackedInputStream::new)
//                .map(InputStreamResource::new)
//                .<ResponseEntity<Resource>>map(resource ->
//                        ResponseEntity.ok()
//                                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                                .body(resource))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }




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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveMovie(@RequestParam("file") MultipartFile file, @ModelAttribute VideoInfo info) throws IOException {
        videoService.save(info, file);
        return "Video saved successfully.";
    }
}




