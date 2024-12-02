import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ComboChecker {
    public static void main(String[] args) throws IOException {
        // change this to smth more accessible
        Scanner scan = new Scanner(System.in);

        System.out.println("What are your combo(s)? Separate each with a newline. And end with \".\".");
        System.out.println("Example input: ");
        System.out.println("mushroom, pineapple, coconut, pine tree, mountain top\n" +
                "mushroom, pineapple, coconut, rose, mountain top\n" +
                "mushroom, pineapple, coconut, pepper, mountain top\n" +
                ".");

        ArrayList<String> combos = new ArrayList<>();

        while(true){
            String input = scan.nextLine();
            if(input.equals(".")) {
                break;
            }
            // add a check for 4 fields and add in top at the end

            if(input.split(", ").length != 5) {
                System.out.println("Invalid input! Requires 5 fields.");
            }

            // add a check if all the fields are real fields and do not repeat

            combos.add(input);
        }
        String triedCombos = Files.readString(Path.of("tried_combinations.txt"));

        for(String combo : combos){
            if(triedCombos.contains(combo)){
                System.out.println("Already contains: ");
                System.out.println(combo);
            }
        }
    }
}
