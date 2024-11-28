import java.util.*;

import javax.management.RuntimeErrorException;
public class Room {
    protected String name;
    protected ArrayList<String> inventory;
    Hashtable<String, ArrayList<String>> connect;

    String[] acceptedResponses = {
        "shea","buck","strom","queeney","gorin","teaching","hallway","1", 
        "2","3", "4", "grab", "drop", "add", "move", "go", "yes", "no", "help", "exit"
    };
    
    Scanner scanner = new Scanner(System.in);
    public Room (String name, ArrayList<String> inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public void roomInfo (String name, ArrayList<String> inventory) {
        System.out.println("You are in " + this.name + " lab. This lab connects to the" + connects(name));
        System.out.println("This lab contains: " + this.inventory.toString());
    }

    public void connected(Hashtable<String, ArrayList<String>> connect, String room, ArrayList<String> connecting) {
        this.connect.put(room, connecting);
    }

    public String connects(String name) {
        if (name.contains("Shea")) {
            return "Buck Lab on your left and Gorin Lab on your right.";
        }
        if (name.contains("Gorin")) {
            return "Shea Lab on your left and Queeney Lab on your right.";
        }
        if (name.contains("Queeney")) {
            return "Gorin Lab on your left.";
        }
        if (name.contains("Buck")) {
            return "Strom Lab on your left and Shea Lab on your right.";
        }
        if (name.contains("Strom")) {
            return "Buck Lab on your right.";
        }
        if (name.contains("hallway")) {
            return " Shea Lab in front of you and teaching labs behind you.";
        }
        if (name.contains("teaching")) {
            return " hallway in front of you.";
        }
        return "Error";
    }

    public boolean yesNo(String response) {
        while (true) {
            response = response.toLowerCase();
            if (response.contains("yes")) {
                return true;
            } 
            if (response.contains("no")) {
                return false;
            } else {
                System.out.println("Response not accepted. Please try again (Yes or No): ");
                continue;
            }
        }
    }

    public String checkResponse(String response) {
        String savedResponse = "";
        String[] splitResponse = response.split(" ");
        for(int i = 0; i < acceptedResponses.length; i++) {
            for (int j = 0; j < splitResponse.length; j++){
            if(acceptedResponses[i].contains(splitResponse[j])) {
                savedResponse = savedResponse + " " + splitResponse[j];
            }
            }
        }
        if (savedResponse.contains("exit")) {
            throw new RuntimeException("Ending game now.");
        }
        if (savedResponse.contains("help")) {
            instructions();
        }
        return savedResponse;
    }

    public void instructions() {
        System.out.println("INSTRUCTIONS HERE");
    }

    public boolean moveLabs(String currentLocation) {
        while (true) {
            System.out.println("This lab connects to " + connects(currentLocation) + ". What lab would you like to move to? ");
            String response = scanner.nextLine().toLowerCase(); 
            String[] connectsLabs = connects(currentLocation).split(" ");
            String[] responseLab = response.split(" ");
            for (int i = 0; i < responseLab.length; i++) {
                for (int j = 0; j < connectsLabs.length; j++) {
                    if (responseLab[i].contains(connectsLabs[j])) {
                        currentLocation = responseLab[i] + " " + 1;
                        System.out.println("Successful move. You are currently located at " + responseLab[i] + " at hood One.");
                        moveHoods(currentLocation);
                        return true;
                    }
                }
            }
            System.out.println("You cannot move there. Try again");
            continue;
        }
    }

    public String moveHoods(String currentLocation) {
        System.out.println("Would you like to go to a different hood?");
        String response = scanner.nextLine().toLowerCase();
        while (true) {
            if (yesNo(response)) {
                System.out.println("Your options are: 1, 2, 3, 4");
                int moveHood = scanner.nextInt();
                scanner.nextLine();
                for (int i = 1; i <= 4; i++) {
                    if (moveHood == i) {
                        currentLocation = currentLocation + " " + i;
                        System.out.println("The inventory of this hood is " + hoods.getOrDefault(currentLocation, false) + ".");
                        System.out.println("Would you like to pick something up? ");
                        response = scanner.nextLine();
                        if (yesNo(response)) {
                            pickUp(currentLocation);
                        } else {
                            System.out.println("Would you like to move hoods"); 
                            response = scanner.nextLine();
                            if (yesNo(response)) {
                                continue;
                            }
                        } 
                        System.out.println("Would you like to move to another lab? ");
                        response = scanner.nextLine();
                        if (yesNo(response)) {
                            moveLabs(currentLocation);
                        }
                        return currentLocation;
                    }
                }
            } 
        }
    }

    public void pickUp(String hoodIdentifier){
        System.out.println("Would you like to pick something up? (Yes/No)");
        String response2 = scanner.nextLine().toLowerCase();
        while (true) {
            if (yesNo(response2)) {
                System.out.println("What would you like to pick up?");
                String response3 = scanner.nextLine().toLowerCase();
                String[] responseList = response3.split(" ");
                for (int l = 1; l <= 4; l++) {
                    for (int n = 1; n <= 4; n++) {
                        if (responseList[n] == hoods.getOrDefault(hoodIdentifier, false)[l]) {
                            hoods.getOrDefault(hoodIdentifier, false).remove[l];
                            addtoCarrying(this.carrying, responseList[n]);
                        }
                    }
                }
                System.out.println("Would you like to pick up anything else? ");
                response2 = scanner.nextLine();
                if (yesNo(response2)) {
                    continue;
                } else {
                    System.out.println("Would you like to move to another hood?");
                    response2 = scanner.nextLine();
                    if (yesNo(response2)) {
                        moveHoods(hoodIdentifier);
                    }
                    System.out.println("Would you like to move to another lab?");
                    response2 = scanner.nextLine();
                    if (yesNo(response2)) {
                        moveLabs(hoodIdentifier);
                    }
                }
                
            }
        }
    }
}

