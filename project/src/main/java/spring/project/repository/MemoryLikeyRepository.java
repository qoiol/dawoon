package spring.project.repository;

import spring.project.domain.Likey;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class MemoryLikeyRepository implements LikeyRepository{

    List<Likey> store = new ArrayList<>();

    @Override
    public Likey save(Likey likey) {
        store.add(likey);
        return likey;
    }

    @Override
    public List<Likey> findByReviewId(Long reviewId) {
        List<Likey> result = new ArrayList<>();
        for(Likey l : store){
//            if(l.getReviewId()==reviewId)
//                result.add(l);
        }
        return result;
    }

    @Override
    public List<Likey> findByUserId(String userId) {
        List<Likey> result = new ArrayList<>();
        for(Likey l : store){
//            if(l.getUserId().equals(userId))
//                result.add(l);
        }
        return result;
    }

    @Override
    public Long countByReviewId(Long reviewId) {
        Long count = 0L;

        List<Likey> result = new ArrayList<>();
        for(Likey l : store){
//            if(l.getReviewId()==reviewId)
//                count++;
        }

        return count;
    }

    @Override
    public void deleteByReviewId(long reviewId) {

    }

    @Override
    public void delete(Likey likey) {

    }

    @Override
    public boolean existsByReviewAndUser(Likey likey) {
        return false;
    }
}
