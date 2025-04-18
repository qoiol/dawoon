package spring.project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name="review")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workoutId")
    private Workout workout;
    private String title;
    private String content;
    private int score;
    private int likeCount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
}
