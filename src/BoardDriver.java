import java.util.Scanner;
public class BoardDriver {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Board b2 = new Board(8, 9);
        String userIn;
        System.out.println("Welcome to Gazoo the Explorer!");
        System.out.println();
        b2.printBoard(false);
        do{
            System.out.println("Enter \"w\" to go up, \"a\" to go left, \"s\" to go down, and \"d\" to go right.");
            System.out.print("Or, enter \"i\" to show your current treasure statistics -> ");
            userIn = input.nextLine().trim().toLowerCase();
            char c;
            if(userIn.equals("")){
                c = ' ';
            }else{
                c = userIn.charAt(0);
            }
            if(b2.move(c) == true){
                b2.printBoard(false);
            }
        }while(b2.isGameOver() == false);

    }
}
