package pl.dmcs.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private Date birthday;
    private int age;

    public Person() {
    }

    public Person(String firstName, String lastName, Date birthday, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = age;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringBuilder("FirstName: " + firstName + ", lastName: " + lastName + ", birthday: " + birthday.toString() + ", age: " + age).toString();
    }
}
