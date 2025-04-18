package spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import spring.project.domain.Review;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewListResponse {
    private int pageSize;
    private int pageBlockSize;
    private List<Review> reviewList;
    private Integer pageNo;
    private int count;
    private int lastPage;
    private int pageBlockStart;
    private int pageBlockEnd;
    private boolean prevBlockFlag;
    private boolean nextBlockFlag;
    private String url;

    public ReviewListResponse(List<Review> reviewList, Integer pageNo, int count, int pageSize, int pageBlockSize, String url) {
        this.pageSize = pageSize;
        this.pageBlockSize = pageBlockSize;
        this.reviewList = reviewList;
        this.pageNo = pageNo;
        this.count = count;
        this.url = url;

        this.lastPage = Math.max(count - 1, 0) / pageSize + 1;

        this.pageBlockStart = pageBlockSize * ((pageNo - 1) / pageSize) + 1;
        this.pageBlockEnd = Math.min(lastPage, this.pageBlockStart + pageBlockSize);

        this.prevBlockFlag = pageNo > pageBlockSize;
        this.nextBlockFlag = pageBlockEnd < lastPage;
    }
}
