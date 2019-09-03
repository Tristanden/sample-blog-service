package fr.liksi.blog.iam.api.bean;

import javax.validation.constraints.NotEmpty;

public class ApiAccount {

    private String lastname;

    private String firstname;

    @NotEmpty
    private String email;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
