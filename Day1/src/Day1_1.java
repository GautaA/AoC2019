import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day1_1 {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day1\\input.txt";
        int result = 0;

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            result = stream.mapToInt(Integer::valueOf).map(i -> Math.floorDiv(i, 3) - 2).sum();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}