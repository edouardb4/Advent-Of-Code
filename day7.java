import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static me.letssee.stuff.Helper.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        String text = scanner.nextLine();
        String[] split = text.split(",");
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < split.length; i++) {
            int k = Integer.parseInt(split[i]);
            int j = map.containsKey(k) ? map.get(k) : 0;
            map.put(k, j + 1);
        }
        System.out.println(findPartOneLeastFuel(map));
        System.out.println(findPartTwoLeastFuel(map));
    }

    public static int findPartOneLeastFuel(HashMap<Integer, Integer> map) {
        int min = findMin(map);
        int max = findMax(map);
        int ret = Integer.MAX_VALUE;
        for(int i = min; i <= max; i++) {
            int dist = findDistanceTo(i, map, true);
            if(dist < ret) {
                ret = dist;
            }
        }
        return ret;
    }

    public static int findPartTwoLeastFuel(HashMap<Integer, Integer> map) {
        int min = findMin(map);
        int max = findMax(map);
        int ret = Integer.MAX_VALUE;
        for(int i = min; i <= max; i++) {
            int dist = findDistanceTo(i, map, false);
            if(dist < ret) {
                ret = dist;
            }
        }
        return ret;
    }

    public static int findDistanceTo(int v, HashMap<Integer, Integer> map, boolean efficient) {
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int dist = -1;
            if(efficient) {
                dist = Math.abs(v - entry.getKey()) * entry.getValue();
            }
            else {
                dist = Math.abs(v - entry.getKey());
                dist = (dist + 1) * dist / 2;
                dist *= entry.getValue();
            }
            sum += dist;
        }
        return sum;
    }

    public static int findMax(HashMap<Integer, Integer> map) {
        int ret = 0;
        for(int i : map.keySet()) {
            if(i > ret) {
                ret = i;
            }
        }
        return ret;
    }

    public static int findMin(HashMap<Integer, Integer> map) {
        int ret = Integer.MAX_VALUE;
        for(int i : map.keySet()) {
            if(i < ret) {
                ret = i;
            }
        }
        return ret;
    }
}
