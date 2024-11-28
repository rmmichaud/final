import java.util.*;
import java.util.ArrayList;
//import javax.management.RuntimeErrorException;
public class Chemist {
    String chemist;
    ArrayList<String> reaction;
    ArrayList<String> hoodInventory;
    String location;
    String baseHood;
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> grignard = new ArrayList<>();
    ArrayList<String> sonogashira = new ArrayList<String>();
    ArrayList<String> dielsAlder = new ArrayList<String>();
    grignard.add();
    grignard.add("chemical2");
    grignard.add("chemical1");
    grignard.add("chemical2");
    grignard.add("chemical1");
    grignard.add("chemical2");
    grignard.add("chemical1");
    grignard.add("chemical2");
    sonogashira.add("chemical1");
    sonogashira.add("chemical2");
    sonogashira.add("chemical3");
    sonogashira.add("chemical1");
    sonogashira.add("chemical2");
    sonogashira.add("chemical3");
    dielsAlder.add("chemical1");
    dielsAlder.add("chemical1");
    dielsAlder.add("chemical1");
    dielsAlder.add("chemical1");
    dielsAlder.add("chemical1");
    dielsAlder.add("chemical1");

    public Chemist (String chemist, ArrayList<String> reaction, String baseHood) {
        this.chemist = chemist;
        this.baseHood = baseHood;
        this.hoodInventory = new ArrayList<String>();
        this.reaction = reaction;
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

    public String startLocation() {
        while (true) {
            System.out.println("Where would you like to set up your reaction? Lab options: Shea, Gorin, Buck, Strom, Queeney.");
            String lab = scanner.nextLine().toLowerCase();
            if (lab.contains("shea") || lab.contains("strom") || lab.contains("queeney") || lab.contains("gorin") || lab.contains("buck")) {
                System.out.println("Great! You will be in the " + lab + " lab! What fume hood would you like to be in? Options: 1, 2, 3, 4");
                String hoodStation = scanner.nextLine(); 
                if (hoodStation.contains("1") || hoodStation.contains("2") || hoodStation.contains("3") || hoodStation.contains("4") ) {
                    System.out.println("Great! You will be in the " + lab + " at hood " + hoodStation + ".");
                    String base = lab + " " + hoodStation;
                    return base;
                } else {
                    System.out.println("That was not an accepted answer. Please try again");
                    continue;
                }
            } else {
                System.out.println("Not one of the options. Please try again. ");
                continue;
            }
        }
        return "error";
    }

    public ArrayList<String> startRxn () {
        while (true) {
            System.out.println("What reaction would you like to run? Options: Grignard, Sonogashira, Diels-Alder. ");
            String rxn = scanner.nextLine().toLowerCase();
            if (rxn.contains("grignard")) {
                System.out.println("You will need: X, X, X, X");
                return grignard;
            }
            if (rxn.contains("sonogashira")) {
                System.out.println("You will need: X, X, X, X");
                return sonogashira;
            }
            if (rxn.contains("diels-alder")) {
                System.out.println("You will need: X, X, X, X");
                return dielsAlder;
            } else {
                System.out.println("Sorry, that was not an option. Please enter a new response!");
                continue;
            }
        }
    }

    public boolean addtoHood(ArrayList<String> carrying, ArrayList<String> hoodInventory, String location, String baseHood) {
        if (location == baseHood) {
            System.out.println("Would you like to add the items you are carrying to your hood inventory? ");
            String response = scanner.nextLine().toLowerCase();
            if (yesNo(response)) {
                for (int k = 0; k < carrying.size(); k++){
                    for (int h = 0; h < this.reaction.size(); h++) {
                        if (this.reaction.get(k).contains(carrying.get(h))) {
                            this.reaction.remove(k);
                            if(this.reaction.isEmpty()){
                                stillPlaying = false;
                            }
                        }
                    }
                    hoodInventory.add(carrying.get(k));
                    carrying.remove(k);
                }

                System.out.println("You are now carrying " + carrying.toString());
                System.out.println("Your hood inventory now contains " + hoodInventory.toString());
            }
            if (!yesNo(response)) {
                System.out.println("XXXX");
            }
        } 
    }
    
    public void addtoCarrying(ArrayList<String> carrying, String item) {
        if (carrying.size() > 4) {
            System.out.println("You cannot carry anything else! Drop an item, or return to your base hood and put your items down. Would you like to drop or return?");
            String response = scanner.nextLine().toLowerCase();
            if (response.contains("drop")) {
                drop(carrying);
            } 
            if (response.contains("return")) {
                moveLabs(this.location);
            }
        } else {
            carrying.add(item);
        }
    }
    
    public boolean drop(ArrayList<String> carrying) {
        while (true) {
            System.out.println("What would you like to drop? ");
            String item = scanner.nextLine().toLowerCase();
            for (int i = 0; i < carrying.size(); i++) {
                if (carrying.get(i).contains(item)) {
                    carrying.remove(i);
                    return true;
                }    
            }
            return false;
        }
    } 
    
    public boolean checkHood() {
        if (this.reaction.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}


