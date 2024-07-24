package klaa.mouataz.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Token {
    @Id
    @SequenceGenerator(
            name="token_sequence_generator",
            sequenceName = "token_sequence_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(generator = "token_sequence_generator",strategy = GenerationType.SEQUENCE)
    private Long id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
