package nl.gremmee.fireworks;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Window extends Canvas
{
  //~ Static Variables & Initializers --------------------------------------------------------------

  private static final long serialVersionUID = 1522602361354624706L;

  //~ Constructors ---------------------------------------------------------------------------------

  public Window(int aWidth, int aHeight, String aTitle, Fireworks aMain)
  {
    JFrame frame = new JFrame(aTitle);
    aMain.addMouseListener(new MouseAdapter()
      {
        @Override public void mouseClicked(MouseEvent e)
        {
          System.exit(0);
        }
      });

    frame.setPreferredSize(new Dimension(aWidth, aHeight));
    frame.setMaximumSize(new Dimension(aWidth, aHeight));
    frame.setMinimumSize(new Dimension(aWidth, aHeight));

    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setUndecorated(true);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(aMain);
    frame.setVisible(true);
    aMain.start();
  }
}
