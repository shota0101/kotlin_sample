package utility;

import java.io.File;

public class SearchSameFIle {
  public static void main (String[] args) {

    String path = ".";

    //Fileクラスのオブジェクトを生成する
    File dir = new File(path);

    //listFilesメソッドを使用して一覧を取得する
    File[] list = dir.listFiles();

    for (File file : list) {
      System.out.println(file + " : " + file.isDirectory());
    }
  }
}
