package nl.gremmee.fireworks;

import nl.gremmee.fireworks.core.*;
import nl.gremmee.fireworks.objects.*;

import java.awt.*;
import java.util.*;

public class Handler
{
  //~ Instance Variables ---------------------------------------------------------------------------

  LinkedList<FireObject> object = new LinkedList<>();

  Random random = new Random();

  private Coord gravity = new Coord(0.0f, 0.19f);

  //~ Methods --------------------------------------------------------------------------------------

  public void addObject(FireObject aObject)
  {
    this.object.add(aObject);
  }

  public FireObject getGameObject(FireObject aObject)
  {
    int index = this.object.indexOf(aObject);
    if (index != -1)
    {
      return this.object.get(index);
    }
    return null;
  }


  public int getParticles()
  {
    int result = 0;
    for (FireObject fireObject : object)
    {
      if (fireObject instanceof Particle)
      {
        result++;
      }
    }
    return result;
  }


  public int getRockets()
  {
    int result = 0;
    for (FireObject fireObject : object)
    {
      if (fireObject instanceof Rocket)
      {
        result++;
      }
    }
    return result;
  }


  public int getSparkles()
  {
    int result = 0;
    for (FireObject fireObject : object)
    {
      if (fireObject instanceof Sparkle)
      {
        result++;
      }
    }
    return result;
  }


  public void removeObject(FireObject aObject)
  {
    this.object.remove(aObject);
  }


  public void render(Graphics aGraphics)
  {
    for (int i = 0; i < object.size(); i++)
    {
      FireObject tempObject = object.get(i);
      tempObject.render(aGraphics);
    }
  }


  public void update()
  {
    for (int i = 0; i < object.size(); i++)
    {
      FireObject tempObject = (FireObject) object.get(i);
      tempObject.applyForce(gravity);
      tempObject.update();

      if (tempObject instanceof Rocket)
      {
        if (tempObject.getVel().getY() >= 0)
        {
          removeObject(tempObject);

          explode(tempObject);
        }
      }
      else if (tempObject instanceof Particle)
      {
        if (!tempObject.isAlive())
        {
          removeObject(tempObject);

          sparkle(tempObject);
        }
      }
      else if (tempObject instanceof Sparkle)
      {
        if (!tempObject.isAlive())
        {
          removeObject(tempObject);
        }
      }
    }
  }


  private void explode(FireObject aFireObject)
  {
    for (int i = 0; i < (random.nextInt(200) + 200); i++)
    {
      Coord coord = new Coord(aFireObject.getPos().getX(), aFireObject.getPos().getY());
      addObject(new Particle(coord, ID.Particle, aFireObject.getColor()));
    }
  }


  private void sparkle(FireObject aFireObject)
  {
    for (int i = 0; i < (random.nextInt(2) + 4); i++)
    {
      Coord coord = new Coord(aFireObject.getPos());
      addObject(new Sparkle(coord, ID.Sparkle, Color.WHITE));
    }
  }
}
