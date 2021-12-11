import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static me.letssee.stuff.Helper.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        int[][] arr = new int[10][10];
        int j = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for(int i = 0; i < line.length(); i++) {
                arr[j][i] = Integer.parseInt(line.substring(i, i + 1));
            }
            j++;
        }
        //doPartOne(arr);
        doPartTwo(arr);
    }

    public static void doPartOne(int[][] arr){
        int count = 0;
        for(int i = 0; i < 100; i++) {
            count += run(arr);
        }
        System.out.println("Part One:" + count);
    }

    public static void doPartTwo(int[][] arr) {
        int count = 1;
        while(run(arr) != 100) {
            count++;
        }
        System.out.println(count);
    }

    public static int run(int[][] arr) {
        boolean[][] flashed = new boolean[10][10];
        int count = doLogic(arr, flashed, 0);
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                arr[i][j] = arr[i][j] + 1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (flashed[i][j]) {
                    arr[i][j] = 0;
                }
            }
        }
        return count;
    }

    public static int doLogic(int[][] arr, boolean[][] flashed, int count) {
        int flashes = 0;
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (flashed[i][j]) {
                    continue;
                }
                if (arr[i][j] >= 9) {
                    flashAllAdjacent(arr, flashed, i, j);
                    flashed[i][j] = true;
                    flashes += 1;
                }
            }
        }
        if(flashes > 0) {
            count = doLogic(arr, flashed, count + flashes);
        }
        return count;
    }

    public static void flashAllAdjacent(int[][] arr, boolean[][] flashed, int i, int j) {
        boolean[] determinate = {i - 1 >= 0, i + 1 < arr.length, j + 1 < arr[0].length, j - 1 >= 0};
        if(determinate[0]) {
            arr[i - 1][j] = arr[i - 1][j] + 1;
        }
        if(determinate[1]) {
            arr[i + 1][j] = arr[i + 1][j] + 1;
        }
        if(determinate[2]) {
            arr[i][j + 1] = arr[i][j + 1] + 1;
        }
        if(determinate[3]) {
            arr[i][j - 1] = arr[i][j - 1] + 1;
        }
        if(determinate[0] && determinate[2]) {
            arr[i - 1][j + 1] = arr[i - 1][j + 1] + 1;
        }
        if(determinate[1] && determinate[2]) {
            arr[i + 1][j + 1] = arr[i + 1][j + 1] + 1;
        }
        if(determinate[0] && determinate[3]) {
            arr[i - 1][j - 1] = arr[i - 1][j - 1] + 1;
        }
        if(determinate[1] && determinate[3]) {
            arr[i + 1][j - 1] = arr[i + 1][j - 1] + 1;
        }
    }
}
