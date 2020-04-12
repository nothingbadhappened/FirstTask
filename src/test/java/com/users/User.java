package com.users;

public class User {
    private String userEmail;
    private String password;
    private boolean isRegistered;

    public User(){

    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getPassword(){
        return password;
    }

    public boolean getRegistrationStatus(){
        return isRegistered;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRegistrationStatus(String isRegistered){
        switch(isRegistered){
            case "registered":
                this.isRegistered = true;
                break;

            case "not registered":
                this.isRegistered = false;
                break;
        }
    }

}
