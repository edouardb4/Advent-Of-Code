import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        String numbers = scanner.nextLine();
        scanner.nextLine();
        Board board = new Board(new int[5][5], new boolean[5][5]);
        List<Board> boards = new ArrayList<Board>();
        int i = 0;
        while (scanner.hasNext()) {
            String str = scanner.nextLine().trim();
            if (str.trim().isEmpty()) {
                boards.add(board);
                board = new Board(new int[5][5], new boolean[5][5]);
                i = 0;
                continue;
            }
            for(int j = 0; j < 5; j++) {
                int val = j != 4 ? Integer.parseInt(str.substring(0, str.indexOf(" "))) : Integer.parseInt(str);
                str = str.substring(findLeadingSpace(str) + 1, str.length());
                board.getBoard()[i][j] = val;
            }
            i++;
        }
        //day 2 solution, day 1 was removed
        while(true) {
            int number = Integer.parseInt(numbers.substring(0, numbers.indexOf(",")));
            for(int j = boards.size() - 1; j >= 0; j--) {
                Board b = boards.get(j);
                b.findAndValidate(number);
                if(b.won()) {
                    if(boards.size() == 1) {
                        System.out.println(number * b.remainingSum());
                        return;
                    }
                    boards.remove(j);
                }
            }
            numbers = numbers.substring(findNextNumber(numbers), numbers.length());
        }
    }

    public static int findLeadingSpace(String str) {
        boolean a = false;
        int lastIndex = -1;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                a = true;
                lastIndex = i;
            }
            else if(a && str.charAt(i) != ' ') {
                return i - 1;
            }
        }
        return lastIndex;
    }

    public static int findNextNumber(String str) {
        int j = -1;
        for(int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) {
                j = i + 1;
                return j;
            }
        }
        return j;
    }
}

class Board {
    private int[][] board;
    private boolean[][] booleanBoard;
    public Board(int[][] board, boolean[][] booleanBoard) {
        this.board = board;
        this.booleanBoard = booleanBoard;
    }

    public int[][] getBoard() {
        return board;
    }

    public int remainingSum() {
        int sum = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(!booleanBoard[i][j]) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }

    public boolean findAndValidate(int value) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                int val = board[i][j];
                if(val == value) {
                    booleanBoard[i][j] = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean won() {
        for(int i = 0; i < booleanBoard.length; i++) {
            boolean determine = true;
            for(int j = 0; j < booleanBoard[0].length; j++) {
                determine &= booleanBoard[i][j];
            }
            if(determine) {
                return true;
            }
            determine = true;
            for(int j = 0; j < booleanBoard[0].length; j++) {
                determine &= booleanBoard[j][i];
            }
            if(determine) {
                return true;
            }
        }
        return false;
    }

    public boolean[][] getBooleanBoard() {
        return booleanBoard;
    }


}
