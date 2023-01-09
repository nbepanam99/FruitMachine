package com.fruitmachine;

import java.util.Scanner;

public class Main {

    private static FruitMachine fruitMachine;

    public static void main(String[] args) {

        fruitMachine = new FruitMachine();
        Scanner scanner = new Scanner(System.in);
        int selection;

        var msg = """
                ---------------------------------
                   -- Play ?
                   -- 1 Yes
                   -- 2 Quit
                """;

        do {
            System.out.println(msg);
            selection = scanner.nextInt();
            switch (selection) {
                case 1 -> {
                    System.out.println("Launch the game ...");
                        fruitMachine.launch();
                }

                default -> System.out.println("Quit");
            }

        } while (selection < 2);

        System.out.println(msg);

    }


}
