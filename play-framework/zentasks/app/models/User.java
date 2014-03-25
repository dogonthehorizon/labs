package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class User extends Model {
// @Entity marks this class as a managed Ebean entity.
// Provides a set of JPA helpers

    // Apparently every JPA must provide an @Id annotation
    // in this case we will use e-mail as the id.
    @Id
    public String email;
    public String name;
    public String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // Our main query endpoint to the database.
    public static Finder<String,User> find = new Finder<>(String.class, User.class);

    public static User authenticate(String email, String password) {
        return find.where().eq("email", email)
                           .eq("password", password)
                           .findUnique();
    }
}
