import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class CircularTaskScheduler {
    Task head = null;
    Task current = null;

    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            Task temp = head;
            while (temp.next != head) temp = temp.next;
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
        System.out.println("Task added at beginning.");
    }

    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            Task temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = newTask;
            newTask.next = head;
        }
        System.out.println("Task added at end.");
    }

    public void addAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        if (position == 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int count = 1;

        while (count < position - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;
        System.out.println("Task added at position " + position);
    }

    public void removeById(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Task temp = head, prev = null;

        if (head.taskId == id) {
            if (head.next == head) {
                head = null;
                System.out.println("Task removed. List is now empty.");
                return;
            }
            Task last = head;
            while (last.next != head) last = last.next;
            head = head.next;
            last.next = head;
            System.out.println("Task with ID " + id + " removed.");
            return;
        }

        do {
            prev = temp;
            temp = temp.next;
            if (temp.taskId == id) {
                prev.next = temp.next;
                System.out.println("Task with ID " + id + " removed.");
                return;
            }
        } while (temp != head);

        System.out.println("Task with ID " + id + " not found.");
    }

    public void viewCurrentAndNext() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        if (current == null) current = head;

        System.out.println("Current Task:");
        printTask(current);
        current = current.next;
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        System.out.println("All Tasks:");
        Task temp = head;
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        boolean found = false;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                printTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }

    private void printTask(Task t) {
        System.out.println("----------------------------");
        System.out.println("Task ID: " + t.taskId);
        System.out.println("Name: " + t.taskName);
        System.out.println("Priority: " + t.priority);
        System.out.println("Due Date: " + t.dueDate);
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularTaskScheduler scheduler = new CircularTaskScheduler();
        int choice;

        do {
            System.out.println("\n=== Task Scheduler ===");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current and Move to Next Task");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTask(sc, scheduler, "beginning");
                    break;
                case 2:
                    addTask(sc, scheduler, "end");
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    addTaskAtPosition(sc, scheduler, pos);
                    break;
                case 4:
                    System.out.print("Enter Task ID to Remove: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    scheduler.removeById(id);
                    break;
                case 5:
                    scheduler.viewCurrentAndNext();
                    break;
                case 6:
                    scheduler.displayAllTasks();
                    break;
                case 7:
                    System.out.print("Enter Priority to Search: ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    scheduler.searchByPriority(priority);
                    break;
                case 8:
                    System.out.println("Exiting Task Scheduler.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        sc.close();
    }

    private static void addTask(Scanner sc, CircularTaskScheduler scheduler, String where) {
        System.out.print("Enter Task ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Task Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Priority: ");
        int priority = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Due Date: ");
        String dueDate = sc.nextLine();

        if (where.equals("beginning")) {
            scheduler.addAtBeginning(id, name, priority, dueDate);
        } else {
            scheduler.addAtEnd(id, name, priority, dueDate);
        }
    }

    private static void addTaskAtPosition(Scanner sc, CircularTaskScheduler scheduler, int pos) {
        System.out.print("Enter Task ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Task Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Priority: ");
        int priority = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Due Date: ");
        String dueDate = sc.nextLine();

        scheduler.addAtPosition(pos, id, name, priority, dueDate);
    }
}
