package Homework_W2;

public class Homework_W2_3 {

    public static void main(String[] args) {
        
        int floors = 9;

        switch(floors) {
            case 9:
                floors--;
            case 8:
                System.out.println("Ани");
                floors--;
            case 7:
                System.out.println("Пепи");
                floors--;
            case 6:
                System.out.println("Ваня");
                floors--;
            case 5:
                System.out.println("Васи");
                floors--;
            case 4:
                System.out.println("Деси");
                floors--;
            case 3:
                System.out.println("Дими");
                floors--;
            case 2:
                System.out.println("Димитър");
                floors--;
            case 1:
                System.out.println("Георги");
                floors--;
            case 0:
                System.out.println("Ауч");
        }
    }
}
