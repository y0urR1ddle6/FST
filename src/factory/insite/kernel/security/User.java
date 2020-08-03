package factory.insite.kernel.security;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Auth auth;
    private final List<Permissions> permissions;

    public User(Auth auth){
        permissions = new ArrayList<>();
        this.auth = auth;
    }
    public List<Permissions> getPermissions() {
        return permissions;
    }
    public void addPermission(Permissions permission){
        permissions.add(permission);
    }
    public Auth getAuth() {
        return auth;
    }
}
