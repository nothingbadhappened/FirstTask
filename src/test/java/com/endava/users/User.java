package com.endava.users;

public class User {
    // == fields ==
    private int userId;
    private String userEmail;
    private String userFullName;
    private String userPassword;
    private boolean isUserRegistered;

    // == getters ==
    public int getUserId(){
        return userId;
    }
    public String getUserFullName() {
        return userFullName;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public String getUserPassword(){
        return userPassword;
    }
    public boolean getRegistrationStatus(){
        return isUserRegistered;
    }

    // == setters ==
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    public void setRegistrationStatus(String isRegistered){
        switch(isRegistered){
            case "is registered":
            case "registered":
            case "1": {
                this.isUserRegistered = true;
                break;
            }
            case "0":
            case "not registered":
                this.isUserRegistered = false;
                break;
        }
    }

}
