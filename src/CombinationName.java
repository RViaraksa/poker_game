public enum CombinationName {
    FLUSH_ROYAL(10), STRAIGHT_FLUSH(9), FOUR_A_KIND(8),
    FULL_OF_HOUSE(7), FLUSH(6), STRAIGHT(5),
    THREE_OF_KIND(4), TWO_PAIR(3), PAIR(2), HIGH_CARD(1);

    private int range;
    CombinationName(int range){
        this.range = range;
    }

}
