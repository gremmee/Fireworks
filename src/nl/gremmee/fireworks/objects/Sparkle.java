package nl.gremmee.fireworks.objects;

import nl.gremmee.fireworks.*;
import nl.gremmee.fireworks.core.*;

import java.awt.*;
import java.util.*;

public class Sparkle extends FireObject
{
  //~ Instance Variables ---------------------------------------------------------------------------

  int lifespan;

  //~ Constructors ---------------------------------------------------------------------------------

  public Sparkle(Coord aPos, ID aId, Color aColor)
  {
    super(aPos, aId);
    this.getVel().setXY((getRandom().nextFloat() - 0.5f) * (getRandom().nextInt(Fireworks.HEIGHT / 50) + 1), (getRandom().nextFloat() - 0.5f) * (getRandom().nextInt(Fireworks.HEIGHT / 50) + 1));
    this.lifespan = 255;
  }

  //~ Methods --------------------------------------------------------------------------------------

  @Override public void doRender(Graphics aGraphics)
  {
    Color c = Color.WHITE;
    aGraphics.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), this.lifespan));
    aGraphics.fillOval((int) this.getPos().getX(), (int) this.getPos().getY(), 1, 1);
  }


  @Override public void doUpdate()
  {
    float rand = getRandomFloat(0.5f, 0.7f);
    this.getVel().setXY(this.getVel().getX() * rand, this.getVel().getY() * rand);

    this.getPos().setX(this.getPos().getX() + getRandom().nextInt(3) - 1);
    this.getPos().setY(this.getPos().getY() + getRandom().nextInt(3) - 1);

    this.lifespan = this.lifespan - (getRandom().nextInt(4) + 4);
    if (this.lifespan < 127)
    {
      lifespan = 0;
      setAlive(false);
    }
  }
}
