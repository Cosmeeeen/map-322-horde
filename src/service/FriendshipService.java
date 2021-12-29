package service;

import domain.Friendship;
import domain.User;
import repository.Repository;

import java.util.*;

public class FriendshipService {
    private Repository<Long, User> userDbRepository;
    private Repository<Long, Friendship> repo;

    public FriendshipService(Repository<Long, User> userDbRepository, Repository<Long, Friendship> repo) {
        this.repo = repo;
        this.userDbRepository = userDbRepository;
    }

    public void addFriendship(String username1, String username2) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Friendship friendship = new Friendship(username1, username2, date);
        repo.save(friendship);
    }

    public void deleteFriendship(String username1, String username2) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Friendship friendship = new Friendship(username1, username2, date);
        for(Friendship f : repo.findAll()) {
            if (((Objects.equals(f.getFriend1(), friendship.getFriend1())) && (Objects.equals(f.getFriend2(), friendship.getFriend2()))) ||
                    (Objects.equals(f.getFriend1(), friendship.getFriend2())) && (Objects.equals(f.getFriend2(), friendship.getFriend1()))) {
                repo.delete(f.getID());
            }
        }
    }


    public Iterable<Friendship> getAll() {
        return repo.findAll();
    }

    public Friendship findOne(Long id){return repo.findOne(id);}

    public Iterable<Friendship> findOnesFriendships(String username){
        Set<Friendship> friendships = new HashSet<>();
        Iterable<Friendship> all = repo.findAll();
        for(Friendship f: all){
            if(Objects.equals(f.getFriend1(), username) || Objects.equals(f.getFriend2(), username))
                friendships.add(f);
        }
        return friendships;
    }

    public int sizeOfIterable(Iterable<Friendship> friendships){
        int count = 0;
        for(Friendship f : friendships)
            count++;
        return count;
    }
}
