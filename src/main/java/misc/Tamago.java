package misc;

import java.awt.*;
import java.awt.event.KeyEvent;

// Java.awt.Robot keyPress and keyRelease not working at all
// https://stackoverflow.com/questions/53901026/java-awt-robot-keypress-and-keyrelease-not-working-at-all


public class Tamago {
  private static final int TIME = 1000 * 3;

  public static void main(String[] args) throws AWTException {
    Robot robot = new Robot();

    for(int i = 0; i < 3; i++) {
      System.out.print(i);
      try {
        Thread.sleep(1000);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    System.out.println();

    int max = 3;
    for(int i = 0; i <= 3; i++) {
      System.out.println(i + "/" + max);
      move(robot, KeyEvent.VK_UP);
      move(robot, KeyEvent.VK_DOWN);
    }
  }

  private static void move(Robot robot, int key) {
    robot.keyPress(key);
    robot.delay(TIME);
    robot.keyRelease(key);
  }
}
