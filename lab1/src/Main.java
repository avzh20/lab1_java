/*
Лабораторная работа 1

Вариант B
Ввести с консоли n целых чисел и поместить их в массив. На консоль вывести:
1. Четные и нечетные числа.
2. Числа, которые делятся на 3 или на 9.
3. Числа, которые делятся на 5 или на 10.
4. Наибольший общий делитель и наименьшее общее кратное
этих чисел.
5. Простые числа.
6. “Счастливые” числа.
7. Числа Фибоначчи: f0 = f1 = 1, f (n) = f (n–1) + f (n–2).
8. Числа-палиндромы, значения которых в прямом и обратном
порядке совпадают.
9. Период десятичной дроби p = m/n для первых двух целых по-
ложительных чисел n и m, расположенных подряд.
10. Построить треугольник Паскаля для первого положительного числа.

*/

import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество чисел:");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Введите числа:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        // 1. Четные и нечетные числа
        System.out.println("Четные числа:");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println("\nНечетные числа:");
        for (int num : numbers) {
            if (num % 2 != 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 2. Числа, которые делятся на 3 или на 9
        System.out.println("Числа, которые делятся на 3 или на 9:");
        for (int num : numbers) {
            if (num % 3 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 3. Числа, которые делятся на 5 или на 10
        System.out.println("Числа, которые делятся на 5 или на 10:");
        for (int num : numbers) {
            if (num % 5 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 4. Наибольший общий делитель и наименьшее общее кратное
        int gcd = numbers[0];
        BigInteger lcm = BigInteger.valueOf(numbers[0]);
        for (int i = 1; i < n; i++) {
            gcd = gcd(gcd, numbers[i]);
            lcm = lcm(lcm, BigInteger.valueOf(numbers[i]));
        }
        System.out.println("НОД: " + gcd);
        System.out.println("НОК: " + lcm);

        // 5. Простые числа
        System.out.println("Простые числа:");
        for (int num : numbers) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 6. “Счастливые” числа
        System.out.println("Счастливые числа:");
        for (int num : numbers) {
            if (isHappy(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 7. Числа Фибоначчи
        System.out.println("Числа Фибоначчи:");
        for (int num : numbers) {
            System.out.print(fibonacci(num) + " ");
        }
        System.out.println();

        // 8. Числа-палиндромы
        System.out.println("Числа-палиндромы:");
        for (int num : numbers) {
            if (isPalindrome(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // 9. Период десятичной дроби
        if (n >= 2) {
            int m = numbers[0];
            int p = numbers[1];
            System.out.println("Период десятичной дроби для " + m + "/" + p + ": " + decimalPeriod(m, p));
        }

        // 10. Треугольник Паскаля
            System.out.println("Треугольник Паскаля для " + numbers[0] + ":");
            printPascalsTriangle(numbers[0]);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(BigInteger.valueOf(gcd(a.intValue(), b.intValue())));
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static boolean isHappy(int num) {
        Set<Integer> seen = new HashSet<>();
        while (num != 1 && !seen.contains(num)) {
            seen.add(num);
            num = sumOfSquaresOfDigits(num);
        }
        return num == 1;
    }

    public static int sumOfSquaresOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static boolean isPalindrome(int num) {
        String str = Integer.toString(num);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public static String decimalPeriod(int m, int n) {
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>();
        int remainder = m % n;
        while (remainder != 0 && !map.containsKey(remainder)) {
            map.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / n);
            remainder %= n;
        }
        return remainder == 0 ? "" : result.substring(map.get(remainder));
    }

    public static void printPascalsTriangle(int rows) {
        for (int i = 0; i < rows; i++) {
            int number = 1;
            System.out.format("%" + (rows - i) * 2 + "s", "");
            for (int j = 0; j <= i; j++) {
                System.out.format("%4d", number);
                number = number * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }
}
