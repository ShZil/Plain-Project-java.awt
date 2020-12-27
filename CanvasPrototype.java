/*
* Made by ShZil
*/

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

public abstract class CanvasPrototype extends Canvas {
  protected String title; // The window title for your canvas.
  protected String iconImagePath; // The relative path used to find an icon image.
  public JFrame frame; // This field is used to save the JavaFrame which the canvas exists in.
  public final Color backgroundColor = Color.black; // The default background for your canvas.
  public final int width = 500; // Width in pixels.
  public final int height = 500; // Height in pixels.
  public final long intervalLoadingTime = 1l; // The time waiting for the interval ticking to start, in MilliSeconds.
  public final long intervalLength = 1000l; // The time between each interval tick, in MilliSeconds.

  public final void start() {
    frame = new JFrame(title);
    frame.getContentPane().setBackground(backgroundColor);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(iconImagePath));
    frame.add(this);
    frame.setSize(width, height);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    createInterval();
    createMouseListener();

    run();
  }

  private final void createInterval() {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        interval();
      }
    }, intervalLoadingTime, intervalLength);
  }

  private final void createMouseListener() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        click(e.getX(), e.getY());
      }
    });
  }

  public abstract void paint(Graphics g);

  public abstract void click(int x, int y);

  public abstract void run();

  public abstract void interval();
}
