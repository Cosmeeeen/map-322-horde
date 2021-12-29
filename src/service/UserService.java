package service;

import domain.Friendship;
import domain.User;
import repository.Repository;

import java.util.Objects;


public class UserService {
    private Repository<Long, User> repo;
    private FriendshipService friendshipService;

    public UserService(Repository<Long, User> repo, FriendshipService friendshipService) {
        this.repo = repo;
        this.friendshipService = friendshipService;
    }

    public User addUser(User user) {
        return repo.save(user);
    }

    public Iterable<User> getAll() {
        return repo.findAll();
    }

    public User deleteUser(String username) {

        Iterable<Friendship> friendships = friendshipService.findOnesFriendships(username);
        if (friendshipService.sizeOfIterable(friendships) > 0) {
            for (Friendship f : friendships) {
                friendshipService.deleteFriendship(f.getFriend1(), f.getFriend2());
            }
        }
        for (User u : repo.findAll()) {
            if (Objects.equals(u.getUsername(), username)) {
                repo.delete(u.getID());
            }
        }
        return null;
    }

    public User findOne(Long id) {
        return repo.findOne(id);
    }

    public User findUserAfterUsername(String username){
        for(User u : repo.findAll()){
            if(Objects.equals(u.getUsername(),username)){
                return u;
            }
        }
        return null;
    }
}