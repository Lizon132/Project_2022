import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class gameBlackjack {
    Scanner sc;
    int dsize=0;
    public static ArrayList<card> deck = new ArrayList<>();
    ArrayList<players> playerList = new ArrayList<>();
    public void game(ArrayList<card> dk, ArrayList<players> playerL) {
        int ply = playerL.size();
        int npc = 7 - ply;
        dsize=dk.size();
        deck = dk;
        playerList = playerL;
        Random rand = new Random();
        sc = new Scanner(System.in);
        System.out.println("Declared Variables");

        //Generate NPC's
        for (int i = 0; i < npc; ++i)
            playerL.add(new players("NPC" + i, 0, 0));

        int D = 2; // deal every player 2 cards
        for (players value : playerL) {
            ArrayList<card> hand = new ArrayList<>();
            for (int n = 0; n < D; ++n) {
                int dealme = rand.nextInt(dk.size());
                hand.add(dk.get(dealme));  //add card to hand
                dk.remove(dealme); //remove card from deck
                value.setC(hand);
            }
            value.setTotal();
        }
        int status = 0;
        do{
            currentGame(playerL, ply);
            highscore(playerL);
            System.out.println("Do you want to play another game?\n1.Yes\n2.No\n-----");
            status = sc.nextInt();
            for (int i=0; i<playerL.size(); ++i){
                playerL.get(i).reset();
                hit(i);
                hit(i);
            }
        } while(status != 2);

    }

    private void currentGame(ArrayList<players> playerL, int ply) {
        //prints the player names, cards, and total
        for (players value : playerL) {
            System.out.println(value);
        }

        //player hit
        for (int i = 0; i < playerL.size(); ++i) {
            if (i < ply){
                boolean flag = true;
                do{
                    try{
                        System.out.println("Do you want to take a hit " + playerList.get(i).getName() +"?\n1. YES\n2.NO");
                        System.out.println(playerL.get(i));
                        int sel = sc.nextInt();
                        if (sel == 1){
                            hit(i);
                            if (playerL.get(i).getTotal() > 21){
                                System.out.println("Too bad you BUSTED!");
                                System.out.println(playerL.get(i));
                                flag=false;
                                break;
                            }
                        } else if (sel == 2){
                            flag = false;
                        } else {
                            System.out.println("Number selection out of range");
                        }
                    }catch(Exception e){
                        System.out.println("Invalid Entry try again");
                        flag = false;
                    }

                }while(flag);
                continue;
            }
            while (playerL.get(i).getTotal()<17){
                hit(i);
            }
        }

        for (players players : playerL) {
            System.out.println(players);
        }
    }


    private void hit(int num){
        Random rand = new Random();
//        ArrayList<card> hand = new ArrayList<>();
        int dealme = rand.nextInt(deck.size());
        playerList.get(num).getC().add(deck.get(dealme));
        playerList.get(num).setTotal();
        deck.remove(dealme);
    }

    private void highscore(ArrayList<players> playerL){
        System.out.println("sorting");
        Collections.sort(playerL, new HighScoreComaparator());
        System.out.println("Scores");
        System.out.println(playerL);
    }
}
