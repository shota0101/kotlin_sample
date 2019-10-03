package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class SampleDate {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");

  public static void main(String[] args) {
    Date date;
    try {
      String dateString = "2016/01/01 00:00:00";
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

      // UTCとしてSimpleDateFormatがDateを生成して、UNIXエポックタイムを取得する
      //      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo")));
      Long epochTimeByUTC = sdf.parse(dateString).getTime();

      DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo")));
      date = DATE_FORMAT.parse("2007/12/03 09:00");
      System.out.println(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
