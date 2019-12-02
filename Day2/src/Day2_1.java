import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2_1 {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day2\\input.txt";
        List<Integer> input = new ArrayList<Integer>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            input = stream.flatMap(line -> Arrays.stream(line.split(","))).map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        input.set(1, 12);
        input.set(2, 2);

        for (int i = 0; i < input.size() - 5  || input.get(i) != 99; i += 4) {
            input.set(input.get(i + 3), input.get(i) == 1 ? input.get(input.get(i+1)) + input.get(input.get(i+2)) : input.get(i) == 2 ?  input.get(input.get(i+1)) * input.get(input.get(i+2)) : input.get(i+3));
        }

        System.out.println(input.get(0));

    }
}
