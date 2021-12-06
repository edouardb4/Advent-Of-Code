import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        String initialState = scanner.nextLine();
        System.out.println(determineDays(toMap(parseString(initialState)), 256));
    }

    public static long determineDays(HashMap<Integer, Long> map, int rep) {
        for(int i = 0; i < rep; i++) {
            Map<Integer, Long> newMap = new HashMap<Integer, Long>();
            for(Map.Entry<Integer, Long> entry : map.entrySet()) {
                int k = entry.getKey();
                long v = entry.getValue();
                if(k == 0) {
                    long l = newMap.containsKey(8) ? newMap.get(8) : 0;
                    newMap.put(8, l + v);
                    l = newMap.containsKey(6) ? newMap.get(6) : 0;
                    newMap.put(6, l + v);
                    continue;
                }
                long l = newMap.containsKey(k - 1) ? newMap.get(k - 1) : 0;
                newMap.put(k - 1, l + v);
            }
            map.clear();
            map.putAll(newMap);
            newMap.clear();
        }
        long count = 0;
        for(Map.Entry<Integer, Long> entry : map.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }
    
    public static HashMap<Integer, Long> toMap(List<Integer> list) {
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
        for(int i : list) {
            long v = map.containsKey(i) ? map.get(i) : 0;
            map.put(i, v + 1);
        }
        return map;
    }

    public static List<Integer> parseString(String str) {
        List<Integer> list = new ArrayList<Integer>();
        while(true) {
            if(str.length() == 0) {
                break;
            }
            list.add(Integer.parseInt(str.indexOf(",") == -1 ? str : str.substring(0, str.indexOf(","))));
            if(str.indexOf(",") != -1) {
                str = str.substring(str.indexOf(",") + 1, str.length());
            }
            else {
                str = "";
            }
        }
        return list;
    }
}
