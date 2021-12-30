package com.example.gamingnews;

public class firebasemodel {

    private String title;
    private String content;
    private String email;
    private String firstname;

    public firebasemodel()
    {

    }

    public firebasemodel (String title, String content, String email, String firstname)
    {
        this.title = title;
        this.content = content;
        this.email = email;
        this.firstname = firstname;

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


}

