package spring.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "userinfo") // <- 테이블 이름으로 User 사용 불가, 다른 이름 지정
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String userType;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
