package com.example.gamingnews;

public class firebasemodel {

    private String title;
    private String content;
    private String email;
    private String firstname;
    private String lastname;
    private String age;
    private String about;

    public firebasemodel()
    {

    }

    public firebasemodel (String title, String content, String email, String firstname, String lastname, String age, String about)
    {
        this.title = title;
        this.content = content;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.about = about;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstname () {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname () {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge () {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAbout () {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}

