package poon.ssc.zork;

import java.util.Scanner;

public class Game {

    public void start(){
        System.out.println("Game started");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String rawInput = scanner.nextLine();
            System.out.println("You entered string " + rawInput);
            if("exit".equalsIgnoreCase(rawInput)){
                break;
            }
        }
        scanner.close();
    }
}
