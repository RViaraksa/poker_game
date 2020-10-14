 class Card {
    Suit suit;
    Weight cardWeight;

    public Card(Suit suit, Weight cardWeight) {
        this.suit = suit;
        this.cardWeight = cardWeight;
    }

    public Suit getSuit() {
        return suit;
    }

    public Weight getCardWeight() {
        return cardWeight;
    }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;

         Card card = (Card) o;

         if (suit != card.suit) return false;
         return cardWeight == card.cardWeight;
     }

     @Override
     public int hashCode() {
         int result = suit != null ? suit.hashCode() : 0;
         result = 31 * result + (cardWeight != null ? cardWeight.hashCode() : 0);
         return result;
     }
 }
