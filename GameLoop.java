import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {
    public static void main(String[] args) {

        // This is a "flag" to let us know when the loop should end
        boolean stillPlaying = true;

        // We'll use this to get input from the user.
        Scanner userInput = new Scanner(System.in);

        // Storage for user's responses
        String userResponse = "";

        // This could be replaced with a more interesting opening
        System.out.println("******************");
        System.out.println("WELCOME TO MY GAME");
        System.out.println("******************");

        // Instructions are sometimes helpful
        System.out.println("Input username: ");
        System.out.println(instructions());
        String baseHood = startLocation();
        ArrayList<String> reaction = startRxn();
        String name = userInput.nextLine(); 
        Chemist response = new Chemist(name, reaction, baseHood);
        String currentLocation = baseHood;
        // The do...while structure means we execute the body of the loop once before checking the stopping condition
        do {
            // ************************************************
            // The stuff that happens in your game will go here
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            System.out.println("You need to gather: " + reaction.toString() + ". Would you like to explore? (Yes/No)");
            String inputOne = userInput.nextLine().toLowerCase();
            if (yesNo(inputOne)) {
                moveLabs(currentLocation);
            } 

            System.out.println("You are still playing. Follow the instructions if you want to win/lose...");
            userResponse = userInput.nextLine().toUpperCase();


            // ***********************************************************************
            // And as the player interacts, you'll check to see if the game should end
            //  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
            if (checkHood() || userResponse.equals("exit")) {
                stillPlaying = false;
            }
        } while (stillPlaying);

        // Tidy up
        userInput.close();

        // Once you exit the loop, you may need to deal with various possible stopping conditions
        if (userResponse.equals("WIN")) {
            System.out.println("Yay, you won!");
        } else { // userResponse.equals("LOSE")
            System.out.println("Better luck next time.");
        }

    }

}