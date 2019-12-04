import java.util.ArrayList;
import java.util.List;

public class Day4_1 {

    public static void main(String[] args) {
        int upperLimit = 790572;
        int lowerLimit = 245182;

        int possibleNumbers = 0;

        for (int i = lowerLimit; i <= upperLimit; i++) {
            if (checkAdjacentDigits(i)) {
                possibleNumbers++;
            }
        }

        System.out.println(possibleNumbers);
    }

    public static boolean checkAdjacentDigits(int number) {

        List<Integer> passedDigits = new ArrayList<Integer>();
        boolean lucky = false;
        boolean ascending = false;

        while (number > 0) {
            int digit = number % 10;

            if (passedDigits.size() >= 1) {
                if (passedDigits.get(passedDigits.size() - 1) >= digit) {
                    ascending = true;
                }
                else return false;
            }

            if (!passedDigits.isEmpty()) {
                if (passedDigits.get(passedDigits.size() - 1) == digit) {
                    lucky = true;
                }
                passedDigits.add(digit);
            } else {
                passedDigits.add(digit);
            }
            number /= 10;
        }
       return lucky & ascending;
    }
}
