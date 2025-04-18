package spring.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NO_TOKEN(HttpStatus.UNAUTHORIZED, "cannot find tokens"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "access denied")
    ;


    private HttpStatus status;
    private String message;
}
