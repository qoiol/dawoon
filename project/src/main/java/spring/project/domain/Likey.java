package spring.project.domain;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Likey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;



//    @Id
//    private long reviewId;
//    @Id
//    private String userId;
//
//    public long getReviewId() {
//        return reviewId;
//    }
//
//    public void setReviewId(long reviewId) {
//        this.reviewId = reviewId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

//    @EmbeddedId
//    private LikeyId likeyId;
//
//    public LikeyId getLikeyId() {
//        return likeyId;
//    }
//
//    public void setLikeyId(LikeyId likeyId) {
//        this.likeyId = likeyId;
//    }
}
