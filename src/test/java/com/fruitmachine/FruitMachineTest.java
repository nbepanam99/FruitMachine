package com.fruitmachine;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Fruit Machine")
class FruitMachineTest {

    FruitMachine fruitMachine = new FruitMachine();

    @Test
    @DisplayName("Test loose case")
    public void shouldReturnLoose(){

        // Given
        List<Integer> result = List.of(2,1,3);

        // When
        GameResult gameResult = fruitMachine.getScore(result);

        // Then
        assertFalse(gameResult.win());
        System.out.println(gameResult.messageResult());

    }

    @Test
    @DisplayName("Test 2 same figures")
    public void shouldReturnWinWith2SameFigures(){

        // Given
        List<Integer> result = List.of(1,2,2);

        // When
        GameResult gameResult = fruitMachine.getScore(result);

        // Then
        assertTrue(gameResult.win());
        System.out.println(gameResult.messageResult());

    }

    @Test
    @DisplayName("Test 2 same figures and wild")
    public void shouldReturnWinWith2SameFiguresAndWild(){

        // Given
        List<Integer> result = List.of(10,9,9);

        // When
        GameResult gameResult = fruitMachine.getScore(result);

        // Then
        assertTrue(gameResult.win());
        System.out.println(gameResult.messageResult());

    }

    @Test
    @DisplayName("Test 3 same figures")
    public void shouldReturnWinWith3SameFigures(){

        // Given
        List<Integer> result = List.of(2,2,2);

        // When
        GameResult gameResult = fruitMachine.getScore(result);

        // Then
        assertTrue(gameResult.win());
        System.out.println(gameResult.messageResult());

    }

    @Test
    @DisplayName("Test 3 Wild figures")
    public void shouldReturnWinWith3WildFigures(){

        // Given
        List<Integer> result = List.of(10,10,10);

        // When
        GameResult gameResult = fruitMachine.getScore(result);

        // Then
        assertTrue(gameResult.win());
        System.out.println(gameResult.messageResult());

    }

    @Test
    @DisplayName("Performance test")
    @Disabled
    public void runUntilSpecificValue(){

        // Given
        Instant start = Instant.now();
        var max=10000;
        var result = 0;
        var distribution = new HashMap<Integer, Integer>();

        // When
        while (result < max){
           GameResult launch = fruitMachine.launch();
           result += launch.score();
           if (distribution.get(launch.score()) != null ){
               distribution.replace(launch.score(), distribution.get(launch.score()) + 1);
           } else {
               distribution.put(launch.score(), 1);
           }
           System.out.println(result);

        }
        System.out.println(" --- End of game ");
        System.out.println(" --- Result:  " + distribution);

        System.out.println("\nSorted Map......By Key");
        Map<Integer, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());
        treeMap.putAll(distribution);

        printMap(treeMap);
        Instant end = Instant.now();
        System.out.println( "-- Duration in s --");
        System.out.println(Duration.between(start, end).getSeconds());

    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Score : " + entry.getKey()
                    + " Number : " + entry.getValue());
        }
    }


}