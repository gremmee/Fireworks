package nl.gremmee.fireworks.objects;

import nl.gremmee.fireworks.*;
import nl.gremmee.fireworks.core.*;

import java.awt.*;

public class Rocket extends FireObject
{
  //~ Constructors ---------------------------------------------------------------------------------

  public Rocket(Coord aPos, ID aId)
  {
    super(aPos, aId);
    this.getVel().setXY(getRandom().nextInt(9) - 5, (getRandom().nextInt(Fireworks.WIDTH / 100) + 10) * -1);
    setColor(new Color(getRandom().nextInt(255), getRandom().nextInt(255), getRandom().nextInt(255)));
  }

  //~ Methods --------------------------------------------------------------------------------------

  @Override public void doRender(Graphics aGraphics)
  {
    aGraphics.setColor(getColor());
    aGraphics.drawOval((int) this.getPos().getX(), (int) this.getPos().getY(), 4, 4);
  }


  @Override public void doUpdate()
  {
    if (getRandom().nextInt(10) < 1)
    {
      this.getPos().setX(this.getPos().getX() + getRandom().nextInt(3) - 1);
    }
  }
}
