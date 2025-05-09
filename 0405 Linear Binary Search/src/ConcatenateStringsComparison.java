public class ConcatenateStringsComparison {
    public static void main(String[] args) {
        int count = 1_000_000;
        StringBuilder sbBuilder = new StringBuilder();
        long startTimeBuilder = System.nanoTime();
        for (int i = 0; i < count; i++) {
            sbBuilder.append("hello");
        }
        long endTimeBuilder = System.nanoTime();
        long durationBuilder = endTimeBuilder - startTimeBuilder;
        StringBuffer sbBuffer = new StringBuffer();
        long startTimeBuffer = System.nanoTime();
        for (int i = 0; i < count; i++) {
            sbBuffer.append("hello");
        }
        long endTimeBuffer = System.nanoTime();
        long durationBuffer = endTimeBuffer - startTimeBuffer;
        System.out.println("Time taken by StringBuilder: " + durationBuilder / 1_000_000 + " ms");
        System.out.println("Time taken by StringBuffer: " + durationBuffer / 1_000_000 + " ms");
    }
}
