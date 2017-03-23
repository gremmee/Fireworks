package nl.gremmee.fireworks.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import nl.gremmee.fireworks.ID;
import nl.gremmee.fireworks.core.Coord;

public abstract class FireObject {
    // ~ Instance Variables
    // ---------------------------------------------------------------------------

    private boolean isAlive;

    private Color   color;
    private Coord   acc;
    private Coord   pos;
    private Coord   vel;

    private ID      id;

    private Random  random = new Random();

    // ~ Constructors
    // ---------------------------------------------------------------------------------

    public FireObject(Coord aPos, ID aId) {
        setPos(aPos);
        setAcc(new Coord(0.0f, 0.0f));
        setVel(new Coord(0.0f, 0.0f));
        this.id = aId;
        this.isAlive = true;
    }

    // ~ Methods
    // --------------------------------------------------------------------------------------

    public abstract void doRender(Graphics aGraphics);

    public abstract void doUpdate();

    public void applyForce(Coord aForce) {
        this.getAcc().add(aForce);
    }

    public Coord getAcc() {
        return this.acc;
    }

    public Color getColor() {
        return this.color;
    }

    public ID getID() {
        return this.id;
    }

    public Coord getPos() {
        return this.pos;
    }

    public Random getRandom() {
        return this.random;
    }

    public float getRandomFloat(float aMin, float aMax) {
        Random rand = this.getRandom();
        return (rand.nextFloat() * (aMax - aMin)) + aMin;
    }

    public Coord getVel() {
        return this.vel;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void render(Graphics aGraphics) {
        doRender(aGraphics);
    }

    public void setAcc(Coord aAcc) {
        this.acc = aAcc;
    }

    public void setAlive(boolean aAlive) {
        this.isAlive = aAlive;
    }

    public void setColor(Color aColor) {
        this.color = aColor;
    }

    public void setId(ID aId) {
        this.id = aId;
    }

    public void setPos(Coord aPos) {
        this.pos = aPos;
    }

    public void setVel(Coord aVel) {
        this.vel = aVel;
    }

    public void update() {
        doUpdate();
        this.getVel().add(this.getAcc());
        this.getPos().add(this.getVel());
        this.getAcc().setXY(0, 0);
    }
}
