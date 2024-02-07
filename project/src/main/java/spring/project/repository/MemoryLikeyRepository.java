package spring.project.repository;

import org.springframework.stereotype.Repository;
import spring.project.domain.Likey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryLikeyRepository implements LikeyRepository{

    List<Likey> store = new ArrayList<>();

    @Override
    public void save(Likey likey) {
        store.add(likey);
    }

    @Override
    public List<Likey> findByReviewId(Long reviewId) {
        List<Likey> result = new ArrayList<>();
        for(Likey l : store){
            if(l.getReviewId()==reviewId)
                result.add(l);
        }
        return result;
    }

    @Override
    public List<Likey> findByUserId(String userId) {
        List<Likey> result = new ArrayList<>();
        for(Likey l : store){
            if(l.getUserId().equals(userId))
                result.add(l);
        }
        return result;
    }

    @Override
    public Long countByReviewId(Long reviewId) {
        Long count = 0L;

        List<Likey> result = new ArrayList<>();
        for(Likey l : store){
            if(l.getReviewId()==reviewId)
                count++;
        }

        return count;
    }
}
