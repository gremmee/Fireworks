package nl.gremmee.fireworks.objects;

import nl.gremmee.fireworks.*;
import nl.gremmee.fireworks.core.*;

import java.awt.*;
import java.util.*;

public class Particle extends FireObject
{
  //~ Instance Variables ---------------------------------------------------------------------------

  int lifespan;

  //~ Constructors ---------------------------------------------------------------------------------

  public Particle(Coord aPos, ID aId, Color aColor)
  {
    super(aPos, aId);
    setColor(aColor);
    this.getVel().setXY((getRandom().nextFloat() - 0.5f) * (getRandom().nextInt(Fireworks.HEIGHT / 10) + 2), (getRandom().nextFloat() - 0.5f) * (getRandom().nextInt(Fireworks.HEIGHT / 10) + 2));
    this.lifespan = 255;
  }

  //~ Methods --------------------------------------------------------------------------------------

  @Override public void doRender(Graphics aGraphics)
  {
    Color c = this.getColor();
    aGraphics.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), this.lifespan));
    aGraphics.fillOval((int) this.getPos().getX(), (int) this.getPos().getY(), 4, 4);
  }


  @Override public void doUpdate()
  {
    float minX = 0.8f;
    float maxX = 0.95f;
    Random rand = this.getRandom();
    float finalX = (rand.nextFloat() * (maxX - minX)) + minX;
    this.getVel().setXY(this.getVel().getX() * finalX, this.getVel().getY() * finalX);

    this.lifespan = this.lifespan - (getRandom().nextInt(5) + 1);
    if (this.lifespan < 0)
    {
      lifespan = 0;
      setAlive(false);
    }
  }
}
