import com.sun.xml.internal.ws.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static me.letssee.stuff.Helper.*;

class PartOne {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        HashMap<String, HashSet<String>> caveMap = new HashMap<String, HashSet<String>>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String one = line.substring(0, line.indexOf("-"));
            String two = line.substring(line.indexOf("-") + 1, line.length());
            HashSet<String> connected = caveMap.containsKey(one) ? caveMap.get(one) : new HashSet<String>();
            connected.add(two);
            caveMap.putIfAbsent(one, connected);
            connected = caveMap.containsKey(two) ? caveMap.get(two) : new HashSet<String>();
            connected.add(one);
            caveMap.putIfAbsent(two, connected);
        }
        HashMap<String, HashSet<String>> visitedMap = new HashMap<String, HashSet<String>>();
        System.out.println(traverse(visitedMap, caveMap, "", "start", 0));
    }

    public static boolean ensureValid(String path) {
        String[] split = path.split(" ");
        HashSet<String> nonCapitalized = new HashSet<String>();
        for(int i = 0; i < split.length; i++) {
            if(nonCapitalized.contains(split[i])) {
                return false;
            }
            if(!StringUtils.capitalize(split[i]).equals(split[i])) {
                nonCapitalized.add(split[i]);
            }
        }
        return true;
    }

    public static int traverse(HashMap<String, HashSet<String>> visited, HashMap<String, HashSet<String>> map, String pathTaken, String key, int count) {
        HashSet<String> paths = map.get(key);
        for(String path : paths) {
            if(path.equals("start")) {
                continue;
            }
            if(!ensureValid(pathTaken + " " + key)) {
                continue;
            }
            if(path.equals("end")) {
                count = count + 1;
                continue;
            }
            count = traverse(visited, map, pathTaken + " " + key, path, count);
        }
        return count;
    }
}

public class PartTwo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        HashMap<String, HashSet<String>> caveMap = new HashMap<String, HashSet<String>>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String one = line.substring(0, line.indexOf("-"));
            String two = line.substring(line.indexOf("-") + 1, line.length());
            HashSet<String> connected = caveMap.containsKey(one) ? caveMap.get(one) : new HashSet<String>();
            connected.add(two);
            caveMap.putIfAbsent(one, connected);
            connected = caveMap.containsKey(two) ? caveMap.get(two) : new HashSet<String>();
            connected.add(one);
            caveMap.putIfAbsent(two, connected);
        }
        HashMap<String, HashSet<String>> visitedMap = new HashMap<String, HashSet<String>>();
        System.out.println(traverse(visitedMap, caveMap, "", "start", 0));
    }

    public static boolean ensureValid(String path) {
        String[] split = path.split(" ");
        HashSet<String> nonCapitalized = new HashSet<String>();
        int tolerance = 1;
        for(int i = 0; i < split.length; i++) {
            if(nonCapitalized.contains(split[i])) {
                if(tolerance-- > 0) {
                    continue;
                }
                return false;
            }
            if(split[i].equals("end") && i != split.length - 2) {
                return false;
            }
            if(!StringUtils.capitalize(split[i]).equals(split[i])) {
                nonCapitalized.add(split[i]);
            }
        }
        return true;
    }

    public static int traverse(HashMap<String, HashSet<String>> visited, HashMap<String, HashSet<String>> map, String pathTaken, String key, int count) {
        HashSet<String> paths = map.get(key);
        for(String path : paths) {
            if(path.equals("start")) {
                continue;
            }
            if(!ensureValid(pathTaken + " " + key)) {
                continue;
            }
            if(path.equals("end")) {
                count = count + 1;
                continue;
            }
            count = traverse(visited, map, pathTaken + " " + key, path, count);
        }
        return count;
    }
}
