package madel;

public class User {
    private int id;
    private String name;
    private String password;
    private String borrow;
    private int borrow_right;

    public User() {
        super();
    }

    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBorrow() {
        return borrow;
    }

    public void setBorrow(String borrow) {
        this.borrow = borrow;
    }

    public int getBorrow_right() {
        return borrow_right;
    }

    public void setBorrow_right(int borrow_right) {
        this.borrow_right = borrow_right;
    }
}
