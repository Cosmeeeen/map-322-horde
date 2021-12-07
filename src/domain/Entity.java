package domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable {

    private static final long serialVersionUID = 7331115341259248461L;
    private ID username;
    public ID getUsername() {
        return username;
    }
    public void setUsername(ID username) {
        this.username = username;
    }
}