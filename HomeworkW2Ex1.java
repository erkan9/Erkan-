package Homework_W2;

public class Homework_W2_1 {

    public static void main(String[] args) {

       Perimeter();
       Area();
        
    }
    public static void Perimeter() {
        int sideA = 2; 
        int sideB = 6;
        
        int P = 2 * sideA + 2 * sideB;
        
        System.out.println("The Perimeter of the rectangle is : " + P);
    }
    public static void Area() {
        int sideA = 2; 
        int sideB = 6;
        
        int area = sideA * sideB;
        
        System.out.println("The area of the rectangle is : " + area);
    }
}
