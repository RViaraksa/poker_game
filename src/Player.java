import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        List<Card> copy= new ArrayList<>(cards);
        return copy;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
