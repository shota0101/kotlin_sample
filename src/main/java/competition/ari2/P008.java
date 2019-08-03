package competition.ari2;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class P008 {
  private static void calc(int n, int m, List<Integer> k) {
    for (int i1 = 0; i1 < k.size(); i1++) {
      for (int i2 = 0; i2 < k.size(); i2++) {
        for (int i3 = 0; i3 < k.size(); i3++) {
          for (int i4 = 0; i4 < k.size(); i4++) {
            int sum = k.get(i1) + k.get(i2) + k.get(i3) + k.get(i4);
            if (sum == m) {
              out.println("Yes");
              return;
            }
          }
        }
      }
    }
    out.println("No");
  }

  public static void main(String[] args) {
    calc(3, 10, Arrays.asList(1, 3, 5));
    calc(3, 9, Arrays.asList(1, 3, 5));
  }
}
