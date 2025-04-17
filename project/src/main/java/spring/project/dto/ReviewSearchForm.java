package spring.project.dto;

public class ReviewSearchForm {
    private Long sworkoutid;
    private String orderby;
    private String keyword;

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public Long getSworkoutid() {
        return sworkoutid;
    }

    public void setSworkoutid(Long sworkoutid) {
        this.sworkoutid = sworkoutid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
