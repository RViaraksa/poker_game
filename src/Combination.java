import java.util.*;

public class Combination {
    private CombinationName combinationName;
    private int weight;

    /**
     *Мутод определяет комбинацию и в случае того, если у нас может возникнуть конфлик
     * (две пары против двух пар), записывает значение старшей карты в поле weight
     */
    public void returnCombination (List<Card> cardList) throws CrowedLimitCardException {
        boolean flag = true;                                                     //флажок для входа в цикл 2
        if (cardList.size() > 6) throw new CrowedLimitCardException();

        /*цикл 1
        вызываются аннимные методы класса возращающие булевские значения при нахождении
        1)всех карт у игрока одинаковой масти
        2)все карты последовательны 5,6,7..
        3)возращает значение наибольшей карты
        * */
        if (checkSuit(cardList)){
            flag = false;
            if (findSequence(cardList)){
                switch (cheeckBiggestWeight(cardList)){

                    case  ACE:
                        this.combinationName = CombinationName.FLUSH_ROYAL;
                        break;
                    default:
                        this.combinationName = CombinationName.STRAIGHT_FLUSH;
                }
            }
            else this.combinationName = CombinationName.FLUSH;
        }

        /*цикл 2*/
        if (findSequence(cardList)){
            flag = false;
            this.combinationName = CombinationName.STRAIGHT;
        }

        /*цикл 3*/
        if (flag) {
            block:{
                flag = false;
                for (Map.Entry<Integer, Integer> entry : checkWeight(cardList).entrySet()) {             //специальный метод entrySet() перебирает все элементы мэпы
                    /**у игрока находиться карта одного значения в 2-ух и более экземплярах (2...4X)*/
                    if (checkWeight(cardList).size() == 1) {
                        this.weight = entry.getKey();                               //велечину карты передаем числом в приватное поле
                        switch (entry.getValue()) {                                 // свитч на количество совпавших карт
                            case 2:
                                this.combinationName = CombinationName.PAIR;
                                this.weight = entry.getValue();
                                break;
                            case 3:
                                this.combinationName = CombinationName.THREE_OF_KIND;
                                this.weight = entry.getValue();
                                break;
                            case 4:
                                this.combinationName = CombinationName.FOUR_A_KIND;
                                this.weight = entry.getValue();
                                break;
                        }
                    }
                    /**у игрока 2 значения карт где 2 и более совпадения : (2Y 2X) (2Y 3X)*/
                    else if (checkWeight(cardList).size() == 2) {
                        if (entry.getValue() == 2) {
                            this.combinationName = CombinationName.TWO_PAIR;
                            if (this.weight < entry.getValue()) this.weight = entry.getValue();
                        }
                        if (entry.getValue() == 3) {                                             //в эту часть цикла программа зайдёт только при наличии 3 карт, при этом перезапишет имя комбинации
                            this.combinationName = CombinationName.FULL_OF_HOUSE;
                            if (this.weight < entry.getValue()) this.weight = entry.getValue();
                            break block;                                                         //если попали сразу на элемент мэпы хранящий 3 карты то записываем название комбинации и закрываем блок, прим. смотри перед гл. if()
                        }
                    }
                }
            }
        }

        /*цикл 4
        * если ничего не прошло смотрим по старшей  карте*/
        if (flag) {
            this.weight = cheeckBiggestWeight(cardList).getWeight();
            this.combinationName = CombinationName.HIGH_CARD;
            flag = false;
        }


    }
    /**
     * метод принимающий массив карт, проверяет его на наличие
     * их принадлежности к одной масти, если это оказывается истинной
     * то возращается true
     *
     * @param cardList
     * @return
     */
    private boolean checkSuit(List<Card> cardList) {
        boolean res = true;
        Suit defSuit = cardList.get(0).suit;
        for (Card i : cardList) {
            if (i.suit.getSuitName() != defSuit.getSuitName())
                res = false;
        }
        return res;
    }

    /**
     * метод принимает лист карт, и возращает мэпу содержащую в себе
     * информацию о сопадениях карт одних занчений, в поле key - значение,
     * в поле weight - содержится информация о количестве совпадений
     *
     * @param cardList
     * @return
     */
    private Map<Integer, Integer> checkWeight(List<Card> cardList) {
        Map<Integer, Integer> res = new HashMap<>();
        List<Card> copyList = new ArrayList<>(cardList);
        for (int j = 0; j < copyList.size(); j++) {
            for (int i = j + 1; j < copyList.size(); j++) {
                int count = 1;
                if (copyList.get(j).equals(copyList.get(i))) {
                    count++;                                                    //счётчик считает количество совпадений карт одного значения
                    res.put(copyList.get(j).cardWeight.getWeight(),count);
                    copyList.remove(i);
                }
            }
        }
        return res;
    }


    /**
     * метод возращает старшую карту из листа
     *
     * @param cardList
     * @return
     */
    private Weight cheeckBiggestWeight(List<Card> cardList) {
        Weight res = cardList.get(0).cardWeight;
        for (Card i : cardList) {
            if (i.cardWeight.getWeight() > res.getWeight())
                res = i.cardWeight;
        }
        return res;
    }


    /**
     * метод находит последовательнсть карт из листа и если нашел возращает true
     *
     * @param cardList
     * @return
     */
    private boolean findSequence(List<Card> cardList) {
        boolean res = false;
        /**
         cardList.sort((Card c1, Card c2) ->
         c2.getCardWeight().getWeight() - c1.getCardWeight().getWeight() );
         //c1.getCardWeight().getWeight().compareTo(c2.getCardWeight().getWeight())
         */
        /**
         Collection.sort(cardList , (c1, c2) ->
         c2.getCardWeight().getWeight() - c1.getCardWeight().getWeight() );
         //c1.getCardWeight().getWeight().compareTo(c2.getCardWeight().getWeight())
         */
        cardList.sort((Card c1, Card c2) ->
                c2.getCardWeight().getWeight() - c1.getCardWeight().getWeight());
       /* int[] array = new int[5];
        int j = 0;
        for (Card i : cardList) {
            array[j] = i.cardWeight.getCardWeight();
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
            }
        }*/
       /* if ((array[4] - array[0]) == 4) res = true;*/
        if ((cardList.get(4).cardWeight.getWeight() -
                cardList.get(0).cardWeight.getWeight()) == 4) {
            res = true;
        }
        return res;
    }

    /**
     cardList.sort(Comparator.comparing(Integer::CardWeight.getCardWeight).reversed());
     или 
     Collections.sort(testList, (a, b) -> b.compareTo(a));
     Comparator<Double> comp = (Double a, Double b) -> {
     return b.compareTo(a);
     };
     https://overcoder.net/q/22564/как-отсортировать-arraylist
     */


    /**
     * чисто рабочий метод для его использования в findSequence, т. к. временный массив
     * значений карт мне лень хранить в листе, потому что прри сортировке нужно
     * реализовать Comparable))))
     *
     * @param array
     * @param ind1
     * @param ind2
     */
   /* private void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }*/
}
