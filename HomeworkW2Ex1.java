package HomeworkW2;

public class HomeworkW2Ex1 {

    public static void main(String[] args) {

        int sideAofRectangle = 2;
        int sideBofRectangle = 6;

        perimeterOfRectangle(sideAofRectangle, sideBofRectangle);
        areaOfRectangle(sideAofRectangle, sideBofRectangle);

    }
    public static void perimeterOfRectangle(int sideA, int sideB) {

        int perimeter = 2 * sideA + 2 * sideB;

        System.out.printf("The Perimeter of the rectangle is: %d\n",perimeter);
    }
    public static void areaOfRectangle(int sideA, int sideB) {

        int area = sideA * sideB;

        System.out.printf("The area of the rectangle is: %d", area);
    }
}
