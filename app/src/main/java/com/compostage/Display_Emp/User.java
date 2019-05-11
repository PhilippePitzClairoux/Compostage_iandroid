package com.compostage.Display_Emp;

public final class User {
    private String  username;
    private int userType;
    private String password;
    private String email;
    private String question;
    private String answer;

    //Empty constructor
    private void construct(){}

    //Allows to create a new user
    public User createNewUser(String username,
                                      int userType,
                                      String password,
                                      String email,
                                      String question,
                                      String answer){
        User instance = new User();

        instance.setUsername(username);
        instance.setUserType(userType);
        instance.setPassword(password);
        instance.setEmail(email);
        instance.setQuestion(question);
        instance.setAnswer(answer);

        return instance;
    }

    //Returns the username
    public String getUserName(){
        return username;
    }

    //Sets a new username for the user
    public void setUsername(String newUsername){
        username = newUsername;
    }

    //Returns the user's type
    public int getUsertType(){
        return userType;
    }

    //Sets a new user type for the user
    public void setUserType(int newUserType){
        this.userType = newUserType;
    }

    //Returns the user's password
    public String getPassword(){
        return password;
    }

    //Sets a new password for the user
    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    //Returns the user's E-mail
    public String getEmail(){
        return email;
    }

    //Sets a new E-mail for the user
    public void setEmail(String newMail){
        this.email = newMail;
    }

    //Returns the user's secret question
    public String getQuestion(){
        return question;
    }

    //Sets a new secret question for the user
    public void setQuestion(String newQuestion){
        this.question = newQuestion;
    }

    //Returns the user's secret question's answer
    public String getAnswer(){
        return answer;
    }

    //Sets a new answer for the user's secret question
    public void setAnswer(String newAnswer){
        this.answer = newAnswer;
    }
}
