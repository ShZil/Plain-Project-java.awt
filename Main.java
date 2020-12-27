import java.awt.Color;
import java.awt.Canvas;
import java.util.Timer;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public class Main extends CanvasPrototype {
  public final String title = "Title Example";
  public final String iconImagePath = "textures/icon.png";

  public static void main(String[] args) {
    new Main().start();
  }

  public void paint(Graphics g) {}

  public void click(int x, int y) {
    System.out.println("Click");
    repaint();
  }

  public void run() {
    System.out.println("Running");
  }

  public void interval() {
    System.out.println("Interval");
    repaint();
  }
}
