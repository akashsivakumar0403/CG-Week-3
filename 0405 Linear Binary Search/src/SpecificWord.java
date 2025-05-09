public class SpecificWord {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word))
                return sentence;
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {"Capgemini wants Java", "Hello World", "Why do we exist?"};
        String result = findSentenceWithWord(sentences, "Java");
        System.out.println("Sentence found: " + result);
    }
}
