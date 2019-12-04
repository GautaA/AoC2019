import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4_1 {

    public static void main(String[] args) {
        int upperLimit = 790572, lowerLimit = 245182, possibleNumbers = 0;

        for (int i = lowerLimit; i <= upperLimit; i++) {
            if (checkIfOrdered(Integer.toString(i)) && checkDigits(Integer.toString(i))) possibleNumbers++;
        }
        System.out.println(possibleNumbers);
    }

    private static boolean checkIfOrdered(String number) {
        String sortedNumber = Stream.of(number.split("")).sorted().collect(Collectors.joining());
        return number.equals(sortedNumber) ? true : false;
    }

    private static boolean checkDigits(String number) {
        String[] aList = number.split("");
        for (int i = 0; i < aList.length - 1; i++) {
            if (aList[i].equals(aList[i+1])) return true;
        }
        return false;
    }
}
