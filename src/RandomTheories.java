import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RandomTheories {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("valid_field_combinations.txt"));

        HashMap<String, Integer> fieldToZones = new HashMap<>();

        fieldToZones.put("sunflower", 0);
        fieldToZones.put("dandelion", 0);
        fieldToZones.put("mushroom", 0);
        fieldToZones.put("blue flower", 0);
        fieldToZones.put("clover", 0);

        fieldToZones.put("strawberry", 5);
        fieldToZones.put("spider", 5);
        fieldToZones.put("bamboo", 5);

        fieldToZones.put("pineapple", 10);
        fieldToZones.put("stump", 10);

        fieldToZones.put("cactus", 15);
        fieldToZones.put("pumpkin", 15);
        fieldToZones.put("pine tree", 15);
        fieldToZones.put("rose", 15);
        fieldToZones.put("mountain top", 25);
        fieldToZones.put("pepper", 35);
        fieldToZones.put("coconut", 35);

        HashMap<String, String> fieldToNectar = new HashMap<>();

        fieldToNectar.put("sunflower", "satisfying");
        fieldToNectar.put("dandelion", "comforting");
        fieldToNectar.put("mushroom", "motivating");
        fieldToNectar.put("blue flower", "refreshing");
        fieldToNectar.put("clover", "invigorating");

        fieldToNectar.put("strawberry", "refreshing");
        fieldToNectar.put("spider", "motivating");
        fieldToNectar.put("bamboo", "comforting");

        fieldToNectar.put("pineapple", "satisfying");
        fieldToNectar.put("stump", "motivating");

        fieldToNectar.put("cactus", "invigorating");
        fieldToNectar.put("pumpkin", "satisfying");
        fieldToNectar.put("pine tree", "comforting");
        fieldToNectar.put("rose", "motivating");
        fieldToNectar.put("mountain top", "invigorating");
        fieldToNectar.put("pepper", "invigorating");
        fieldToNectar.put("coconut", "refreshing");

        ArrayList<String> validLines = new ArrayList<>();

        while(scan.hasNext()){
            String line = scan.nextLine();
            if(!line.contains("mushroom") && line.contains("pineapple") && !line.contains("coconut") && !line.contains("pepper")){
                validLines.add(line);
            }
        }

        ArrayList<String> uniqueLines = new ArrayList<>();

        outerloop:
        for(String l : validLines){
            String[] parts = l.split(", ");
            ArrayList<Integer> zones = new ArrayList<>();
            ArrayList<String> nectars = new ArrayList<>();

            for(String field : parts){
                Integer zone = fieldToZones.get(field);
                String nectar = fieldToNectar.get(field);

                if(!zones.contains(zone) && !nectars.contains(nectar)){
                    zones.add(zone);
                    nectars.add(nectar);
                }
                else {
                    continue outerloop;
                }
            }
            uniqueLines.add(l);
        }

        for(String l : uniqueLines){
            System.out.println(l);
        }

        System.out.println("Combos: " + uniqueLines.size());
    }
}