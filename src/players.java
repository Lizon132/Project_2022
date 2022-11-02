import java.util.ArrayList;

public class players {
    String name;
    int wins, losses;
    ArrayList<card> C = new ArrayList<>();
    int total;

    @Override
    public String toString() {
        return "Name: " + name + "\nCards: " + C + "\nTotal: " + total + "\n---------";
    }

    public void setC(ArrayList<card> c) {
        C = c;
    }

    public ArrayList<card> getC() {
        return C;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        total = 0;
        boolean ace = false;
        for (card card : C) {
            if (card.getF() == 'A')
                ace = true;
            total = total + card.getValue();
        }
        /*This is the ace check. So if your total is over 21 AND you have an ace you run this
         to see if you can change the value of the ace to 1 instead of 11.
         This loop checks EVERY card object in the hand and verifies the value just in case you get multiple aces.*/
        while (ace && total > 21) {
            for (card card : C) {
                if (card.getF() == 'A' && card.getValue() == 11) {
                    card.setValue(1);
                    total = total - 10;
                }
            }
        }

        this.total = total;
    }

    public players(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    public players(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
    public void reset(){
        this.total = 0;
        this.C = new ArrayList<>();
    }
}
