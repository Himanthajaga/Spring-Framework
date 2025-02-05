package lk.ijse.dto;

public class CustomerDTO {
    private String FirstName;
    private String LastName;
    private int Age;

    public CustomerDTO() {

    }

    public CustomerDTO(String firstName, String lastName, int age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Age=" + Age +
                '}';
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
