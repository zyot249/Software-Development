package homework01;

import java.util.Scanner;

class Calculator {
    public static void main(String main[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter two number:");
        System.out.print("The first number: ");
        int a = scanner.nextInt();
        System.out.print("The second number: ");
        int b = scanner.nextInt();
        scanner.close();

        System.out.printf("a + b = %d\n", a + b);
        System.out.printf("a - b = %d\n", a - b);
        System.out.printf("a * b = %d\n", a * b);
        System.out.printf("a / b = %f\n", (float) a / (float) b);
    }

}
