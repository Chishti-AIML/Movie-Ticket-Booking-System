import java.util.Scanner;

class Movie {
    String name;
    double price;

    Movie(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void displayMovie() {
        System.out.println(name + " - Rs." + price);
    }
}

class Booking {
    String customerName;
    String movieName;
    int row;
    int seat;
    double price;

    Booking(String customerName, String movieName, int row, int seat, double price) {
        this.customerName = customerName;
        this.movieName = movieName;
        this.row = row;
        this.seat = seat;
        this.price = price;
    }

    void displayBooking() {
        System.out.println("\n===== BOOKING DETAILS =====");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Movie         : " + movieName);
        System.out.println("Row           : " + row);
        System.out.println("Seat          : " + seat);
        System.out.println("Ticket Price  : Rs." + price);
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    static char[][] seats = new char[4][5];

    static Movie[] movies = {
            new Movie("Avengers", 200),
            new Movie("Interstellar", 250),
            new Movie("Inception", 220)
    };

    static Booking booking = null;

    static void initializeSeats() {

        for (int i = 0; i < seats.length; i++) {

            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 'O';
            }
        }
    }
    static void displayMovies() {

        System.out.println("\n===== AVAILABLE MOVIES =====");

        for (int i = 0; i < movies.length; i++) {

            System.out.print((i + 1) + ". ");
            movies[i].displayMovie();
        }
    }

    
    static void displaySeats() {

        System.out.println("\n===== SEAT LAYOUT =====");
        System.out.println("O = Available");
        System.out.println("X = Booked\n");

        System.out.print("     ");

        for (int j = 1; j <= seats[0].length; j++) {
            System.out.print(j + " ");
        }

        System.out.println();

        for (int i = 0; i < seats.length; i++) {

            System.out.print("Row " + (i + 1) + " ");

            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }

            System.out.println();
        }
    }

    
    static void bookTicket() {

        if (booking != null) {
            System.out.println("\nYou already have a booking.");
            System.out.println("Cancel your current booking first.");
            return;
        }

        sc.nextLine();

        System.out.print("\nEnter your name: ");
        String customerName = sc.nextLine();

        displayMovies();

        System.out.print("\nChoose movie: ");
        int movieChoice = sc.nextInt();

        if (movieChoice < 1 || movieChoice > movies.length) {
            System.out.println("Invalid movie choice.");
            return;
        }

        displaySeats();

        System.out.print("\nEnter row number (1-4): ");
        int row = sc.nextInt();

        System.out.print("Enter seat number (1-5): ");
        int seat = sc.nextInt();

        
        if (row < 1 || row > 4 || seat < 1 || seat > 5) {
            System.out.println("Invalid seat number.");
            return;
        }

        
        if (seats[row - 1][seat - 1] == 'X') {
            System.out.println("Sorry! This seat is already booked.");
            return;
        }

        seats[row - 1][seat - 1] = 'X';

        Movie selectedMovie = movies[movieChoice - 1];

        booking = new Booking(
                customerName,
                selectedMovie.name,
                row,
                seat,
                selectedMovie.price
        );

        System.out.println("\nTicket booked successfully!");
        System.out.println("Total Amount: Rs." + selectedMovie.price);
    }

    
    static void cancelBooking() {

        if (booking == null) {
            System.out.println("\nNo booking found.");
            return;
        }

        seats[booking.row - 1][booking.seat - 1] = 'O';

        System.out.println("\nBooking cancelled successfully!");

        booking = null;
    }

   
    static void viewBooking() {

        if (booking == null) {
            System.out.println("\nNo booking found.");
        } else {
            booking.displayBooking();
        }
    }

    public static void main(String[] args) {

        initializeSeats();

        while (true) {

            System.out.println("\n================================");
            System.out.println("   MOVIE TICKET BOOKING SYSTEM");
            System.out.println("================================");

            System.out.println("1. View Movies");
            System.out.println("2. View Seats");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View Booking");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayMovies();
                    break;

                case 2:
                    displaySeats();
                    break;

                case 3:
                    bookTicket();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    viewBooking();
                    break;

                case 6:
                    System.out.println("\nThank you for using Movie Ticket Booking System!");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }
}