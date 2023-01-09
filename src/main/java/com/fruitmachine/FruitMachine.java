package com.fruitmachine;

import java.util.*;

public class FruitMachine {
    public GameResult launch() {

        List<Integer> reelsResult = getReelsResult();

        GameResult score = getScore(reelsResult);

        System.out.println(score.messageResult());
    return score;
    }

    private List<Integer> getReelsResult() {
        Random random = new Random();

        var max = 10;
        var min = 1;

        int firstReel = random.nextInt(max - min + 1) + min;
        int secondReel = random.nextInt(max - min + 1) + min;
        int thirdReel = random.nextInt(max - min + 1) + min;

        List<Integer> reelsResult = List.of(firstReel, secondReel, thirdReel);
        return reelsResult;
    }

    public GameResult getScore(List<Integer> reelsResult) {
        String messageResult = null;
        Integer firstReel = reelsResult.get(0);
        Integer secondReel = reelsResult.get(1);
        Integer thirdReel = reelsResult.get(2);

        System.out.println("Result: \n     " + getItemValue(reelsResult.get(0))
                + " " + getItemValue(reelsResult.get(1))
                + " " + getItemValue(reelsResult.get(2)));


        if (firstReel == secondReel && secondReel == thirdReel) {
            messageResult = "You win "+ reelsResult.get(0) * 10+" \uD83C\uDF89\uD83C\uDFC6 ";
            return new GameResult(reelsResult.get(0)*10, true, messageResult);
        } else {

            Set set = new HashSet();
            set.add(firstReel);
            boolean doubleCard = false;

            int doubleValue = firstReel;

            if (set.contains(secondReel)) {
                doubleCard = true;
                doubleValue = secondReel;
            } else {
                set.add(secondReel);
            }

            if (set.contains(thirdReel)) {
                doubleCard = true;
                doubleValue = thirdReel;
            } else {
                set.add(thirdReel);
            }

            if (set.size() == 3) {
                messageResult = "You loose ¯\\_(ツ)_/¯ ";
                return  new GameResult(0, false, messageResult );
            }

            if (doubleCard && set.contains(10)) {
                messageResult = "You win " + doubleValue * 2 + " \\( ﾟヮﾟ)/ \uD83C\uDFC6 ";
                return new GameResult(doubleValue * 2, true, messageResult);
            } else if (doubleCard) {
                messageResult = "You win " + doubleValue + " \\( ﾟヮﾟ)/  \uD83C\uDF89";
                return new GameResult(doubleValue , true, messageResult);
            }


        }
        return new GameResult(0,false, "Error !!!");
    }

    public static String getItemValue(int key) {
        Map<Integer, String> itemValue = Map.of(
                10, "Wild",
                9, "Star",
                8, "Bell",
                7, "Shell",
                6, "Seven",
                5, "Cherry",
                4, "Bar",
                3, "King",
                2, "Queen",
                1, "Jack"
        );
        return itemValue.get(key);
    }
}




