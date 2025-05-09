import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    String grade;
    Student next;

    public Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    Student head;
    public void addAtBeginning(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = head;
        head = newStudent;
        System.out.println("Student added at beginning.");
    }
    public void addAtEnd(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
        } else {
            Student temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newStudent;
        }
        System.out.println("Student added at end.");
    }
    public void addAtPosition(int position, int rollNo, String name, int age, String grade) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }

        if (position == 1) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }

        Student newStudent = new Student(rollNo, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position is greater than list size. Adding at end.");
            addAtEnd(rollNo, name, age, grade);
        } else {
            newStudent.next = temp.next;
            temp.next = newStudent;
            System.out.println("Student added at position " + position);
        }
    }
    public void deleteByRollNo(int rollNo) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Student with Roll No " + rollNo + " deleted.");
            return;
        }

        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student with Roll No " + rollNo + " not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student with Roll No " + rollNo + " deleted.");
        }
    }
    public void searchByRollNo(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found:");
                System.out.println("Roll No: " + temp.rollNo);
                System.out.println("Name: " + temp.name);
                System.out.println("Age: " + temp.age);
                System.out.println("Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }
    public void updateGrade(int rollNo, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated for Roll No " + rollNo);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        Student temp = head;
        System.out.println("Student Records:");
        while (temp != null) {
            System.out.println("-----------------------------------");
            System.out.println("Roll No: " + temp.rollNo);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();
        int choice;

        do {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Specific Position");
            System.out.println("4. Delete Student by Roll No");
            System.out.println("5. Search Student by Roll No");
            System.out.println("6. Update Student Grade by Roll No");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade1 = sc.nextLine();
                    list.addAtBeginning(roll1, name1, age1, grade1);
                    break;

                case 2:
                    System.out.print("Enter Roll No: ");
                    int roll2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade2 = sc.nextLine();
                    list.addAtEnd(roll2, name2, age2, grade2);
                    break;

                case 3:
                    System.out.print("Enter Position: ");
                    int position = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Roll No: ");
                    int roll3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name3 = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade3 = sc.nextLine();
                    list.addAtPosition(position, roll3, name3, age3, grade3);
                    break;

                case 4:
                    System.out.print("Enter Roll No to Delete: ");
                    int delRoll = sc.nextInt();
                    list.deleteByRollNo(delRoll);
                    break;

                case 5:
                    System.out.print("Enter Roll No to Search: ");
                    int searchRoll = sc.nextInt();
                    list.searchByRollNo(searchRoll);
                    break;

                case 6:
                    System.out.print("Enter Roll No to Update Grade: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();
                    list.updateGrade(updateRoll, newGrade);
                    break;

                case 7:
                    list.displayAll();
                    break;

                case 8:
                    System.out.println("Closing system");
                    break;

                default:
                    System.out.println("Select existing options");
            }

        } while (choice != 8);

        sc.close();
    }
}
