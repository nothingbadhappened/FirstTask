package com.endava.users;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {
    // == fields ==
    private int userId;
    private String userEmail;
    private String userPassword;
    private boolean isUserRegistered;

    // == getters ==
    public int getUserId(){
        return userId;
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
    public void setUserId (int userId){
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
