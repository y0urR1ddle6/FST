package factory.insite.kernel.session;


import factory.insite.kernel.security.Permissions;
import factory.insite.kernel.security.User;
import factory.insite.server.Configuration;
import factory.insite.server.io.DirectoryHelper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public enum Commands {
    get((arg,user,in,out) -> {
        if(!user.getPermissions().contains(Permissions.READ))
            return;
        // next in TODO list
    }),
    put((arg,user,in,out) -> {
        if(!user.getPermissions().contains(Permissions.WRITE))
            return;
        // next in TODO list
    }),
    ls((arg,user,in,out) -> {
        if(!user.getPermissions().contains(Permissions.READ)){
            out.write(("User " + user.getAuth().getLogin() + " doesn't have READ privilege").getBytes());
        }
        DirectoryHelper.listDirectory(new File(Configuration.getInstance().getPwd()),out);
    })
    ;

    // TODO
    //  1. add help cmd
    //  2. create description for commands

    private Command command;
    public String description;

    Commands(Command command){
        this.command = command;
    }

    public void run(String args, User user, Scanner in, OutputStream out){
        try {
            command.run(args,user,in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
