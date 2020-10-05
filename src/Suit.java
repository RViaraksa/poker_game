public enum Suit {
    CHIRVA("C"), BUBA("B"), PIKA("P"), KRESTI("K");
    private String suitName;

    Suit(String c) {
        this.suitName = c;
    }

    String getSuitName(){
        return suitName;
    }
}
