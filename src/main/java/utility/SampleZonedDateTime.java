package utility;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class SampleZonedDateTime {
  public static void main(String[] args) {
    ZonedDateTimeを文字列から生成();
    ZonedDateTimeを文字列から生成_フォーマット指定();
    testIsDateLate();
    misc();
  }

  private static void ZonedDateTimeを文字列から生成() {
    ZonedDateTime zonedDateTime = ZonedDateTime.parse("2007-12-03T10:15:30.00Z");

    // フォーマット指定
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    ZonedDateTime zonedDateTime2 = ZonedDateTime.parse("2007/12/03 09:00", formatter);

    DateTimeFormatter a = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
  }

  private static void ZonedDateTimeを文字列から生成_フォーマット指定() {
    DateTimeFormatter dateTimeFormatter
        = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    LocalDate localDate = LocalDate.parse(
        "2007/12/03",
        dateTimeFormatter);
    ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Asia/Tokyo"));
  }

  private static void testIsDateLate() {
    testIsDateLate("2018/07/31", "2018-07-31T00:00:00Z");
    testIsDateLate("2018/07/31", "2018-07-31T00:00:01Z");
    testIsDateLate("2018/07/30", "2018-07-31T00:00:00Z");
    testIsDateLate("2018/08/01", "2018-07-31T00:00:00Z");
    testIsDateLate("2018/8/31", "2018-07-31T00:00:00Z");
  }

  private static void testIsDateLate(String dateStr, String todayStr) {
    Clock clock = Clock.fixed(Instant.parse(todayStr), ZoneId.of("UTC"));

    if (isPastDate(dateStr, clock)) {
      System.out.println(dateStr + " <  " + todayStr);
    } else {
      System.out.println(dateStr + " >= " + todayStr);
    }
  }

  /**
   * dateStrの日付が現在時刻より古いかどうかを確認
   * （dateStrはUTC時刻で解釈）
   * @param dateStr 「2018/07/31」の形式で指定
   * @param clockNow 現在時刻を固定する場合に利用
   * @return true → dateStrは過去の日付, false → dateStrは今日か未来
   */
  private static boolean isPastDate(String dateStr, Clock clockNow) throws DateTimeParseException {
    ZoneId zoneId = ZoneId.of("UTC");

    ZonedDateTime today = ZonedDateTime.now(clockNow); // 今日

    DateTimeFormatter dateTimeFormatter
        = DateTimeFormatter.ofPattern("uuuu/M/dd");
    LocalDate localDate = LocalDate.parse(
        dateStr,
        dateTimeFormatter);
    ZonedDateTime dateInput = localDate.atStartOfDay(zoneId);

    long diff = ChronoUnit.SECONDS.between(dateInput, today);

    if (diff <= 0) {
      return false;
    }
    return true;
  }

  private static void misc() {
    ZonedDateTime utc = ZonedDateTime.parse("2007-12-03T22:30:15.00Z");
    // 日付以下の情報を切り落とし
    ZonedDateTime utcDay = utc.truncatedTo(ChronoUnit.DAYS);

    // Zoneの変更
    ZonedDateTime japanTime = utc.withZoneSameLocal(ZoneId.of("Asia/Tokyo"));
    ZonedDateTime japanDay = japanTime.truncatedTo(ChronoUnit.DAYS);
    ChronoUnit.MILLIS.between(utcDay, japanDay);
  }
}
