package com.mp1;

import java.util.Scanner;

public class Battleship {
    static String[][] oceanMap = new String[10][10];


    public static void main(String[] args) {
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty");
        System.out.println();
        printMap();
        deployPlayerShip();
        printMap();
        deployComputerShip();
        printMap();
        battle();
        declareWinner();




    }
    public static void printMap(){
        printTopRow();
        printRows();
        printTopRow();


    }
    public static void printTopRow(){
//        System.out.println("  0123456789  ");
        System.out.print("  ");
        for (int j=0; j<oceanMap[0].length; j++){       // Col
            System.out.print(j);
        }
        System.out.println("  ");
    }
    public static void printRows(){
        // print the index from 0 to last index

        for(int i=0; i<oceanMap.length; i++ ){      // Row
            System.out.print(i + "|");
            for (int j=0; j<oceanMap[i].length; j++){   // Col
                if (oceanMap[i][j] == null)
                    System.out.print(" ");
                else if(oceanMap[i][j] == "1")
                    System.out.print("@");
                else if (oceanMap[i][j] == "2")          // Computer Ship Location
                    System.out.print(" ");
                else
                    System.out.print(oceanMap[i][j]);

            }
            System.out.println( "|" + i);
        }

    }
    public static void deployPlayerShip(){
        int shipCount = 1;
        do {
            System.out.print("Enter X coordinate for your ship:");
            int x = getLocation();
            System.out.print("Enter Y coordinate for your ship:");
            int y = getLocation();
            if (isFree(x, y))
                oceanMap[x][y] = "1";
            else
                System.out.println("The location is not free");
            ++shipCount;
        } while (shipCount < 6);

        shipCount++;

    }

    public static int getLocation(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();

    }

    public static boolean isFree(int x, int y){
        if (oceanMap[x][y] == null)
            return true;
        else
            return false;
    }


    public static void deployComputerShip(){
        int shipCount = 1;
        System.out.println();
        System.out.println("Computer is deploying ship");

        do {
// Math.random returns 0 to .99 only.
// While you are multiplying with 10, it returns 0 to 9 only
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if (isFree(x, y)){
                oceanMap[x][y] = "2";
                System.out.println(shipCount + ".ship DEPLOYED");
                ++shipCount;

            }else{
                System.out.println("The location is not free");
            }

        } while (shipCount < 6);
        System.out.println("-------------------------------------");

    }


    static int playerShips = 5;
    static int computerShips = 5;

    public static void battle() {
        System.out.println("YOUR TURN");
//
        do {
            // Player Turn
            // 1. guess computer's ship location
            System.out.print("Enter X coordinate:");
            int x = getLocation();
            System.out.print("Enter Y coordinate:");
            int y = getLocation();

            if (oceanMap[x][y] == "2") {
                System.out.println("Boom! You sunk the ship!");
                oceanMap[x][y] = "!";

                computerShips = computerShips - 1; // computership decreases by 1

            } else if (oceanMap[x][y] == "1") {
                System.out.println("Oh no, you sunk your own ship :(");
                oceanMap[x][y] = "x";

                playerShips = playerShips - 1; // playership decreases by 1


            } else {
                System.out.println("Sorry, you missed");
                oceanMap[x][y] = "-";
            }
            //printMap();
            // Computer turn
            System.out.println("COMPUTER'S TURN");
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);

            if (oceanMap[x][y] == "1") {
                System.out.println("The Computer sunk one of your ships!");
                oceanMap[x][y] = "x";

                playerShips = playerShips - 1; // playership decreases by 1

            } else if (oceanMap[x][y] == "2") {
                System.out.println("The Computer sunk one of its own ships");
                oceanMap[x][y] = "!";
            } else {
                System.out.println("Computer missed");
                System.out.println();
            }

            printMap();
            System.out.println();
            System.out.println("Your Ships: " + playerShips + " | Computers Ships: " + computerShips);
            System.out.println("---------------------------------------");

        }
        while (playerShips > 0 && computerShips > 0) ;
        //    declareWinner (playerShips,computerShips);

    }

    public static void declareWinner() {
                    // int playerShips, computerShips; {


        if (playerShips > 0 && computerShips <= 0) {
            System.out.println("Hooray!You won the battle! :) ");

        }else
            System.out.println("You lost the battles! ");

        System.out.println();


    }

}







