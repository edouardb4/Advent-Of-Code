import com.sun.xml.internal.ws.util.StringUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static me.letssee.stuff.Helper.*;

class PartOne {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        HashSet<Point> points = new HashSet<Point>();
        List<FoldInstruction> foldInstructions = new ArrayList<FoldInstruction>();
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if(line.startsWith("fold")) {
                boolean xAxis = line.contains("x");
                int p = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.length()));
                foldInstructions.add(new FoldInstruction(xAxis, p));
                continue;
            }
            if(line.trim().isEmpty()) {
                continue;
            }
            int x = Integer.parseInt(line.substring(0, line.indexOf(",")));
            int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.length()));
            points.add(new Point(x, y));
        }
        for(FoldInstruction instruction : foldInstructions) {
            fold(points, instruction);
        }

        char[][] arr = new char[getMaxX(points) + 1][getMaxY(points) + 1];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = ' ';
            }
        }
        for(Point point : points) {
            arr[point.x][point.y] = '#';
        }
        for(int i = 0; i < arr[0].length; i++) {
            String ans = "";
            for(int j = 0; j < arr.length; j++) {
                ans += arr[j][i];
            }
            System.out.println(ans);
        }
    }

    public static int getMaxY(HashSet<Point> points) {
        int max = 0;
        for(Point i : points) {
            if(Math.abs(i.y) > max) {
                max = Math.abs(i.y);
            }
        }
        return max;
    }

    public static int getMaxX(HashSet<Point> points) {
        int max = 0;
        for(Point i : points) {
            if(Math.abs(i.x) > max) {
                max = Math.abs(i.x);
            }
        }
        return max;
    }

    public static void fold(HashSet<Point> points, FoldInstruction instruction) {
        HashSet<Point> newPoints = new HashSet<Point>();
        for(Point p : points) {
            if(instruction.isYAxis()) {
                if(p.y > instruction.getRotation()) {
                    newPoints.add(new Point(p.x, p.y - 2 * Math.abs(p.y - instruction.getRotation())));
                }
                else {
                    newPoints.add(p);
                }
            }
            else if(!instruction.isYAxis()) {
                if(p.x > instruction.getRotation()) {
                    newPoints.add(new Point(p.x - 2 * Math.abs(p.x - instruction.getRotation()), p.y));
                }
                else {
                    newPoints.add(p);
                }
            }
        }
        points.clear();
        points.addAll(newPoints);
    }
}

class Point {

    public int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point)) {
            return false;
        }
        return ((Point)o).x == x && ((Point)o).y == y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
}

class FoldInstruction {
    public int p;
    public boolean axis;
    public FoldInstruction(boolean axis, int p) {
        this.axis = axis;
        this.p = p;
    }

    public int getRotation() {
        return p;
    }

    public boolean isYAxis() {
        return !axis;
    }
}
