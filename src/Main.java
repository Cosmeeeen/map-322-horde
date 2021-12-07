import domain.Friendship;
import domain.User;
import repository.Repository;
import repository.UtilizatorDbRepository;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        User user = new User("vlad01", "Luca", "Vlad");
        System.out.println(user);
        Repository<String, User> repo = new UtilizatorDbRepository("jdbc:postgresql://localhost:5432/proiectDatabase", "postgres", "user");
        //repo.save(user);
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Friendship f = new Friendship("vlad01","adi",date, "accepted");
        System.out.println(f);
    }
}
