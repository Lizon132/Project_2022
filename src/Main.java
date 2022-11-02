import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ply;

        System.out.println("How many sets of cards do you want in the playdeck (between 1 and 10)? ");
        String str;
        do {
            str = sc.nextLine();
        } while (!intValidation(str, 10)); // Validation loop, always do a validation loop
        ArrayList<card> deck = createDeck(Integer.parseInt(str));

        System.out.println("How many Human Players (between 1 and 7)?");
        do {
            str = sc.nextLine();
        } while (!intValidation(str, 7)); // Validation loop, always do a validation loop
        ply = Integer.parseInt(str); // number of players
        ArrayList<players> playerList = createPlayers(ply); // create ply number of players

        System.out.println(playerList);
        System.out.println(deck);
        gameBlackjack gm = new gameBlackjack();
        gm.game(deck, playerList);
    }

    public static ArrayList<players> createPlayers(int num){
        Scanner sc = new Scanner(System.in);
        ArrayList<players> plist = new ArrayList<>();
        for (int i=1; i<=num; ++i){
            try{

                String choice, str;
                do{
                    System.out.println("Enter player " + i + " name: ");
                    str = sc.nextLine();
                    System.out.println("Is " + str + " correct?\n1. Yes\n2. No");

                    do {
                        choice = sc.nextLine();
                    } while (!intValidation(choice, 2)); // Validation loop, always do a validation loop

                }while (Integer.parseInt(choice) == 2);//Validate the name
                plist.add(new players(str,0,0));

            }catch (Exception e){
                System.out.println("Error in creating player");
            }
        }
        return plist;
    }
    public static ArrayList<card> createDeck(int num){
        ArrayList<card> deck = new ArrayList<>();
        for (int i=0; i < num; ++i){
            try {
                File myObj = new File("src/cardlist.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    deck.add(new card(myReader.next().charAt(0), myReader.next().charAt(0), myReader.nextInt()));
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        return deck;
    }

    public static boolean intValidation (String str, int num){

        try{
            if (Integer.parseInt(str) <= 0 || Integer.parseInt(str)>num){
                System.out.println(str + " is an invalid entry, please enter a number between 1 and " + num);
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(str + " is an invalid entry, please try again");
            return false;
        }
    }
}