import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RandomTheories {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("allCombos.txt"));
        String fileName = "inv-ref-mot-mot.csv";

        String[] mandatoryFields = new String[]{}; // {"mushroom", "pineapple"}
        Integer[] zoneOrder = new Integer[]{-1, -1, -1, -1};
        String[] nectarOrder = new String[]{"invigorating", "refreshing", "motivating", "motivating"};

        HashMap<String, Integer> fieldToZones = getFieldToZonesMap();

        HashMap<String, String> fieldToNectars = getFieldToNectarsMap();

        ArrayList<String> validLines = new ArrayList<>();

        scannerLoop:
            while(scan.hasNext()){
                String line = scan.nextLine();
                String[] parts = line.split(", ");

                for(String mand : mandatoryFields) {
                    if(!line.contains(mand)) {
                        continue scannerLoop;
                    }
                }

                for (int i = 0; i < zoneOrder.length; i++) {
                    Integer zone = zoneOrder[i];
                    if(zone == -1){
                        continue;
                    }

                    if(!zone.equals(fieldToZones.get(parts[i]))) {
                        continue scannerLoop;
                    }
                }

                for (int i = 0; i < nectarOrder.length; i++) {
                    String nectar = nectarOrder[i];
                    if(nectar.equals("any")) {
                        continue;
                    }

                    if(!nectar.equals(fieldToNectars.get(parts[i]))) {
                        continue scannerLoop;
                    }
                }

                // passed all requirements
                validLines.add(line);
            }

        String content = String.join("\n", validLines);

        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Combos: " + validLines.size());
    }

    private static ArrayList<String> getUniqueCombos(
            ArrayList<String> original, HashMap<String, Integer> fieldToZones, HashMap<String, String> fieldToNectars) {
        ArrayList<String> uniqueLines = new ArrayList<>();
        outerloop:
            for(String l : original){
                String[] parts = l.split(", ");
                ArrayList<Integer> zones = new ArrayList<>();
                ArrayList<String> nectars = new ArrayList<>();

                for(String field : parts){
                    Integer zone = fieldToZones.get(field);
                    String nectar = fieldToNectars.get(field);

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
            return uniqueLines;
    }

    private static HashMap<String, String> getFieldToNectarsMap() {
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
        return fieldToNectar;
    }

    private static HashMap<String, Integer> getFieldToZonesMap() {
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
        return fieldToZones;
    }
}