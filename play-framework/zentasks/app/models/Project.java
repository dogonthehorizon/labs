package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class Project extends Model {

    @Id
    public Long id;
    public String name;
    public String folder;

    // This ManyToMany relationship declares that a user
    // can be a member of many projects, and each project
    // can have many users.
    @ManyToMany(cascade = CascadeType.REMOVE)
    public List<User> members = new ArrayList<>();

    public Project(String name, String folder, User owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
    }

    public static Finder<Long, Project> find = new Finder<>(Long.class, Project.class);

    public static Project create(String name, String folder, String owner) {
        Project project = new Project(name, folder, User.find.ref(owner));
        project.save();
        // This ManyToMany association must be saved explicitly
        project.saveManyToManyAssociations("members");

        // Note that by *not* explicitly generating an ID, the database will
        // create one for us.

        return project;
    }

    public static List<Project> findInvolving(String user) {
        return find.where().eq("members.email", user)
                           .findList();
    }
}
