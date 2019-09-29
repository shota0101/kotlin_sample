import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RoboSample {
  private static final int DELAY = 100;

  private static void inputKey(Robot robot, int... keys) {
    for (int key : keys) {
      robot.keyPress(key);
    }
    for (int i = keys.length - 1; i >= 0; i--) {
      robot.keyRelease(keys[i]);
    }
    robot.delay(DELAY);
  }

  private static void click(Robot robot, int key) {
    robot.mousePress(key);
    robot.mouseRelease(key);
    robot.delay(DELAY);
  }

  public static void main(String args[]) throws AWTException {
    Robot robot = new Robot();

    int screenWidth =  Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight =  Toolkit.getDefaultToolkit().getScreenSize().height;
    robot.mouseMove(screenWidth / 2, screenHeight / 2);
    click(robot, InputEvent.BUTTON1_DOWN_MASK);

    inputKey(robot, KeyEvent.VK_SHIFT, KeyEvent.VK_H);
    inputKey(robot, KeyEvent.VK_E);
    inputKey(robot, KeyEvent.VK_L);
    inputKey(robot, KeyEvent.VK_L);
    inputKey(robot, KeyEvent.VK_O);
    inputKey(robot, KeyEvent.VK_SPACE);
    inputKey(robot, KeyEvent.VK_SHIFT, KeyEvent.VK_W);
    inputKey(robot,KeyEvent.VK_O);
    inputKey(robot, KeyEvent.VK_R);
    inputKey(robot, KeyEvent.VK_L);
    inputKey(robot, KeyEvent.VK_D);
    inputKey(robot, KeyEvent.VK_SHIFT, KeyEvent.VK_1);
  }
}
