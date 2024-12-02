import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AllCombinationsPossible {
    /**
     * ASSUMPTIONS:
     * Mountain Top is the last field
     * Fields do not repeat
     */
    public static void main(String[] args){
        // All fields, excluding mountain top
        ArrayList<String> fields = new ArrayList<>(Arrays.asList("sunflower", "dandelion", "mushroom", "blue flower", "clover",
                "strawberry", "spider", "bamboo", "pineapple", "stump",
                "cactus", "pumpkin", "pine tree", "rose", "pepper", "coconut"));

        ArrayList<ArrayList<String>> validCombos = new ArrayList<>();

        genPermutations(fields, 4, new ArrayList<>(), validCombos);

        ArrayList<String> fmtValidCombos = new ArrayList<>();

        for(ArrayList<String> combo : validCombos){
            combo.add("mountain top");
            String line = String.join(", ", combo);
            fmtValidCombos.add(line);
        }

        String content = String.join("\n", fmtValidCombos);

        try(FileWriter writer = new FileWriter("allCombos.txt")) {
            writer.write(content);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Use recursion
    private static void genPermutations(
            ArrayList<String> fields, int length, ArrayList<String> current, ArrayList<ArrayList<String>> validCombos){

        if(current.size() == length) {
            validCombos.add(new ArrayList<>(current));
            return;
        }

        for(String field : fields){
            // No dupes
            if(!current.contains(field)){
                current.add(field);
                genPermutations(fields, length, current, validCombos);
                current.remove(current.size() - 1);
            }
        }
    }
}
