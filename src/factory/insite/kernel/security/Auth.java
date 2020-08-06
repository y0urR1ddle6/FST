package factory.insite.kernel.security;

public class Auth {
    private String login;
    private String password;

    public Auth(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        Auth auth = (Auth) obj;
        if(login.equals(auth.login) && Hash.str2Sha256(password).equals(auth.password))
            return true;
        return false;
    }
}
