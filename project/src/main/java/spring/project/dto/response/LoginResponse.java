package spring.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.project.dto.SessionUserDTO;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private SessionUserDTO user;
}
