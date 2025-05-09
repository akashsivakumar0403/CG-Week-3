import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean available;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.available = available;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    Book head = null;
    Book tail = null;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean available) {
        Book newBook = new Book(title, author, genre, bookId, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        System.out.println("Book added at beginning.");
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean available) {
        Book newBook = new Book(title, author, genre, bookId, available);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        System.out.println("Book added at end.");
    }

    public void addAtPosition(int position, String title, String author, String genre, int bookId, boolean available) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addAtBeginning(title, author, genre, bookId, available);
            return;
        }

        Book temp = head;
        int count = 1;
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null || temp == tail) {
            addAtEnd(title, author, genre, bookId, available);
        } else {
            Book newBook = new Book(title, author, genre, bookId, available);
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
            System.out.println("Book added at position " + position);
        }
    }

    public void removeById(int bookId) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book ID not found.");
            return;
        }

        if (temp == head && temp == tail) {
            head = tail = null;
        } else if (temp == head) {
            head = head.next;
            head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        System.out.println("Book removed.");
    }

    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book title not found.");
    }

    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Author not found.");
    }

    public void updateAvailability(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.available = status;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }

    public void displayForward() {
        Book temp = head;
        if (temp == null) {
            System.out.println("Library is empty.");
            return;
        }
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Book temp = tail;
        if (temp == null) {
            System.out.println("Library is empty.");
            return;
        }
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books: " + count);
    }

    private void printBook(Book b) {
        System.out.println("----------------------------");
        System.out.println("Title: " + b.title);
        System.out.println("Author: " + b.author);
        System.out.println("Genre: " + b.genre);
        System.out.println("Book ID: " + b.bookId);
        System.out.println("Available: " + (b.available ? "Yes" : "No"));
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n=== Library Management ===");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display Books (Forward)");
            System.out.println("9. Display Books (Reverse)");
            System.out.println("10. Count Books");
            System.out.println("11. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBook(sc, library, "beginning");
                    break;
                case 2:
                    addBook(sc, library, "end");
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    addBookAtPosition(sc, library, pos);
                    break;
                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    int id = sc.nextInt();
                    library.removeById(id);
                    break;
                case 5:
                    System.out.print("Enter Book Title to search: ");
                    String title = sc.nextLine();
                    library.searchByTitle(title);
                    break;
                case 6:
                    System.out.print("Enter Author Name to search: ");
                    String author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;
                case 7:
                    System.out.print("Enter Book ID to update: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter availability (true/false): ");
                    boolean status = sc.nextBoolean();
                    library.updateAvailability(bookId, status);
                    break;
                case 8:
                    library.displayForward();
                    break;
                case 9:
                    library.displayReverse();
                    break;
                case 10:
                    library.countBooks();
                    break;
                case 11:
                    System.out.println("Exiting Library System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 11);

        sc.close();
    }

    private static void addBook(Scanner sc, Library library, String where) {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Book ID: ");
        int id = sc.nextInt();
        System.out.print("Available (true/false): ");
        boolean avail = sc.nextBoolean();
        sc.nextLine();

        if (where.equals("beginning"))
            library.addAtBeginning(title, author, genre, id, avail);
        else
            library.addAtEnd(title, author, genre, id, avail);
    }

    private static void addBookAtPosition(Scanner sc, Library library, int pos) {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Book ID: ");
        int id = sc.nextInt();
        System.out.print("Available (true/false): ");
        boolean avail = sc.nextBoolean();
        sc.nextLine();
        library.addAtPosition(pos, title, author, genre, id, avail);
    }
}
