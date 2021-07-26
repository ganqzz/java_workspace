package demo.strings;

import java.util.StringTokenizer;

public class TokenizerDemo {
    public static void main(String[] args) {
        //given two Strings
        String colors = "red|orange|yellow|green|blue|indigo|violet";
        String players = "John David Mike Jerry Sally Tina Jenni";

        StringTokenizer allColors = new StringTokenizer(colors, "\\|");
        while (allColors.hasMoreTokens()) {
            System.out.println("COLOR: " + allColors.nextToken());
        }
        StringTokenizer allPlayers = new StringTokenizer(players);
        while (allPlayers.hasMoreTokens()) {
            System.out.println("PLAYER: " + allPlayers.nextToken());
        }
    }
}
