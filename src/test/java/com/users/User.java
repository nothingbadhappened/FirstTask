package com.users;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String userEmail;
    private String userPassword;
    private boolean isUserRegistered;

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public boolean getRegistrationStatus(){
        return isUserRegistered;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public void setRegistrationStatus(String isRegistered){
        switch(isRegistered){
            case "registered":
                this.isUserRegistered = true;
                break;

            case "not registered":
                this.isUserRegistered = false;
                break;
        }
    }

}
