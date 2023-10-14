package eaut.edu.vn.model;

public class Reader {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String sex;
    private int lostBooks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLostBooks() {
        return lostBooks;
    }

    public void setLostBooks(int lostBooks) {
        this.lostBooks = lostBooks;
    }


}
