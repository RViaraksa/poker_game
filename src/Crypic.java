public class Crypic {
    /**
     * Класс купурье
     * 1)Создать колоду
     * 2)Создать игроков, при этом их не д.б. больше 8
     * 3) Раздать карты всем игрокам
     * 4)вывести результат - кто выиграл
     */
    public static void main(String[] args) {
        Dech dech = new Dech();
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        try {
            p1.setCards(dech.giveFiveCardToPlayer());
            p2.setCards(dech.giveFiveCardToPlayer());
            p3.setCards(dech.giveFiveCardToPlayer());
        } catch (LowCardInDechException e) {
            e.printStackTrace();
        }
        Combination cp1 = new Combination();                                        //создать поле Combination в player и 
        Combination cp2 = new Combination();                                        //выполнятьвызов метода returnCombination из самого player
        Combination cp3 = new Combination();
        try {
           cp1.returnCombination(p1.getCards());
           cp2.returnCombination(p2.getCards());
           cp3.returnCombination(p3.getCards());


        } catch (CrowedLimitCardException e) {
            e.printStackTrace();
        }

    }
}
