package ui;

import domain.Friendship;
import domain.FriendshipRequest;
import domain.User;
import service.FriendshipRequestService;
import service.FriendshipService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Console {
    private UserService userService;
    private FriendshipService friendshipService;
    private FriendshipRequestService friendshipRequestService;


    public Console(UserService userService, FriendshipService friendshipService, FriendshipRequestService friendshipRequestService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.friendshipRequestService = friendshipRequestService;
    }

    public void menu() {
        System.out.println("1. User menu.");
        System.out.println("2. Friendship menu.");
        System.out.println("3. Friendship request menu.");
        System.out.println("4. Exit.");
    }

    public void userMenu() {
        System.out.println("1. Add user.");
        System.out.println("2. Delete user.");
        System.out.println("3. Print all users.");
        System.out.println("4. Back.");
    }

    public void friendshipMenu() {
        System.out.println("1. Add friendship.");
        System.out.println("2. Delete friendship.");
        System.out.println("3. Print all friendships.");
        System.out.println("4. Show all friendships of an user.");
        System.out.println("5. Show all friendships of an user from a certain month.");
        System.out.println("6. Back.");
    }

    public void friendshipRequestMenu() {
        System.out.println("1. Send friendship request.");
        System.out.println("2. Update friendship request.");
        System.out.println("3. Print all friendship requests.");
        System.out.println("4. Back.");
    }

    public void uiAddUser() {
        try {
            Scanner input = new Scanner(System.in);


            System.out.println("Enter username : ");
            String username = input.next();
            System.out.println("Enter user first name : ");
            String firstName = input.next();
            System.out.println("Enter user last name : ");
            String lastName = input.next();
            User user = new User(username, firstName, lastName);
            userService.addUser(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void uiDeleteUser() {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter username of user to delete : ");
            String username = input.next();
            userService.deleteUser(username);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllUsers() {
        for (User u : userService.getAll()) {
            System.out.println(u);
        }
    }

    public void uiAddFriendship() {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter first friend username : ");
            String username1 = input.next();
            System.out.println("Enter second friend username : ");
            String username2 = input.next();
            friendshipService.addFriendship(username1, username2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void uiDeleteFriendship() {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Delete friendship between" + "\n" + " username: ");
            String username1 = input.next();
            System.out.println("username: ");
            String username2 = input.next();
            friendshipService.deleteFriendship(username1, username2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllFriendships() {
        for (Friendship f : friendshipService.getAll()) {
            System.out.println(f);
        }
    }


    private void uisendFrienshipRequest() {
        Scanner input = new Scanner(System.in);

        System.out.println("Send friendship request from" + "\n" + " username: ");
        String username1 = input.next();
        System.out.println("to username: ");
        String username2 = input.next();
        friendshipRequestService.sendFriendshipRequest(username1, username2);
    }

    private void uiupdateFrienshipRequest() {
        Scanner input = new Scanner(System.in);

        System.out.println("Update friendship request status from" + "\n" + " username: ");
        String username1 = input.next();
        System.out.println("to username: ");
        String username2 = input.next();
        System.out.println("status(accepted/declined): ");
        String status = input.next();
        friendshipRequestService.updateFriendshipRequestStatus(username1, username2, status);
    }

    public void printAllFriendshipRequests() {
        for(FriendshipRequest fr : friendshipRequestService.getAll()){
            System.out.println(fr);
        }
    }

    public void showAllFriendshipsOfAnUser(){
        Scanner input = new Scanner(System.in);

        System.out.println("Show all friendships of an user ");
        System.out.println("First Name: ");
        String firstName = input.next();
        System.out.println("Last Name: ");
        String lastName = input.next();

        List<Friendship> friendships = new ArrayList<>();
        for(Friendship f : friendshipService.getAll()){
            friendships.add(f);
        }
        friendships.stream()
                .filter(x -> {
                    return ((Objects.equals(userService.findUserAfterUsername(x.getFriend1()).getFirstName(),firstName) &&
                            Objects.equals(userService.findUserAfterUsername(x.getFriend1()).getLastName(), lastName)));
                })
                .forEach(x->{
                    System.out.println(userService.findUserAfterUsername(x.getFriend2()).getFirstName() + " | " +
                            userService.findUserAfterUsername(x.getFriend2()).getLastName() + " | " + x.getFriendshipDate());
                });

        friendships.stream()
                .filter(x -> {
                    return ((Objects.equals(userService.findUserAfterUsername(x.getFriend2()).getFirstName(),firstName) &&
                            Objects.equals(userService.findUserAfterUsername(x.getFriend2()).getLastName(), lastName)));
                })
                .forEach(x->{
                    System.out.println(userService.findUserAfterUsername(x.getFriend1()).getFirstName() + " | " +
                            userService.findUserAfterUsername(x.getFriend1()).getLastName() + " | " + x.getFriendshipDate());
                });
    }

    public void showAllFriendshipsOfAnUserInACertainMonth(){

        Scanner input = new Scanner(System.in);

        System.out.println("Show all friendships of an user ");
        System.out.println("First Name: ");
        String firstName = input.next();
        System.out.println("Last Name: ");
        String lastName = input.next();
        System.out.println("Month: ");
        int month = input.nextInt();

        List<Friendship> friendships = new ArrayList<>();
        for(Friendship f : friendshipService.getAll()){
            friendships.add(f);
        }
        friendships.stream()
                .filter(x -> {
                    return ((Objects.equals(userService.findUserAfterUsername(x.getFriend1()).getFirstName(),firstName) &&
                            Objects.equals(userService.findUserAfterUsername(x.getFriend1()).getLastName(), lastName) &&
                            Objects.equals(x.getFriendshipDate().getMonth(),(month-1))));
                })
                .forEach(x->{
                    System.out.println(userService.findUserAfterUsername(x.getFriend2()).getFirstName() + " | " +
                            userService.findUserAfterUsername(x.getFriend2()).getLastName() + " | " + x.getFriendshipDate());
                });

        friendships.stream()
                .filter(x -> {
                    return ((Objects.equals(userService.findUserAfterUsername(x.getFriend2()).getFirstName(),firstName) &&
                            Objects.equals(userService.findUserAfterUsername(x.getFriend2()).getLastName(), lastName) &&
                            Objects.equals(x.getFriendshipDate().getMonth(),(month-1))));
                })
                .forEach(x->{
                    System.out.println(userService.findUserAfterUsername(x.getFriend1()).getFirstName() + " | " +
                            userService.findUserAfterUsername(x.getFriend1()).getLastName() + " | " + x.getFriendshipDate());
                });
    }

    public void runFriendshipMenu() {
        boolean running = true;
        while (running) {
            friendshipMenu();
            Scanner input = new Scanner(System.in);

            System.out.println("Enter option : ");
            String option = input.next();
            switch (option) {
                case "1" -> uiAddFriendship();

                case "2" -> uiDeleteFriendship();

                case "3" -> printAllFriendships();

                case "4" -> showAllFriendshipsOfAnUser();

                case "5" -> showAllFriendshipsOfAnUserInACertainMonth();

                case "6" -> running = false;
            }
        }
    }

    public void runUserMenu() {
        boolean running = true;
        while (running) {
            userMenu();
            Scanner input = new Scanner(System.in);

            System.out.println("Enter option : ");
            String option = input.next();
            switch (option) {
                case "1" -> uiAddUser();

                case "2" -> uiDeleteUser();

                case "3" -> printAllUsers();

                case "4" -> running = false;
            }
        }
    }

    private void runFriendshipRequestMenu() {
        boolean running = true;
        while (running) {
            friendshipRequestMenu();
            Scanner input = new Scanner(System.in);

            System.out.println("Enter option : ");
            String option = input.next();
            switch (option) {
                case "1" -> uisendFrienshipRequest();

                case "2" -> uiupdateFrienshipRequest();

                case "3" -> printAllFriendshipRequests();

                case "4" -> running = false;
            }

        }
    }

    public void runMenu() {
        boolean running = true;
        while (running) {
            menu();
            Scanner input = new Scanner(System.in);

            System.out.println("Enter option : ");
            String option = input.next();
            switch (option) {
                case "1" -> runUserMenu();

                case "2" -> runFriendshipMenu();

                case "3" -> runFriendshipRequestMenu();

                case "4" -> running = false;

            }
        }
    }

}