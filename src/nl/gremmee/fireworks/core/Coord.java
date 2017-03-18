package nl.gremmee.fireworks.core;

public class Coord
{
  //~ Instance Variables ---------------------------------------------------------------------------

  private float x;
  private float y;

  //~ Constructors ---------------------------------------------------------------------------------

  public Coord(Coord aCoord)
  {
    this.setXY(aCoord);
  }


  public Coord(float aX, float aY)
  {
    this.setXY(aX, aY);
  }

  //~ Methods --------------------------------------------------------------------------------------

  public void add(Coord aOtherCoord)
  {
    setXY(this.getX() + aOtherCoord.getX(), this.getY() + aOtherCoord.getY());
  }


  public float getX()
  {
    return x;
  }


  public float getY()
  {
    return y;
  }


  public void setX(float aX)
  {
    this.x = aX;
  }


  public void setXY(Coord aCoord)
  {
    this.setXY(aCoord.getX(), aCoord.getY());
  }


  public void setXY(float aX, float aY)
  {
    this.setX(aX);
    this.setY(aY);
  }


  public void setY(float aY)
  {
    this.y = aY;
  }
}
