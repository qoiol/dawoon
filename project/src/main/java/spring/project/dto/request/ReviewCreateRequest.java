package spring.project.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {
    @NotBlank(message = "제목을 20자 이내로 입력해주세요.")
    @Size(min=1, max=20, message = "제목을 20자 이내로 입력해주세요.")
    String title;
    @NotBlank(message = "내용을 300자 이내로 입력해주세요.")
    @Size(min=1, max=300, message = "내용을 300자 이내로 입력해주세요.")
    String content;
    @NotNull(message = "후기를 남길 강좌를 선택해주세요.")
    long workoutId;
    @Min(value = 1, message = "점수를 입력해주세요.(1-5)")
    @Max(value = 5, message = "점수를 입력해주세요.(1-5)")
    int score;
}
