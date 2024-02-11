package spring.project.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity(name="review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId;
    private long workoutId;
    private String title;
    private String content;
    private int score;
    private int likeCount;
    @CreatedDate
    private String postedDate;

    @Transient
    private String workoutName;
    @Transient
    private String trainerName;

    public Review() {    };

    public Review(long id, String userId, long workoutId, String title, String content, int score, int likeCount, String postedDate) {
        this.id = id;
        this.userId = userId;
        this.workoutId = workoutId;
        this.title = title;
        this.content = content;
        this.score = score;
        this.likeCount = likeCount;
        this.postedDate = postedDate;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}
