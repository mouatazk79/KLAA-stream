package klaa.mouataz.videos.controller;

import klaa.mouataz.videos.model.VideoInfo;
import klaa.mouataz.videos.service.VideoInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/videoinfos")
public class VideoInfoController {
    private final VideoInfoService videoInfoService;
    @GetMapping("/{courseId}")
    public List<VideoInfo> getCourseVideoInfo(@PathVariable("courseId") String courseId){
        return videoInfoService.getVideosInfoByCourseId(courseId);
    }
    @PostMapping
    public VideoInfo addVideoInfoToCourse(@RequestBody VideoInfo info){
        return videoInfoService.addVideoInfo(info);
    }
}
