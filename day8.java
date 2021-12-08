import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static me.letssee.stuff.Helper.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        int count = 0;
        while(scanner.hasNext()) {
            String str = scanner.nextLine();
            String j = str.substring(str.indexOf("|") + 2, str.length());
            String[] split = j.split(" ");
            String k = str.substring(0, str.indexOf("|"));
            String[] split2 = k.split(" ");
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            Character[] container = new Character[8];
            for(int i = 0; i < split2.length; i++) {
                if(split2[i].length() == 2) {
                    container[0] = split2[i].charAt(0);
                    container[1] = split2[i].charAt(1);
                }
            }
            for(int i = 0; i < split2.length; i++) {
                if(split2[i].length() == 4) {
                    String s = split2[i];
                    s = s.replaceAll(container[0] + "", "");
                    s = s.replaceAll(container[1] + "", "");
                    container[2] = s.charAt(0);
                    container[3] = s.charAt(1);
                }
            }
            for(int i = 0; i < split2.length; i++) {
                if(split2[i].length() == 3) {
                    String s = split2[i];
                    s = s.replaceAll(container[0] + "", "");
                    s = s.replaceAll(container[1] + "", "");
                    s = s.replaceAll(container[2] + "", "");
                    s = s.replaceAll(container[3] + "", "");
                    container[4] = s.charAt(0);
                }
            }
            for(int i = 0; i < split2.length; i++) {
                if(split2[i].length() == 7) {
                    String s = split2[i];
                    s = s.replaceAll(container[0] + "", "");
                    s = s.replaceAll(container[1] + "", "");
                    s = s.replaceAll(container[2] + "", "");
                    s = s.replaceAll(container[3] + "", "");
                    s = s.replaceAll(container[4] + "", "");
                    container[5] = s.charAt(0);
                    container[6] = s.charAt(1);
                }
            }
            String built = "";
            for(int i = 0; i < split.length; i++) {
                int l = split[i].length();
                String s = split[i];
                if(l == 2) {
                    built += "1";
                }
                else if(l == 4) {
                    built += "4";
                }
                else if(l == 3) {
                    built += "7";
                }
                else if(l == 7) {
                    built += "8";
                }
                else if(l == 6) {
                    if(s.indexOf(container[2] + "") == -1 || s.indexOf(container[3] + "") == -1) {
                        built += "0";
                    }
                    else if(s.indexOf(container[0] + "") != -1 && s.indexOf(container[1] + "") != -1) {
                        built += "9";
                    }
                    else {
                        built += "6";
                    }
                }
                else {
                     if(s.indexOf(container[0] + "") != -1 && s.indexOf(container[1] + "") != -1) {
                         built += "3";
                     }
                     else if(s.indexOf(container[2] + "") == -1 || s.indexOf(container[3] + "") == -1) {
                         built += "2";
                     }
                     else {
                         built += "5";
                     }
                }
            }
            count += Integer.valueOf(built);
        }
        System.out.println(count);
    }
}
