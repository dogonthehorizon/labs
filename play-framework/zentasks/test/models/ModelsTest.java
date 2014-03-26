package models;

import com.avaje.ebean.Ebean;
import org.junit.*;
import static org.junit.Assert.*;
import play.libs.Yaml;
import play.test.WithApplication;
import java.util.*;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
    }

    @Test
    public void createAndRetrieveUser() {
        new User("bob@gmail.com", "Bob", "secret").save();
        User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }

    @Test
    public void tryAuthenticateUser() {
        new User("bob@gmail.com", "Bob", "secret").save();

        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "lolapalooza"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }

    @Test
    public void findProjectsInvolving() {
        new User("bob@gmail.com", "Bob", "secret").save();
        new User("alice@gmail.com", "Alice", "secret").save();

        Project.create("Foo", "foo", "bob@gmail.com");
        Project.create("Bar", "foo", "alice@gmail.com");

        List<Project> results = Project.findInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Foo", results.get(0).name);

    }

    @Test
    public void findTodoTasksInvolving() {
        User bob = new User("bob@gmail.com", "Bob", "secret");
        bob.save();

        Project project = Project.create("Foo", "foo", "bob@gmail.com");
        Task makeTodoApp = new Task();
        makeTodoApp.title = "Make a todo application!";
        makeTodoApp.assignedTo = bob;
        makeTodoApp.done = true;
        makeTodoApp.save();

        Task releaseNextVersion = new Task();
        releaseNextVersion.title = "Release the next version of this app.";
        releaseNextVersion.project = project;
        releaseNextVersion.save();

        List<Task> results = Task.findTodoInvolving("bob@gmail.com");
        assertEquals(1, results.size());
        assertEquals("Release the next version of this app.", results.get(0).title);
    }

    @Test
    public void fullTest() {
        Ebean.save((List) Yaml.load("test-data.yml"));

        // Count things
        assertEquals(3, User.find.findRowCount());
        assertEquals(7, Project.find.findRowCount());
        assertEquals(5, Task.find.findRowCount());

        // Try to auth users
        assertNotNull(User.authenticate("bob@example.com", "secret"));
        assertNotNull(User.authenticate("jane@example.com", "secret"));

        assertNull(User.authenticate("jeff@example.com", "badpassword"));
        assertNull(User.authenticate("tom@example.com", "secret"));

        // Find all Bob's projects
        List<Project> bobsProjects = Project.findInvolving("bob@example.com");
        assertEquals(5, bobsProjects.size());

        // Find all Bob's todo tasks
        List<Task> bobsTasks = Task.findTodoInvolving("bob@example.com");
        assertEquals(4, bobsTasks.size());
    }
}