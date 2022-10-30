package dxc.bookstore.bookstoreapp.model.response;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private T data;
    private String errors;
    private HttpStatus status;

    public ApiResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }

    public ApiResponse(T data, String errors, HttpStatus status) {
        this.data = data;
        this.errors = errors;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
