package Homework_W2;

import java.util.Scanner;

public class Homework_W2_1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter sides A of the rectangle");
        double sideA = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter side B of the rectangle");
        double sideB = Double.parseDouble(scanner.nextLine());

        double P = perimeter(sideA, sideB);
        double area = area(sideA, sideB);

        System.out.printf("The perimeter is: %.2f \n", P);
        System.out.printf("The area is: %.2f", area);
    }
    public static double perimeter(double sideA, double sideB) {
        double P = 2 * sideA + 2 * sideB;
        return P;
    }
    public static double area(double sideA, double sideB) {
        double area = sideA * sideB;
        return area;
    }
}