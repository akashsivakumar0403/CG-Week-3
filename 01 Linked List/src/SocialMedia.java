import java.util.*;

class FriendNode {
    int friendId;
    FriendNode next;

    FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class User {
    int userId;
    String name;
    int age;
    FriendNode friendList;
    User next;

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendList = null;
        this.next = null;
    }

    void addFriend(int friendId) {
        if (!isAlreadyFriend(friendId)) {
            FriendNode newFriend = new FriendNode(friendId);
            newFriend.next = friendList;
            friendList = newFriend;
        }
    }

    void removeFriend(int friendId) {
        if (friendList == null) return;
        if (friendList.friendId == friendId) {
            friendList = friendList.next;
            return;
        }
        FriendNode temp = friendList;
        while (temp.next != null && temp.next.friendId != friendId) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    boolean isAlreadyFriend(int friendId) {
        FriendNode temp = friendList;
        while (temp != null) {
            if (temp.friendId == friendId) return true;
            temp = temp.next;
        }
        return false;
    }

    void displayFriends() {
        if (friendList == null) {
            System.out.println("No friends.");
            return;
        }
        System.out.print("Friends: ");
        FriendNode temp = friendList;
        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    int countFriends() {
        int count = 0;
        FriendNode temp = friendList;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class UserList {
    User head;

    void addUser(int userId, String name, int age) {
        if (getUser(userId) != null) {
            System.out.println("User ID already exists.");
            return;
        }
        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
        System.out.println("User added.");
    }

    User getUser(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriendConnection(int userId1, int userId2) {
        User user1 = getUser(userId1);
        User user2 = getUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }
        user1.addFriend(userId2);
        user2.addFriend(userId1);
        System.out.println("Friend connection added.");
    }

    void removeFriendConnection(int userId1, int userId2) {
        User user1 = getUser(userId1);
        User user2 = getUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }
        user1.removeFriend(userId2);
        user2.removeFriend(userId1);
        System.out.println("Friend connection removed.");
    }

    void displayFriendsOfUser(int userId) {
        User user = getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("User: " + user.name + " (ID: " + user.userId + ")");
        user.displayFriends();
    }

    void findMutualFriends(int userId1, int userId2) {
        User user1 = getUser(userId1);
        User user2 = getUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        Set<Integer> friendsSet = new HashSet<>();
        FriendNode temp = user1.friendList;
        while (temp != null) {
            friendsSet.add(temp.friendId);
            temp = temp.next;
        }

        System.out.print("Mutual Friends: ");
        boolean found = false;
        temp = user2.friendList;
        while (temp != null) {
            if (friendsSet.contains(temp.friendId)) {
                System.out.print(temp.friendId + " ");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("None.");
        else System.out.println();
    }

    void searchUser(String query) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(query) || Integer.toString(temp.userId).equals(query)) {
                System.out.println("User ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No matching user found.");
        }
    }

    void countFriendsOfAll() {
        User temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.userId + ", Name: " + temp.name + ", Friends: " + temp.countFriends());
            temp = temp.next;
        }
    }
}

public class SocialMedia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserList list = new UserList();

        while (true) {
            System.out.println("\n1. Add User\n2. Add Friend Connection\n3. Remove Friend Connection");
            System.out.println("4. Display Friends of a User\n5. Find Mutual Friends");
            System.out.println("6. Search User\n7. Count Friends of All Users\n8. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    list.addUser(uid, name, age);
                    break;
                case 2:
                    System.out.print("Enter User ID 1: ");
                    int u1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int u2 = sc.nextInt();
                    list.addFriendConnection(u1, u2);
                    break;
                case 3:
                    System.out.print("Enter User ID 1: ");
                    int r1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int r2 = sc.nextInt();
                    list.removeFriendConnection(r1, r2);
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int fId = sc.nextInt();
                    list.displayFriendsOfUser(fId);
                    break;
                case 5:
                    System.out.print("Enter User ID 1: ");
                    int m1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int m2 = sc.nextInt();
                    list.findMutualFriends(m1, m2);
                    break;
                case 6:
                    System.out.print("Enter Name or User ID: ");
                    String query = sc.nextLine();
                    list.searchUser(query);
                    break;
                case 7:
                    list.countFriendsOfAll();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
