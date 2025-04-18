package spring.project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewForm {
    @NotBlank(message = "제목을 20자 이내로 입력해주세요.")
    @Size(min=1, max=20, message = "제목을 20자 이내로 입력해주세요.")
    String title;
    @NotBlank(message = "내용을 300자 이내로 입력해주세요.")
    @Size(min=1, max=300, message = "내용을 300자 이내로 입력해주세요.")
    String content;
    @NotBlank(message = "후기를 남길 강좌를 선택해주세요.")
    long workoutId;
    @Min(value = 1, message = "점수를 입력해주세요.(1-5)")
    @Max(value = 5, message = "점수를 입력해주세요.(1-5)")
    int score;
}
