package main.java.com.vijay.learning.problemsolve;

//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//@Slf4j
public class ProblemSolvingUtils {

    public static int fibonacci(int position) {
        if (position <= 2) return 1;
        /** without recursive **/
        /*int fibonacci = 1;
        int fibo1 = 1;
        int fibo2 = 1;
        for (int i = 3; i <= position; i++) {
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        return fibonacci; */
        return fibonacci(position - 1) + fibonacci(position - 2);
    }

    public static List<Integer> getFibonacciSeries(int fibCount) {
        /*List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= fibCount; i++) {
            list.add(fibonacci(i));
        }
        return list;*/
        // Using Java8
        return IntStream.rangeClosed(1, fibCount).mapToObj(ProblemSolvingUtils::fibonacci).collect(Collectors.toList());

    }

    public static boolean isPrime(int number) {
        if (number <= 2) return number == 2;
        if (number % 2 == 0) return false;
        /* for (int i = 3; i <= Math.sqrt(number); i = i + 2) {
            if (number % i == 0) return false;
        }
        return true;
         */
        return IntStream.iterate(3, i -> i <= Math.sqrt(number), i -> i + 2).noneMatch(i -> number % i == 0);
    }

    public static List<Integer> getPrimeNumbers(int rangeNumber) {
        return IntStream.rangeClosed(2, rangeNumber).filter(ProblemSolvingUtils::isPrime).boxed().collect(Collectors.toUnmodifiableList());
    }

    public static String reverse(String input) {
        if (Objects.isNull(input) || input.isBlank()) return input;
        //return new StringBuilder(input).reverse().toString();
        //return StringUtils.reverse(input);
        return input.charAt(input.length() - 1) + reverse(input.substring(0, input.length() - 1));

    }

    public static boolean isPalindrome(String input) {
        return input.equals(reverse(input));
    }
    public static boolean isEfficientPalindrome(String input) {
        char[] chars = input.toCharArray();
        int j = chars.length - 1;
        for (int i = 0; i <= j; i++) {
            if (chars[i] == chars[j]) {
                //log.info("Equal Chars: {} && {}", chars[i], chars[j]);
            } else {
               // log.info("Non Equal Chars: {} && {}", chars[i], chars[j]);
                return false;
            }
            j = j - 1;
        }
        return true;
    }

    public static int reverse(int number) {
        // 1234
        int reverse = 0;
        int remainder;
        while (number > 0) {
            remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number = number / 10;
        }
        return reverse;
    }

    public static boolean isPalindrome(int input) {
        return input == reverse(input);
    }

    public static double getCubesSum(int number) {
        double sum = 0;
        int remainder;
        while (number > 0) {
            remainder = number % 10;
            sum = sum + Math.pow(remainder, 3);
            number = number / 10;
        }
        return sum;
    }

    public static boolean isArmstrong(int number) {
        return number == getCubesSum(number);
    }

    public static List<Integer> getArmStrongNumbers(int number) {
        return IntStream.rangeClosed(1, number).filter(ProblemSolvingUtils::isArmstrong).boxed().collect(Collectors.toList());
    }

    public static int findFactorial(int number) {
        return IntStream.rangeClosed(1, number).reduce(1, (a, b) -> a * b);
    }

    public static int[] removeDuplicate(int[] inputArray) {
        return Arrays.stream(inputArray).distinct().toArray();
    }

    public static Map<Character, Integer> findDuplicateChars(String name) {
        return name.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() >= 2)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().intValue(), (a, b) -> b));
    }

    public static Map<Integer, Integer> findDuplicateNumbers(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() >= 2)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().intValue(), (a, b) -> b));
    }

    public static List<String> findTwoSum(int[] numbers, int target) {
        /** formula
         x+complement = target
         complement = target -x
         */
        List<String> result = new ArrayList<>();
        Map<Integer, Integer> intMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            if (intMap.containsKey(complement)) {
                // result will return position
                //result.add("(" + intMap.get(complement) + "," + i + ")");
                // result will values
                result.add("(" + numbers[intMap.get(complement)] + "," + numbers[i] + ")");
            } else {
                intMap.put(numbers[i], i);
            }
        }
        return result;

    }

    public static boolean isAnagram(String string, String anotherString) {
        if (string.length() == anotherString.length()) {
            Map<Character, Long> mapOne = string.trim().toLowerCase().chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
            Map<Character, Long> mapTwo = anotherString.trim().toLowerCase().chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
            return mapOne.equals(mapTwo);
        }
        return false;
    }

    public static boolean isArmStrongNumber(Integer inputNumber) {
        return inputNumber == Arrays.stream(String.valueOf(inputNumber).split("")).mapToDouble(number -> Math.pow(Integer.parseInt(number), 3)).sum();
    }

    public static Character findFirstNonRepeatedCharacter(String name) {
        Map<Character, Long> countMap = new LinkedHashMap<>(name.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(ch -> ch, Collectors.counting())));
        //log.info("countMap: {}", countMap);
        return countMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst().orElseThrow(RuntimeException::new);
    }
    public static void swapStrings(String s1, String s2) {
        s1 = s1 + s2;
        s2 = s1.substring(0, s1.length() - s2.length());
        s1 = s1.substring(s2.length());
      //  log.info("String s1: {} s2: {}", s1, s2);

    }
    public static int swapCountForPalindrome(String input) {
        int length = input.length();
        if(length==0) return -1;
        // If String already a palindrome return 0
        if(input.equals(new StringBuilder(input).reverse().toString())) return 0;
        // aaabbb abaaba
        Map<Character, Long> countMap = input.chars().mapToObj(ch -> (char) ch).collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        // if length is even then all characters count should be even for palindrome
        if(length%2==0 && countMap.entrySet().stream().anyMatch(entry -> entry.getValue() % 2 != 0)) return -1;

        // if length is odd then only character count should be odd for palindrome
        if(length%2==1 && countMap.entrySet().stream().filter(entry -> entry.getValue() % 2 == 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).size()!=1) return -1;

        return swapCount(input.toCharArray());

    }
    private static int swapCount(char[] charArray){
        int result = 0;
        int i = 0;
        int j = charArray.length - 1;
        int k = j;
        while(i < j) {
            k = j;
            while(charArray[i] != charArray[k] && k > i)
                k--;
            if(charArray[i] == charArray[k] && i != k) {
                while(k < j) {
                    swap(charArray,k,k+1);
                    k++;
                    result++;
                }
                i++;
                j--;
            }
            else {
                swap(charArray,i,i+1);
                result++;
            }

        }
        return result;
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static int[] altTab(int input1, int input2, int[] input3) {
        if (input1 != input3.length) {
            throw new IllegalArgumentException("Input length doesn't match");
        }
        List<Integer> list = Arrays.stream(input3).boxed().collect(Collectors.toList());
        Integer removed = list.remove(input2 - 1);
        list.add(0, removed);
        return list.stream().mapToInt(i -> i).toArray();
    }
}
