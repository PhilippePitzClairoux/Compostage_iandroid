package com.compostage.Data;

public class User implements IDataBase {

    private String username;
    private UserType usertype;
    private String password;
    private String email;
    private String authquestion;
    private String authanswer;


    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthquestion() {
        return authquestion;
    }

    public void setAuthquestion(String authquestion) {
        this.authquestion = authquestion;
    }

    public String getAuthanswer() {
        return authanswer;
    }

    public void setAuthanswer(String authanswer) {
        this.authanswer = authanswer;
    }

    @Override
    public void fetch_data() {

    }

    @Override
    public void insert_data() {

    }

    @Override
    public void sync_data() {

    }
}
