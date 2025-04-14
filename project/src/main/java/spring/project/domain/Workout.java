package spring.project.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Workout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workoutId;
    private String workoutType;
    private String workoutDifficulty;
    @ManyToOne
    @JoinColumn(name = "trainerId")
    private User trainer;
    private String trainerName;
    private String workoutName;
}
