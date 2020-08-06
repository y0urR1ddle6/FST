package factory.insite.server;

import factory.insite.kernel.security.Auth;
import factory.insite.kernel.security.Permissions;
import factory.insite.kernel.security.User;

import java.util.*;

public final class Configuration {

    // USERS
    List<User> users = new ArrayList<>();
    private void fillUsers(){
        /* create users with class Auth(<Username>,<Password encoded by sha256>)
                 1. use STRONG PASSWORD!!!
                 2. remove default user (or change password)
           add Permissions (check kernel/security//Permissions.java)
         */
        User user1 = new User(
                new Auth("admin","8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"));
        user1.addPermission(Permissions.READ);
        user1.addPermission(Permissions.WRITE);

        // add users into array
        users.add(user1);
    }
    // OPTIONS
        // server listening port
        private final int port = 21;
        // files location
        private final String pwd = "C:\\Users\\vhatv\\Desktop\\fst";
        // max connections count
        private final int backlog = 2;
        // 0.0.0.0 or 127.0.0.1
        private final String host = "127.0.0.1";
        // pack size
        private final int packSize = 64;
        // set true for anonymous access
        private final boolean anonymousAccess = false;
        // send hash with pack (true for safe transport)
        private final boolean hashCheck = true;
        // set true for more information
        private final boolean verbose = true;
    // OPTIONS

    private static Configuration configuration;
    private final String version = "0.1";

    private Configuration(){
        fillUsers();
        if(anonymousAccess)
            addAnonymousUser();
    }
    private void addAnonymousUser(){
        User anonymous = new User(
                // anonymous:anonymous
                new Auth("anonymous","2f183a4e64493af3f377f745eda502363cd3e7ef6e4d266d444758de0a85fcc8")
        );
        anonymous.addPermission(Permissions.READ);
        users.add(anonymous);
    }
    public static Configuration getInstance(){
        if(configuration == null)
            configuration = new Configuration();
        return configuration;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public int getPackSize() {
        return packSize;
    }

    public boolean shouldCheckHash() {
        return hashCheck;
    }

    public String getVersion() {
        return version;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public int getBacklog() {
        return backlog;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getPwd() {
        return pwd;
    }
}
