# Plain Project for java.awt
## by ShZil

### Description:
Starting out with the `java.awt` package was long for me. Learning how everything works, looking through infinite [StackOverflow](stackoverflow.com) threads, studing everything - **and finishing with a low fps project**.
So I built the simplest (yet useful) `java.awt` class I could make, mostly so I could copy code from somewhere to begin a new project, but also so others can use it and learn from it.

### Copyright?
You may use this code in a project or learn from it, but while creating any other content using it, please credit me :)

### Explanation of the code:
As said eariler, one of my goals with [this repository](#) is to let others easily learn `java.awt`.
Therefore, I will split this code up to parts and explain each.

**Note:** _this may be outdated. Please submit an issue if it is :)_

#### Part 1: Importing
```java
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
```
This part imports everything needed for the project, from the following packages:
`java.awt, java.util, javax.swing, java.awt.event`
Those all come with java and do not need to be further installed.

#### Part 2: Fields
``` java
protected String title;
protected String iconImagePath;
public JFrame frame;
public final Color backgroundColor;
public final int width;
public final int height;
public final long intervalLoadingTime;
public final long intervalLength;
```
Some of these fields have to be overriden by the subclass (specifically the `protected` ones).
More info about each field can be found within the comments in `CanvasPrototype.java`.

#### Part 3: Setup / Initializer / Start
```java
27: public final void start() {
28:   frame = new JFrame(title);
29:   frame.getContentPane().setBackground(backgroundColor);
30:   frame.setIconImage(Toolkit.getDefaultToolkit().getImage(iconImagePath));
31:   frame.add(this);
32:   frame.setSize(width, height);
33:   frame.setVisible(true);
34:   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
35:
36:
37:   createInterval();
38:   createMouseListener();
39:
40:   run();
41: }
```
This part is called in `Main.java:18`, and it initializes the whole thing.
It sets:
- `JFrame frame` field _(line 28)_
- Background _(line 29)_
- Icon Image _(line 30)_
- Adds the canvas to the rendered view _(line 31)_
- Sets the size of the window _(line 32)_
- Asks the computer to start rendering the window _(line 33)_
- Sets close (Pressing the window's default X) operation _(line 34)_
- Calls the Interval initializer _(line 37)_
- Calls the Click-listener initializer _(line 38)_
- Calls the user defined setup/run/beginWith method _(line 40)_

#### Part 4: A lot of Anonymous Classes
TBH, I don't really understand things there, but I'll do my best.
```java
private final void createInterval() {
  new Timer().schedule(new TimerTask() {
    @Override
    public void run() {
      interval();
    }
  }, intervalLoadingTime, intervalLength);
}
```
This is the Interval initializer.
It creates a `new Timer` instance, asks it to `schedule` an action `new TimerTask` after `intervalLoadingTime` milliseconds and repeat that every `intervalLength` milliseconds.
The `TimerTask` itself only has one `@Override`n method (`void run()`) which just calls the user-defined `interval` method.
```java
private final void createMouseListener() {
  addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
      click(e.getX(), e.getY());
    }
  });
}
```
This is the Click-listener initializer.
It asks the default `this` object to `addMouseListener` (predefined action in `java.awt.Component`), with the only `@Override`n method being `void mouseClicked(MouseEvent)`, which calls the user-defined action `click(int,int)` while extracting the click's coordinates (`MouseEvent.getX() and MouseEvent.getY()`).

#### Part 5: The Abstract Methods
```java
public abstract void paint(Graphics g);

public abstract void click(int x, int y);

public abstract void run();

public abstract void interval();
```

`paint(Graphics)` is called sometimes - its hard to define when exactly, but it is called from time to time 🤣 - and it paints the colors according to the code. Check the [offical Oracle documentation](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html) and the example code (`Main.java`), or any other source about `java.awt.Graphics` to learn about drawing methods. I'd suggest trying out [p5.js](https://p5js.org), [Processing](https://processing.org), [Processing.py](https://py.processing.org) or other similar graphics engines to get used to working on bare visuals.

`click(int,int)` is called whenever the user clicks inside the window. The coordinates start at the top-left of the canvas (0,0) and cascade downwards and right to (width,height). You can use this method to check collision with objects, render a click, or just print to the console.

`run()` is called once, at the very beginning of the code, right after `start()` finishes and everything is ready for drawing. In this method the user can put code for their specific project, and initialize all their code.

`interval()` is called repeatedly during the running of the project, until the window is closed. For more info, look at `Part 4: createInterval()` in this file.
