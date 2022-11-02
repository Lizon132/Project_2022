public class card {
    char S; //suite
    char F; //whatever is on the face of the card, K = king, J=Jack, 0 = 10, etc
    int value; //value of the card

    public card(char s, char f, int value) {
        S = s;
        F = f;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getS() {
        return S;
    }

    public char getF() {
        return F;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + S + F;
    }

}
