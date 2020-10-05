import java.util.*;

public class Dech {
    ArrayDeque<Card> dush;

    public Dech() {
        Suit[] s = Suit.values();
        Weight[] w = Weight.values();
        dush = new ArrayDeque<>();              //initialization que
        List<Card> buffer = new ArrayList();
        for (Suit i: s) {                       //build card dech, in the final randomizi card position
            for (Weight j: w){
                buffer.add(new Card(i,j)) ;
            }
        }
        Collections.shuffle(buffer);               //mix arrays elements
        dush.addAll(buffer);
    }

    public List<Card> giveFiveCardToPlayer() throws LowCardInDechException {     //метод раздает игроку пять карт
        if (dush.size() < 5) throw new LowCardInDechException();
        List<Card> playerCard = new ArrayList<>();
        for (int i = 0; i >= 5; i++) {
            playerCard.add(dush.poll());                                //метод возращает и удаляет эхлемент и очереди
        }
        return playerCard;
    }

    public Card giveCard() throws LowCardInDechException {
        if (dush.size() < 5) throw new LowCardInDechException();
        return dush.poll();
    }
}
