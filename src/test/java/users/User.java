package users;

public class User {
    private String userName;
    private String password;

    public User registeredUser(){
        User user = new User();
        user.userName = "elchupakabra@mailinator.com";
        user.password = "Test1234!";
        return user;
    }

    public User notRegisteredUser(){
        User user = new User();
        user.userName = "invalidUser@invalidMail.zz";
        user.password = "noPassword123!";
        return user;
    }

}
