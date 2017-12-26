package task3;

public class LoginChecker {

    @Login(reverse = "your name reversed")
    private String login;
    private String name;

    public void setName(String input) {
        name = input;
    }

    public String getName() { return name; }

    public void setLogin(String result) {
        login = result;
    }

    public String getLogin() {
        return login;
    }
}
