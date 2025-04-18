package spring.project.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListRequest {
    private Long workoutId;
    @Builder.Default
    private String orderby = "r.postedDate";
    @Size(max = 20, message = "20자 이내로 검색해주세요.")
    private String keyword;
    @Builder.Default
    private Integer pageNo = 1;
}
