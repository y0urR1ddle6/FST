package factory.insite.kernel.session;

import factory.insite.kernel.security.User;
import factory.insite.server.Configuration;

import java.io.IOException;
import java.io.OutputStream;
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
                String parseMe = in.nextLine();
                runCommand(parseMe);
            } catch (Exception e) {
                return;
            }
        }
    }

    private void runCommand(String parseMe){
        String cmd = parseMe.split(" ")[0];
        for(Commands commandType : Commands.values()){
            if(commandType.name().toLowerCase().equals(cmd)){
                commandType.run(parseMe.replaceAll(cmd + " ",""),user,in,out);
                return;
            }
        }
        try {
            out.write(("Command: " + cmd + " not found.\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
