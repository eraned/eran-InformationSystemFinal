package sample.Model;

public class User {
    private String userName;
    private String password;
    private String birth;
    private String firstName;
    private String lastName;
    private String city;

    public User(String userName,String password, String birth, String firstName,String lastName,String city){
        this.userName=userName;
        this.password=password;
        this.birth=birth;
        this.firstName=firstName;
        this.lastName=lastName;
        this.city=city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
