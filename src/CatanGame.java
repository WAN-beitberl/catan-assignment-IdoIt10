import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

public class CatanGame {
    private static ArrayList<Player> players = new ArrayList<Player>();;
    private static int currentPlayerIndex;
    private Board board;

    public CatanGame() {
        board = new Board();
        //board.generateBoard();
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        /*System.out.println("Enter num of players: ");
        int numOfPlayers = sc.nextInt();
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Enter name for player " + (i + 1) + ": ");
            String name = sc.next();
            System.out.println("Enter color for player " + (i + 1) + " (red, blue, green, yellow): ");
            String color = sc.next();

            Color playerColor;
            if (color.equalsIgnoreCase("red")) {
                playerColor = Color.RED;
            } else if (color.equalsIgnoreCase("blue")) {
                playerColor = Color.BLUE;
            } else if (color.equalsIgnoreCase("green")) {
                playerColor = Color.GREEN;
            } else if (color.equalsIgnoreCase("yellow")) {
                playerColor = Color.YELLOW;
            } else {
                playerColor = Color.BLACK;
            }

            Player player = new Player(name, playerColor);
            //player.setSettlements();
            //player.set();
            addPlayer(player);
        }*/
        System.out.println("Starting Catan Game...");

        JPanel panel = new JPanel(new GridLayout(300, 300));
        JFrame frame = new JFrame("Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Board());
        frame.pack();
        frame.setVisible(true);

        while (!checkWinner()) {
            for (Player player : players) {
                System.out.println("\nPlayer " + player.getName() + " turn: ");
                rollDice(player);
                // Player can choose to build a settlement or upgrade a settlement
                System.out.println("Do you want to build a settlement? (y/n)");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("y")) {
                    buildSettlement(player);
                } else {
                    System.out.println("Do you want to upgrade a settlement to a city? (y/n)");
                    choice = sc.next();
                    if (choice.equalsIgnoreCase("y")) {
                        upgradeSettlement(player);
                    }
                }
                endTurn(player);
            }
        }
        System.out.println("\nCongratulations! Player " + players.get(0).getName() + " has won the game!");
    }

    public static int rollDice(Player p) {
        int dice1 = (int) (Math.random() * 6 + 1);
        int dice2 = (int) (Math.random() * 6 + 1);
        int roll = dice1 + dice2;
        System.out.println("You rolled a " + roll);
        return roll;
    }

    public static void buildSettlement(Player player) {
        int settlements = player.getSettlements();
        if (settlements < 5) {
            HashMap<String, Integer> resources = player.getResources();
            if (resources.get("WOOD") >= 1 && resources.get("BRICK") >= 1 &&
                resources.get("WHEAT") >= 1 && resources.get("WOOL") >= 1){

                resources.replace("WOOD", resources.get("WOOD"), resources.get("WOOD") - 1);
                resources.replace("BRICK", resources.get("BRICK"), resources.get("BRICK") - 1);
                resources.replace("WHEAT", resources.get("WHEAT"), resources.get("WHEAT") - 1);
                resources.replace("WOOL", resources.get("WOOL"), resources.get("WOOL") - 1);

                player.setResources(resources);
                player.setSettlements(settlements + 1);

                System.out.println(player.getName() + " has built a settlement.");
            } else {
                System.out.println("You do not have enough resources to build a settlement.");
            }
        } else {
            System.out.println("You have reached the maximum number of settlements.");
        }
    }

    public static void upgradeSettlement(Player player) {
        int Citys = player.getCities();
        if (Citys < 5) {
            HashMap<String, Integer> resources = player.getResources();
            if (resources.get("ORE") >= 3 && resources.get("WHEAT") >= 2){

                resources.replace("WHEAT", resources.get("WHEAT"), resources.get("WHEAT") - 2);
                resources.replace("ORE", resources.get("ORE"), resources.get("ORE") - 3);

                player.setResources(resources);
                player.setCities(Citys + 1);

                System.out.println(player.getName() + " has built a city.");
            } else {
                System.out.println("You do not have enough resources to build a city.");
            }
        } else {
            System.out.println("You have reached the maximum number of citys.");
        }
    }

    public static void endTurn(Player player) {
        System.out.println(player.getName() + " has ended their turn.");
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        System.out.println("It is now " + players.get(currentPlayerIndex).getName() + "'s turn.");
    }


    public static boolean checkWinner() {
        for (Player player : players) {
            if (player.getVictoryPoints() >= 10) {
                return true;
            }
        }
        return false;
    }

}