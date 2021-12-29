package service;

import domain.Friendship;
import domain.FriendshipRequest;
import domain.User;
import repository.Repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FriendshipRequestService {
    private Repository<Long, User> userDbRepository;
    private Repository<Long, FriendshipRequest> repo;
    private FriendshipService friendshipService;

    public FriendshipRequestService(Repository<Long, User> userDbRepository, Repository<Long, FriendshipRequest> repo, FriendshipService friendshipService) {
        this.repo = repo;
        this.userDbRepository = userDbRepository;
        this.friendshipService = friendshipService;
    }

    public void sendFriendshipRequest(String username1, String username2) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        FriendshipRequest friendship = new FriendshipRequest(username1, username2, date, "pending");
        repo.save(friendship);
    }

    public void updateFriendshipRequestStatus(String username1, String username2, String status) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        FriendshipRequest friendship = new FriendshipRequest(username1, username2, date, status);
        repo.update(friendship);
        if(Objects.equals(status, "accepted")){
            friendshipService.addFriendship(username1,username2);
        }

    }

    public Iterable<FriendshipRequest> getAll() {
        return repo.findAll();
    }

    public FriendshipRequest findOne(Long id){return repo.findOne(id);}

    public Iterable<FriendshipRequest> findOnesFriendshipRequests(String username){
        Set<FriendshipRequest> friendships = new HashSet<>();
        Iterable<FriendshipRequest> all = repo.findAll();
        for(FriendshipRequest f: all){
            if(Objects.equals(f.getFriend1(), username) || Objects.equals(f.getFriend2(), username))
                friendships.add(f);
        }
        return friendships;
    }

    public int sizeOfIterable(Iterable<FriendshipRequest> friendships){
        int count = 0;
        for(FriendshipRequest f : friendships)
            count++;
        return count;
    }
}
