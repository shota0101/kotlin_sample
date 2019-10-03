package utility;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnixTime {
  public static void main(String[] args) {
    System.out.println(new Date().getTime());
    System.out.println(new Date(TimeUnit.SECONDS.toMillis(1451606400)));
  }
}
