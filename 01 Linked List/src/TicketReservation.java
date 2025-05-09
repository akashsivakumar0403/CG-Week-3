import java.util.Scanner;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    Ticket head = null;
    Ticket tail = null;
    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head; // Circular link
        }
        System.out.println("Ticket booked successfully.");
    }
    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        Ticket previous = null;
        do {
            if (current.ticketID == ticketID) {
                if (previous == null) {
                    if (head == tail) {
                        head = null;
                        tail = null;
                    } else {
                        tail.next = current.next;
                        head = current.next;
                    }
                } else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                }
                System.out.println("Ticket with ID " + ticketID + " removed.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket with ID " + ticketID + " not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketID);
            System.out.println("Customer Name: " + current.customerName);
            System.out.println("Movie Name: " + current.movieName);
            System.out.println("Seat Number: " + current.seatNumber);
            System.out.println("Booking Time: " + current.bookingTime);
            System.out.println("----------------------------");
            current = current.next;
        } while (current != head);
    }
    public void searchTicket(String searchValue) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        boolean found = false;
        do {
            if (current.customerName.equalsIgnoreCase(searchValue) || current.movieName.equalsIgnoreCase(searchValue)) {
                System.out.println("Ticket Found:");
                System.out.println("Ticket ID: " + current.ticketID);
                System.out.println("Customer Name: " + current.customerName);
                System.out.println("Movie Name: " + current.movieName);
                System.out.println("Seat Number: " + current.seatNumber);
                System.out.println("Booking Time: " + current.bookingTime);
                System.out.println("----------------------------");
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No ticket found for " + searchValue);
        }
    }
    public void totalTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        int count = 0;
        do {
            count++;
            current = current.next;
        } while (current != head);

        System.out.println("Total number of tickets booked: " + count);
    }
}

public class TicketReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();

        while (true) {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Remove Ticket by ID");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket");
            System.out.println("5. Total Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int ticketID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = sc.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movieName = sc.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seatNumber = sc.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String bookingTime = sc.nextLine();
                    system.addTicket(ticketID, customerName, movieName, seatNumber, bookingTime);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    int removeID = sc.nextInt();
                    system.removeTicket(removeID);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String searchValue = sc.nextLine();
                    system.searchTicket(searchValue);
                    break;
                case 5:
                    system.totalTickets();
                    break;
                case 6:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
