package dxc.bookstore.bookstoreapp.model.request;

public class SearchBookRequest {

    private String title;
    private Integer authorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
