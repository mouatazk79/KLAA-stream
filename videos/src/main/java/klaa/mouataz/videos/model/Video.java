package klaa.mouataz.videos.model;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Video {
    private Long id;
    private byte[] data;

}
