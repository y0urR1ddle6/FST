package factory.insite.kernel.session;

import factory.insite.kernel.security.Auth;
import factory.insite.kernel.security.User;
import factory.insite.server.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthSession {

    private final Scanner in;
    private final OutputStream out;
    private User user;
    public AuthSession(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void start(Configuration configuration) throws IOException {
        String login;
        String password;
        try {
            // send version ( client check) , ask login, ask pass
            out.write((configuration.getVersion() + "\nLogin: ").getBytes());
            login = in.nextLine();
            out.write("Password: ".getBytes());
            password = in.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Auth auth = new Auth(login,password);

        if(login(auth,configuration)) {
            System.out.println("-- Success login");
            InteractiveSession interactiveSession = new InteractiveSession(in,out,user);
            interactiveSession.start(configuration);
        }
        else {
            System.out.println("-- Invalid credentials");
        }
    }

    private boolean login(Auth auth, Configuration configuration){
        ArrayList<User> users = (ArrayList<User>) configuration.getUsers();
        for(User user : users)
            if(auth.equals(user.getAuth())){
                this.user = user;
                return true;
            }
        return false;
    }


}
