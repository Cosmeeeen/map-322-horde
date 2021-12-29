import domain.Friendship;
import domain.FriendshipRequest;
import domain.User;
import repository.FriendshipDbRepository;
import repository.FriendshipRequestDbRepository;
import repository.Repository;
import repository.UtilizatorDbRepository;
import service.FriendshipRequestService;
import service.FriendshipService;
import service.UserService;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        User user = new User("vlad012", "Luca", "Vlad");
        // System.out.println(user);
        Repository<Long, User> repo = new UtilizatorDbRepository("jdbc:postgresql://localhost:5432/proiectDatabase", "postgres", "user");
        // /// repo.delete(3L);
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Friendship f = new Friendship("vlad01","adi",date);
        System.out.println(f);
        Repository<Long, Friendship> repof = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/proiectDatabase", "postgres", "user");
        // repof.save(f);
        FriendshipRequest fr = new FriendshipRequest("vlad01","adi",date, "pending");
        System.out.println(fr);
        Repository<Long, FriendshipRequest> repofr = new FriendshipRequestDbRepository("jdbc:postgresql://localhost:5432/proiectDatabase", "postgres", "user");
        //repofr.delete(1L);
        FriendshipService friendshipService = new FriendshipService(repo, repof);
        UserService userService = new UserService(repo,friendshipService);
        //userService.deleteUser("vlad01");
        friendshipService.deleteFriendship("ada", "gabi");
        FriendshipRequestService friendshipRequestService = new FriendshipRequestService(repo, repofr, friendshipService);
        //friendshipRequestService.sendFriendshipRequest("ada1", "gabi");
        //friendshipRequestService.updateFriendshipRequestStatus("ada1", "gabi", "declined");
        Console console = new Console(userService, friendshipService, friendshipRequestService);
        console.runMenu();

    }
}
