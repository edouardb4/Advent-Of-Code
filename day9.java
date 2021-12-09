import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static me.letssee.stuff.Helper.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        List<String> lines = new ArrayList<String>();
        while(scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        doPartTwo(lines);
    }

    public static void doPartTwo(List<String> lines) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int[][] array = new int[lines.size()][lines.get(0).length()];
        for(int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for(int j = 0; j < line.length(); j++) {
                array[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }
        List<Integer> integers = new ArrayList<Integer>();
        boolean[][] explored = new boolean[lines.size()][lines.get(0).length()];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                if(array[i][j] == 9 || explored[i][j]) {
                    continue;
                }
                int result = move(i, j, 0, explored, array);
                integers.add(result);
            }
        }
        sort(integers);
        System.out.println((integers.get(0) + 1) * (integers.get(1) + 1) * (integers.get(2) + 1));
    }

    public static void printList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
    }

    public static void sort(List<Integer> list) {
        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = i; j < list.size(); j++) {
                if(list.get(j) > list.get(i)) {
                    int temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                }
            }
        }
    }

    public static int move(int i, int j, int carry, boolean[][] explored, int[][] arr) {
        explored[i][j] = true;
        if(i + 1 != arr.length && arr[i + 1][j] != 9 && !(explored[i + 1][j])) {
            carry = move(i + 1, j, carry + 1, explored, arr);
        }
        if(j + 1 != arr[0].length && arr[i][j + 1] != 9 && !(explored[i][j+1])) {
            carry = move(i, j + 1, carry + 1, explored, arr);
        }
        if(j - 1 >= 0 && arr[i][j - 1] != 9 && !(explored[i][j-1])) {
            carry = move(i, j - 1, carry + 1, explored, arr);
        }
        if(i - 1 >= 0 && arr[i - 1][j] != 9 && !(explored[i-1][j])) {
            carry = move(i - 1, j, carry + 1, explored, arr);
        }
        return carry;
    }

    public static void doPartOne(List<String> lines) {
        int sum = 0;
        for(int k = 0; k < lines.size(); k++) {
            String line = lines.get(k);
            for (int i = 0; i < line.length(); i++) {
                int j = Integer.parseInt(line.substring(i, i + 1));
                if (i > 0 && j >= Integer.parseInt(line.substring(i - 1, i))) {
                    continue;
                }
                if (i != line.length() - 1 && Integer.parseInt(line.substring(i + 1, i + 2)) <= j) {
                    continue;
                }
                if (k > 0 && Integer.parseInt(lines.get(k - 1).substring(i, i + 1)) <= j) {
                    continue;
                }
                if (k != lines.size() - 1 && Integer.parseInt(lines.get(k + 1).substring(i, i + 1)) <= j) {
                    continue;
                }
                System.out.println(i);
                sum += j + 1;
            }
        }
        System.out.println(sum);
    }
}
