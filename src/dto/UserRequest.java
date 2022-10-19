package dto;

public class UserRequest {

    private String id;
    private String name;
    private String phoneNumber;
    private Integer xLocation;
    private Integer yLocation;
    private String email;

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

    public Integer getxLocation() {
        return xLocation;
    }

    public void setxLocation(Integer xLocation) {
        this.xLocation = xLocation;
    }

    public Integer getyLocation() {
        return yLocation;
    }

    public void setyLocation(Integer yLocation) {
        this.yLocation = yLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
