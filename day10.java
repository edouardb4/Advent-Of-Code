import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static me.letssee.stuff.Helper.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        doPartOne();
        doPartTwo();
    }

    public static void doPartOne() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        int sum = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> list = new ArrayList<Character>();
            list.add(line.charAt(0));
            for(int i = 1; i < line.length(); i++) {
                if(list.size() > 0) {
                    if (isOpen(list.get(list.size() - 1)) && isClosed(line.charAt(i)) && isSimilar(list.get(list.size() - 1), line.charAt(i))) {
                        list.remove(list.size() - 1);
                        continue;
                    }
                    boolean mismatch = mismatch(list.get(list.size() - 1), line.charAt(i));
                    if (mismatch) {
                        int points = getPointsPartOne(line.charAt(i));
                        sum += points;
                        break;
                    }
                }
                list.add(line.charAt(i));
            }
        }
        System.out.println(sum);
    }

    public static void doPartTwo() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        List<Long> sums = new ArrayList<Long>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> list = new ArrayList<Character>();
            list.add(line.charAt(0));
            boolean incomplete = true;
            for(int i = 1; i < line.length(); i++) {
                if(list.size() > 0) {
                    if (isOpen(list.get(list.size() - 1)) && isClosed(line.charAt(i)) && isSimilar(list.get(list.size() - 1), line.charAt(i))) {
                        list.remove(list.size() - 1);
                        continue;
                    }
                    boolean mismatch = mismatch(list.get(list.size() - 1), line.charAt(i));
                    if (mismatch) {
                        incomplete = false;
                        break;
                    }
                }
                list.add(line.charAt(i));
            }
            if(!incomplete) {
                continue;
            }
            long sum = 0;
            while(list.size() > 0) {
                char c = list.get(list.size() - 1);
                sum *= 5;
                sum += getPointsPartTwo(c);
                list.remove(list.size() - 1);
            }
            sums.add(sum);
        }
        sort(sums);
        System.out.println(sums.get(sums.size() / 2));
    }

    public static void printList(List<Long> list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        System.out.println(sb.toString());
    }

    public static void sort(List<Long> list) {
        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = i; j < list.size(); j++) {
                if(list.get(i) < list.get(j)) {
                    long temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public static int getPointsPartOne(char i) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 3);
        map.put(')', 3);
        map.put('<', 25137);
        map.put('>', 25137);
        map.put('[', 57);
        map.put(']', 57);
        map.put('{', 1197);
        map.put('}', 1197);
        return map.get(i);
    }

    public static int getPointsPartTwo(char i) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 1);
        map.put(')', 1);
        map.put('[', 2);
        map.put(']', 2);
        map.put('{', 3);
        map.put('}', 3);
        map.put('<', 4);
        map.put('>', 4);
        return map.get(i);
    }

    public static boolean mismatch(char i, char j) {
        if(isOpen(i) && isClosed(j) && !isSimilar(i, j)) {
            return true;
        }
        return false;
    }

    public static boolean isSimilar(char i, char j) {
        return getOpposite(i) == j;
    }

    public static char getOpposite(char i) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put(')', '(');
        map.put('<', '>');
        map.put('>', '<');
        map.put('[', ']');
        map.put(']', '[');
        map.put('{', '}');
        map.put('}', '{');
        return map.get(i);
    }

    public static boolean isOpen(char i) {
        return i == '(' || i == '[' || i == '<' || i == '{';
    }

    public static boolean isClosed(char i) {
        return i == ')' || i == ']' || i == '>' || i == '}';
    }
}
