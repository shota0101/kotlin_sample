package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RenameFileCreatedDate {
  public static void main(String[] args) {
    String dirPath = "";

    if (!dirPath.endsWith("/"))
      dirPath+="/";

    File dir = new File(dirPath);
    for (File file: dir.listFiles()) {
      String[] fileNameArray = file.getName().split("\\.");
      String ext = fileNameArray[fileNameArray.length - 1];
      if ("DS_Store".equals(ext))
        continue;
      String newFilePath = dirPath + getCreatedDate(file.getAbsolutePath()) + "." + ext;
      File newFile = new File(newFilePath);
      if (!newFile.exists())
        file.renameTo(newFile);
    }
  }

  private static String getCreatedDate(String path) {
    File file = new File(path);

    BasicFileAttributes attrs;
    try {
      attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
      FileTime time = attrs.creationTime();

      String pattern = "yyyy-MM-dd HH.mm.ss";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

      return simpleDateFormat.format( new Date( time.toMillis() ) );
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
