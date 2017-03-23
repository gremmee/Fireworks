package nl.gremmee.fireworks.objects;

import java.awt.Color;
import java.awt.Graphics;

import nl.gremmee.fireworks.Fireworks;
import nl.gremmee.fireworks.ID;
import nl.gremmee.fireworks.core.Coord;

public class Rocket extends FireObject {

    public Rocket(Coord aPos, ID aId) {
        super(aPos, aId);
        this.getVel().setXY(getRandom().nextInt(9) - 5, (getRandom().nextInt(Fireworks.WIDTH / 100) + 10) * -1);
        setColor(new Color(getRandom().nextInt(255), getRandom().nextInt(255), getRandom().nextInt(255)));
    }

    @Override
    public void doRender(Graphics aGraphics) {
        aGraphics.setColor(getColor());
        aGraphics.drawOval((int) this.getPos().getX(), (int) this.getPos().getY(), 4, 4);
    }

    @Override
    public void doUpdate() {
        if (getRandom().nextInt(10) < 1) {
            this.getPos().setX(this.getPos().getX() + getRandom().nextInt(3) - 1);
        }
    }
}
