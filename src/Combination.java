import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combination {
    private String name;
    private int weight;

    /**
     * метод принимающий массив карт, проверяет его на наличие
     * их принадлежности к одной масти, если это оказывается истинной
     * то возращается true
     * @param cardList
     * @return
     */
    private boolean checkSuit(List<Card> cardList){
        boolean res = true;
        Suit defSuit = cardList.get(0).suit;
        for (Card i: cardList) {
            if (i.suit.getSuitName() != defSuit.getSuitName())
                res = false;
                break;
        }
        return res;
    }

    /**
     * метод принимает лист карт, и возращает мэпу содержащую в себе
     * информацию о сопадениях карт одних занчений, в поле key - значение,
     * в поле weight - содержится информация о количестве совпадений
     * @param cardList
     * @return
     */
    private Map<Integer, Integer> checkWeight(List<Card> cardList){
        Map<Integer, Integer> res = new HashMap<>();
        List<Card> copyList = new ArrayList<>(cardList);
        for (int j=0; j < copyList.size(); j++){
            for (int i = j+1; j < copyList.size(); j++) {
                int count = 1;
                if (copyList.get(j).equals(copyList.get(i))) {
                    count++;                                                    //счётчик считает количество совпадений карт одного значения
                    res.put(copyList.get(j).cardWeight.getCardWeight(), count);
                    copyList.remove(i);
                }
            }
        }
        return res;
    }


    /**
     * метод возращает старшую карту из листа
     * @param cardList
     * @return
     */
    private int cheeckBiggestWeight(List<Card> cardList){
        int res = cardList.get(0).cardWeight.getCardWeight();
        for (Card i: cardList) {
            if (i.cardWeight.getCardWeight() > res)
                res = i.cardWeight.getCardWeight();
        }
        return res;
    }


    /**
     * метод находит последовательнсть карт из листа и если нашел возращает true
     * @param cardList
     * @return
     */
    private boolean findSequence(List<Card> cardList){
        boolean res = false;
        int[] array = new int[5];
        int j = 0;
        for (Card i:cardList) {
            array[j] = i.cardWeight.getCardWeight();
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i-1);
            }
        }
        if ((array[4] - array[0]) == 4) res = true;
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
     * @param array
     * @param ind1
     * @param ind2
     */
    private void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
