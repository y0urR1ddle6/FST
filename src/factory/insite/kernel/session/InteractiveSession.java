package factory.insite.kernel.session;

import factory.insite.kernel.security.User;
import factory.insite.server.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedReader;
import java.util.Scanner;

public class InteractiveSession {

    private final Scanner in;
    private final OutputStream out;
    private final User user;

    public InteractiveSession(Scanner in, OutputStream out, User user) {
        this.in = in;
        this.out = out;
        this.user = user;
    }
    public void start(Configuration configuration){
        while (true){
            try {
                out.write("$ ".getBytes());
                String cmd = in.nextLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
