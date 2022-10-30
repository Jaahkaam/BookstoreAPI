package dxc.bookstore.bookstoreapp.model.request;

public class ApiRequest<T> {

    private T data;
    private String username;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
