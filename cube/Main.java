package cube;
import java.util.Arrays;

public class Main {
  public static void main(String args[]) {
    String s[] = {"a", "b", "c", "d"};
    double d [][]= {
        {0.50, 0.20,  0.20, 0.30},
        {0.50, 1.10,  0.50, 0.80},
        {0.50, 0.70,  0.40},
        {0.50, 0.70},
        {0.50},
    };
    System.out.println(Arrays.toString(s));
    System.out.println(Arrays.deepToString(d));
  }
}
