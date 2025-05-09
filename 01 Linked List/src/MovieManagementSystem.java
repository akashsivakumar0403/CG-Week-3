import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieDoublyLinkedList {
    Movie head;
    Movie tail;

    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
        System.out.println("Movie added at beginning.");
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
        System.out.println("Movie added at end.");
    }

    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newMovie.next = temp.next;
            newMovie.prev = temp;
            temp.next.prev = newMovie;
            temp.next = newMovie;
            System.out.println("Movie added at position " + position);
        }
    }

    public void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie titled '" + title + "' not found.");
            return;
        }

        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        System.out.println("Movie titled '" + title + "' removed.");
    }

    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found by Director '" + director + "'.");
        }
    }

    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                printMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found with Rating " + rating);
        }
    }

    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated for movie '" + title + "'.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie titled '" + title + "' not found.");
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("No movies to display.");
            return;
        }

        System.out.println("Movies (Forward):");
        Movie temp = head;
        while (temp != null) {
            printMovie(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies to display.");
            return;
        }

        System.out.println("Movies (Reverse):");
        Movie temp = tail;
        while (temp != null) {
            printMovie(temp);
            temp = temp.prev;
        }
    }

    private void printMovie(Movie m) {
        System.out.println("-----------------------------------");
        System.out.println("Title: " + m.title);
        System.out.println("Director: " + m.director);
        System.out.println("Year: " + m.year);
        System.out.println("Rating: " + m.rating);
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieDoublyLinkedList list = new MovieDoublyLinkedList();
        int choice;

        do {
            System.out.println("\n=== Movie Management System ===");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Specific Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search Movie by Director");
            System.out.println("6. Search Movie by Rating");
            System.out.println("7. Update Movie Rating by Title");
            System.out.println("8. Display Movies (Forward)");
            System.out.println("9. Display Movies (Reverse)");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addMovie(sc, list, "beginning");
                    break;
                case 2:
                    addMovie(sc, list, "end");
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int position = sc.nextInt();
                    sc.nextLine();
                    addMovieAtPosition(sc, list, position);
                    break;
                case 4:
                    System.out.print("Enter Movie Title to Remove: ");
                    String delTitle = sc.nextLine();
                    list.removeByTitle(delTitle);
                    break;
                case 5:
                    System.out.print("Enter Director Name: ");
                    String director = sc.nextLine();
                    list.searchByDirector(director);
                    break;
                case 6:
                    System.out.print("Enter Rating: ");
                    double rating = sc.nextDouble();
                    sc.nextLine();
                    list.searchByRating(rating);
                    break;
                case 7:
                    System.out.print("Enter Movie Title to Update Rating: ");
                    String title = sc.nextLine();
                    System.out.print("Enter New Rating: ");
                    double newRating = sc.nextDouble();
                    list.updateRating(title, newRating);
                    break;
                case 8:
                    list.displayForward();
                    break;
                case 9:
                    list.displayReverse();
                    break;
                case 10:
                    System.out.println("Closing system.");
                    break;
                default:
                    System.out.println("Select an existing option.");
            }

        } while (choice != 10);

        sc.close();
    }

    private static void addMovie(Scanner sc, MovieDoublyLinkedList list, String where) {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Director: ");
        String director = sc.nextLine();
        System.out.print("Enter Year of Release: ");
        int year = sc.nextInt();
        System.out.print("Enter Rating: ");
        double rating = sc.nextDouble();
        sc.nextLine();

        if (where.equals("beginning")) list.addAtBeginning(title, director, year, rating);
        else list.addAtEnd(title, director, year, rating);
    }

    private static void addMovieAtPosition(Scanner sc, MovieDoublyLinkedList list, int position) {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Director: ");
        String director = sc.nextLine();
        System.out.print("Enter Year of Release: ");
        int year = sc.nextInt();
        System.out.print("Enter Rating: ");
        double rating = sc.nextDouble();
        sc.nextLine();

        list.addAtPosition(position, title, director, year, rating);
    }
}
