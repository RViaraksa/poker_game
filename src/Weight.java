public enum Weight {
    TWO(2), THREE(3) , FOUR(4) , FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11),
    QUEN(12), KING(13), ACE(14);

    private int cardWeight;
    Weight(int i) {
        this.cardWeight = i;
    }

    public int getWeight() {
        return cardWeight;
    }
}
