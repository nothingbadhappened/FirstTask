package users;

public class User {
    private String userName;
    private String password;
    private boolean isRegistered;

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public boolean getRegistrationStatus(){
        return isRegistered;
    }

    public void setUserName(String userName){
        this.userName = userName;
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
