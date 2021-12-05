import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static boolean part1 = false;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        List<Point[]> list = new ArrayList<Point[]>();
        Map<Point[], List<Point[]>> intersectMap = new HashMap<Point[], List<Point[]>>();
        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            int x = Integer.parseInt(input.substring(0, input.indexOf(",")));
            int y = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf("->") - 1));
            int x1 = Integer.parseInt(input.substring(input.indexOf("->") + 3, input.lastIndexOf(",")));
            int y1 = Integer.parseInt(input.substring(input.lastIndexOf(",") + 1, input.length()));
            Point p0 = new Point(x, y);
            Point p1 = new Point(x1, y1);
            list.add(new Point[]{p0, p1});
        }
        int count = 0;
        HashMap<String, Integer> touchMap = new HashMap<String, Integer>();
        for(int i = 0; i < list.size(); i++) {
            Point[] p0 = list.get(i);
            if(part1) {
                if(p0[0].getX() == p0[1].getX() || p0[0].getY() == p0[1].getY()) {
                    doWork(p0[0], p0[1], touchMap);
                }
            }
            else {
                doWork(p0[0], p0[1], touchMap);
            }
        }
        for(int i : touchMap.values()) {
            if(i >= 2) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void doWork(Point p1, Point p2, Map<String, Integer> touchMap) {
        int l0xd = p2.getX() == p1.getX() ? 0 : (p2.getX() - p1.getX()) / (Math.abs(p2.getX() - p1.getX()));
        int l0yd = p2.getY() == p1.getY() ? 0 : (p2.getY() - p1.getY()) / (Math.abs(p2.getY() - p1.getY()));
        int startX = p1.getX();
        int startY = p1.getY();
        while(true) {
            String str = startX + "," + startY;
            int count = touchMap.containsKey(str) ? touchMap.get(str) : 0;
            touchMap.put(str, count + 1);
            if(startX == p2.getX() && startY == p2.getY()) {
                break;
            }
            startX += l0xd;
            startY += l0yd;
        }
    }
}


class Point {

    private int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return  x + "," + y;
    }
}
