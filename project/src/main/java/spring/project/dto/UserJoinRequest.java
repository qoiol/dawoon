package spring.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJoinRequest {
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{5,20}$", message = "아이디는 영문 소문자와 숫자 조합으로 5자 이상 20자 이하로 입력해주세요.")
    private String id;
    @NotBlank(message = "이름을 입력해주세요. (2~20자)")
    @Size(max = 20, min = 2, message = "이름을 입력해주세요. (2~20자)")
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "^(ROLE_USER)|(ROLE_TRAINER)$", message = "올바른 회원 유형이 아닙니다.")
    private String userType;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]{1,20}@[a-zA-Z0-9.-]{1,20}(\\.[a-zA-Z]{2,3})+$", message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    //    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()-+=])[a-zA-Z\\d!@#$%^&*()-+=]{8,20}$"
//            , message = "비밀번호는 영어, 숫자, 특수문자를 포함해야 하며, 8~20자여야 합니다.")
}
