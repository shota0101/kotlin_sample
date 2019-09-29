package competition.ari2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

public class P021 {
  // 棒を選んで三角形を作る問題
  public static void main(String[] args) {
    calc(Arrays.asList(2, 3, 4, 5, 10));
    calc(Arrays.asList(4, 5, 10, 20));

    calcBook(Arrays.asList(2, 3, 4, 5, 10));
    calcBook(Arrays.asList(4, 5, 10, 20));
  }

  @SuppressWarnings("Duplicates")
  private static void calc(List<Integer> a) {

    int max = 0;
    for (int i1 = 0; i1 < a.size(); i1++) {
      for (int i2 = 0; i2 < a.size(); i2++) {
        for (int i3 = 0; i3 < a.size(); i3++) {
          if (i1 == i2)
            continue;
          if (i2 == i3)
            continue;
          if (i3 == i1)
            continue;

          List<Integer> three = new ArrayList<>();
          three.add(a.get(i1));
          three.add(a.get(i2));
          three.add(a.get(i3));

          Collections.sort(three);
          if (three.get(0) + three.get(1) <= three.get(2))
            continue;

          int length = three.stream().mapToInt(s -> s).sum();
          if (max < length)
            max = length;
        }
      }
    }

    out.println(max);
  }

  @SuppressWarnings("Duplicates")
  private static void calcBook(List<Integer> a) {

    int max = 0;
    // リストから重複せずに3つ選ぶ場合は↓のやり方だと少しは効率良さそう
    for (int i1 = 0; i1 < a.size(); i1++) {
      for (int i2 = i1 + 1; i2 < a.size(); i2++) {
        for (int i3 = i2 + 1; i3 < a.size(); i3++) {
          if (i1 == i2)
            continue;
          if (i2 == i3)
            continue;
          if (i3 == i1)
            continue;

          List<Integer> three = new ArrayList<>();
          three.add(a.get(i1));
          three.add(a.get(i2));
          three.add(a.get(i3));

          Collections.sort(three);
          if (three.get(0) + three.get(1) <= three.get(2))
            continue;

          int length = three.stream().mapToInt(s -> s).sum();
          if (max < length)
            max = length;
        }
      }
    }

    out.println(max);
  }
}
