package spring.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Likey {

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

    @EmbeddedId
    private LikeyId likeyId;

    public LikeyId getLikeyId() {
        return likeyId;
    }

    public void setLikeyId(LikeyId likeyId) {
        this.likeyId = likeyId;
    }
}
