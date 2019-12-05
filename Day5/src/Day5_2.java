import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5_2 {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day5\\input.txt";
        List<Integer> input = new ArrayList<Integer>();
        boolean paramMode = true;
        int storedValue;

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            input = stream.flatMap(line -> Arrays.stream(line.split(","))).map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; input.get(i) != 99; i++) {
            if (input.get(i) == 1) {
                if (paramMode) input.set(input.get(i + 3), (input.get(input.get(i + 1)) + input.get(input.get(i + 2))));
                else input.set(input.get(i + 3), (input.get(i + 1) + input.get(i + 2)));
                i = i+3;
            } else if (input.get(i) == 2) {
                int pasMater = input.get(i + 3);
                if (paramMode) input.set(pasMater, input.get(input.get(i + 1)) * input.get(input.get(i + 2)));
                else input.set(input.get(i + 3), (input.get(i + 1) * input.get(i + 2)));
                i = i+3;
            } else if (input.get(i) == 3) {
                input.set(input.get(i + 1), 5);
                i++;
            } else if (input.get(i) == 4) {
                if (paramMode) storedValue = input.get(input.get(i+1));
                else storedValue = input.get(i+1);
                System.out.println(storedValue);
                i++;
            } else if (input.get(i) == 5) {
                if (input.get(input.get(i + 1)) != 0) i = input.get(i + 2) - 1;
                else i += 2;
            } else if (input.get(i) == 6) {
                if (input.get(input.get(i + 1)) == 0) i = input.get(i + 2) - 1;
                else i += 2;
            } else if (input.get(i) == 7) {
                int store = input.get(i + 3);
                if (input.get(input.get(i + 1)) < input.get(input.get(i + 2))) input.set(store, 1);
                else input.set(store, 0);
                i += 3;
            } else if (input.get(i) == 8) {
                int store = input.get(i + 3);
                if (input.get(input.get(i + 1)).equals(input.get(input.get(i + 2)))) input.set(store, 1);
                else input.set(store, 0);
                i += 3;
            } else {
                int n = input.get(i);
                ArrayList<Integer> digits = splitNumber(n);
                ArrayList<Boolean> modes = new ArrayList<Boolean>();
                int firstParam, secondParam = 0, thirdParam = 0, holder;
                for (int j = 1; j < 4; j++) {
                    if (digits.get(j) == 1) modes.add(true);
                    else modes.add(false);
                }

                if (modes.get(0)) firstParam = input.get(i + 1);
                else firstParam = input.get(input.get(i + 1));

                if (digits.get(0) != 3 && digits.get(0) != 4) {
                    if (modes.get(1)) secondParam = input.get(i + 2);
                    else secondParam = input.get(input.get(i + 2));
                }

                if (digits.get(0) == 1) {
                    holder = firstParam + secondParam;
                    input.set(input.get(i + 3), holder);
                    i += 3;
                } else if (digits.get(0) == 2) {
                    holder = firstParam * secondParam;
                    input.set(input.get(i + 3), holder);
                    i += 3;
                } else if (digits.get(0) == 3) {
                    input.set(input.get(i + 1), 5);
                    i++;
                } else if (digits.get(0) == 4) {
                    System.out.println(firstParam);
                    i++;
                } else if (digits.get(0) == 5) {
                    if (firstParam != 0) i = secondParam - 1;
                    else i += 2;
                } else if (digits.get(0) == 6) {
                    if (firstParam == 0) i = secondParam - 1;
                    else i += 2;
                } else if (digits.get(0) == 7) {
                    thirdParam = input.get(i + 3);
                    if (firstParam < secondParam) input.set(thirdParam, 1);
                    else input.set(thirdParam, 0);
                    i += 3;
                } else if (digits.get(0) == 8) {
                    thirdParam = input.get(i + 3);
                    if (firstParam == secondParam) input.set(thirdParam, 1);
                    else input.set(thirdParam, 0);
                    i += 3;
                }
            }
        }
    }

    private static ArrayList<Integer> splitNumber(int n) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        returnList.add(n % 100);
        n /= 100;
        for (int i = 0; i < 3; i++) {
            returnList.add(n % 10);
            n /= 10;
        }
        return returnList;
    }
}
