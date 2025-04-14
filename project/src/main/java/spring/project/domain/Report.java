package spring.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userId;
    Long reviewId;
    String reportReason;
}
