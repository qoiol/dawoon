package spring.project.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaMyPageRepository implements MyPageRepository{

    @PersistenceContext
    private final EntityManager em;

    public JpaMyPageRepository(EntityManager em){
        this.em = em;
    }
}
