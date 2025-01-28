
import java.util.*;
 class Train{
    String name;
    String routeFrom;
    String routeTo;
    int availableSeats;

    public Train(String name, String routeFrom, String routeTo, int availableSeats){
        this.name = name;
        this.routeFrom = routeFrom;
        this.routeTo = routeTo;
        this.availableSeats = availableSeats;
    }
}

class User{
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

public class railway{
    private static List<Train> trains = new ArrayList<>();
    private static Map<String,User> users = new HashMap<>();
    private static Map<String, String> bookedSeats = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeTrains();
        initializeUsers();
        mainMenu();
    }

    private static void initializeTrains() {
        trains.add(new Train("Train1", "CityA", "CityB", 100));
        trains.add(new Train("Train2", "CityA", "CityB", 100));
        trains.add(new Train("Train3", "CityA", "CityB", 100));
        trains.add(new Train("Train4", "CityA", "CityB", 100));
        trains.add(new Train("Train5", "CityA", "CityB", 100));
    }

    private static void initializeUsers() {
        users.put("user1", new User("siri", "1234", "siri@.com"));
        users.put("user2", new User("jyothika", "5678", "jyothika@.com"));
    }

    private static void mainMenu() {
        System.out.println("Welcome to Railway Reservation System");
        System.out.println("1. Login");
        System.out.println("2. Forgot Password");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> login();
            case 2 -> forgotPassword();
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Invalid choice! Try again.");
                mainMenu();
            }
        }
    }

    private static void login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("Login Successful!");
            bookingMenu(username);
        } else {
            System.out.println("Invalid Username or Password. Try Again.");
            mainMenu();
        }
    }

    private static void forgotPassword() {
        System.out.print("Enter your Username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Your Password: " + users.get(username).password);
        } else {
            System.out.println("User not found.");
        }
        mainMenu();
    }

    private static void bookingMenu(String username) {
        System.out.println("Available Trains:");
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            System.out.println((i + 1) + ". " + train.name + " (" + train.routeFrom + " to " + train.routeTo + ") - Seats Available: " + train.availableSeats);
        }

        System.out.print("Enter the train number to book: ");
        int trainChoice = scanner.nextInt();
        scanner.nextLine();

        if (trainChoice < 1 || trainChoice > trains.size()) {
            System.out.println("Invalid Train Choice! Try again.");
            bookingMenu(username);
            return;
        }

        Train selectedTrain = trains.get(trainChoice - 1);
        if (selectedTrain.availableSeats > 0) {
            selectedTrain.availableSeats--;
            bookedSeats.put(username, selectedTrain.name);
            System.out.println("Booking Confirmed on " + selectedTrain.name);
        } else {
            System.out.println("Seats are not available for " + selectedTrain.name);
            showAvailableTrains(selectedTrain.routeFrom, selectedTrain.routeTo);
        }
        mainMenu();
    }

    private static void showAvailableTrains(String routeFrom, String routeTo) {
        System.out.println("Available trains on the same route:");
        for (Train train : trains) {
            if (train.routeFrom.equals(routeFrom) && train.routeTo.equals(routeTo) && train.availableSeats > 0) {
                System.out.println(train.name + " - Seats Available: " + train.availableSeats);
                
            }
        }
    }
}
