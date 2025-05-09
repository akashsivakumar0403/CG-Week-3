import java.util.HashSet;

public class RemoveDuplicates {
    public static String removeDuplicates(String input) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();

        for (char ch : input.toCharArray()) {
            if (!seen.contains(ch)) {
                seen.add(ch);
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "hello world";
        String result = removeDuplicates(input);
        System.out.println("Original: " + input);
        System.out.println("Without duplicates: " + result);
    }
}
