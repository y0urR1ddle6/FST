package factory.insite.kernel.session;

import factory.insite.kernel.security.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

@FunctionalInterface
public interface Command {
    void run(String arg, User user, Scanner in, OutputStream out) throws IOException;
}
