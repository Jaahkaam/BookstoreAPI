package dxc.bookstore.bookstoreapp.model.request;

public class AuthorRequest {

    private String name;
    private String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "AuthorRequest{" +
                "name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
