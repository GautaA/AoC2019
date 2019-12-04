import java.util.ArrayList;
import java.util.List;

public class Day4_2 {

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
        List<Integer> luckyList = new ArrayList<Integer>();
        boolean lucky = false;
        boolean ascending = false;
        boolean luckyStreak = false;

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
                    if (!luckyList.isEmpty()) {
                        if (checkIfSame(luckyList.get(luckyList.size() -1), digit)) {
                            int lastDigit = luckyList.get(luckyList.size() - 1);
                            String s1 = Integer.toString(lastDigit);
                            String s2 = Integer.toString(digit);
                            String s3 = s1 + s2;
                            luckyList.remove(luckyList.size() - 1);
                            luckyList.add(Integer.parseInt(s3));
                        }
                        else {
                            int lastDigit = passedDigits.get(passedDigits.size() - 1);
                            String s1 = Integer.toString(lastDigit);
                            String s2 = Integer.toString(digit);
                            String s3 = s1 + s2;
                            luckyList.add(Integer.parseInt(s3));
                        }
                    } else {
                        int lastDigit = passedDigits.get(passedDigits.size() - 1);
                        String s1 = Integer.toString(lastDigit);
                        String s2 = Integer.toString(digit);
                        String s3 = s1 + s2;
                        luckyList.add(Integer.parseInt(s3));
                    }
                }
                passedDigits.add(digit);
            } else {
                passedDigits.add(digit);
            }
            number /= 10;
        }

        for (int i = 0; i < luckyList.size(); i++) {
            int numberOfDigits = String.valueOf(luckyList.get(i)).length();
            if (numberOfDigits == 2) luckyStreak = true;
        }

        return lucky & ascending & luckyStreak;
    }

    private static boolean checkIfSame(int arrayNumber, int digit) {
        if (arrayNumber % 10 == digit) return true;
        else return false;
    }
}
