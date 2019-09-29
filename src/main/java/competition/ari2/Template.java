package competition.ari2;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class Template {
  //
  public static void main(String[] args) {
    calc(Arrays.asList(1, 2, 3));
  }

  private static void calc(List<Integer> list) {
    out.println(list);
  }
}
