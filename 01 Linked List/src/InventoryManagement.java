import java.util.Scanner;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head = null;

    public void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
        System.out.println("Item added at beginning.");
    }

    public void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newItem;
        }
        System.out.println("Item added at end.");
    }

    public void addAtPosition(int position, String name, int id, int quantity, double price) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addAtBeginning(name, id, quantity, price);
            return;
        }

        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        int count = 1;
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            System.out.println("Position out of bounds.");
        } else {
            newItem.next = temp.next;
            temp.next = newItem;
            System.out.println("Item added at position " + position);
        }
    }

    public void removeById(int id) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemId == id) {
            head = head.next;
            System.out.println("Item with ID " + id + " removed.");
            return;
        }

        Item prev = null, temp = head;
        while (temp != null && temp.itemId != id) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Item with ID " + id + " not found.");
        } else {
            prev.next = temp.next;
            System.out.println("Item with ID " + id + " removed.");
        }
    }

    public void updateQuantity(int id, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == id) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + id + " not found.");
    }

    public void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + id + " not found.");
    }

    public void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item with name \"" + name + "\" not found.");
    }

    public void displayTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: $" + total);
    }

    public void sortBy(String field, boolean ascending) {
        head = mergeSort(head, field, ascending);
        System.out.println("Inventory sorted by " + field + " in " + (ascending ? "ascending" : "descending") + " order.");
    }

    private Item mergeSort(Item head, String field, boolean asc) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, field, asc);
        Item right = mergeSort(nextOfMiddle, field, asc);

        return merge(left, right, field, asc);
    }

    private Item merge(Item left, Item right, String field, boolean asc) {
        if (left == null) return right;
        if (right == null) return left;

        Item result;
        int cmp = 0;

        if (field.equals("name")) {
            cmp = left.itemName.compareToIgnoreCase(right.itemName);
        } else if (field.equals("price")) {
            cmp = Double.compare(left.price, right.price);
        }

        if ((asc && cmp <= 0) || (!asc && cmp > 0)) {
            result = left;
            result.next = merge(left.next, right, field, asc);
        } else {
            result = right;
            result.next = merge(left, right.next, field, asc);
        }

        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;

        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayAll() {
        Item temp = head;
        if (temp == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    private void printItem(Item item) {
        System.out.println("-----------------------------");
        System.out.println("Item Name: " + item.itemName);
        System.out.println("Item ID: " + item.itemId);
        System.out.println("Quantity: " + item.quantity);
        System.out.println("Price: $" + item.price);
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int choice;

        do {
            System.out.println("\n=== Inventory Management ===");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by ID");
            System.out.println("6. Search Item by ID");
            System.out.println("7. Search Item by Name");
            System.out.println("8. Display Total Value");
            System.out.println("9. Sort Inventory");
            System.out.println("10. Display All Items");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addItem(sc, inventory, "beginning");
                    break;
                case 2:
                    addItem(sc, inventory, "end");
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    addItemAtPosition(sc, inventory, pos);
                    break;
                case 4:
                    System.out.print("Enter Item ID to remove: ");
                    inventory.removeById(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Item ID to update quantity: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new quantity: ");
                    int qty = sc.nextInt();
                    inventory.updateQuantity(id, qty);
                    break;
                case 6:
                    System.out.print("Enter Item ID to search: ");
                    inventory.searchById(sc.nextInt());
                    break;
                case 7:
                    System.out.print("Enter Item Name to search: ");
                    inventory.searchByName(sc.nextLine());
                    break;
                case 8:
                    inventory.displayTotalValue();
                    break;
                case 9:
                    System.out.print("Sort by (name/price): ");
                    String field = sc.nextLine();
                    System.out.print("Ascending (true/false): ");
                    boolean asc = sc.nextBoolean();
                    inventory.sortBy(field, asc);
                    break;
                case 10:
                    inventory.displayAll();
                    break;
                case 11:
                    System.out.println("Exiting Inventory System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 11);

        sc.close();
    }

    private static void addItem(Scanner sc, Inventory inventory, String where) {
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        if (where.equals("beginning")) {
            inventory.addAtBeginning(name, id, quantity, price);
        } else {
            inventory.addAtEnd(name, id, quantity, price);
        }
    }

    private static void addItemAtPosition(Scanner sc, Inventory inventory, int pos) {
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        inventory.addAtPosition(pos, name, id, quantity, price);
    }
}
