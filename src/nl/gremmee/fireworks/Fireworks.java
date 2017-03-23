package nl.gremmee.fireworks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;

import nl.gremmee.fireworks.core.Coord;
import nl.gremmee.fireworks.objects.Rocket;

/**
 * Main
 */
public class Fireworks extends Canvas implements Runnable {

    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private boolean running = false;
    private Handler handler;
    private int frames = 0;
    private Random random = new Random();

    private Thread thread;

    /**
     * Creates a new Game object.
     */
    public Fireworks() {
        handler = new Handler();

        new Window(WIDTH, HEIGHT, "FireWorks", this);
    }

    // ~ Methods
    // --------------------------------------------------------------------------------------

    public static void main(String[] aArgs) {
        new Fireworks();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                int rockets = handler.getRockets();
                int particles = handler.getParticles();
                int sparkles = handler.getSparkles();
                System.out.println("FPS: " + frames + " : Rockets " + rockets + " : particles " + particles
                        + " : sparkles " + sparkles);
                frames = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0, 0, 0, 15));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (random.nextInt(Fireworks.HEIGHT / 5) < 1) {
            // Coord coord = new Coord(random.nextInt(WIDTH), HEIGHT);
            Coord coord = new Coord(WIDTH / 2, HEIGHT);
            handler.addObject(new Rocket(coord, ID.Rocket));
        }

        handler.render(g);

        g.setColor(Color.WHITE);
        g.drawString("FPS " + frames, 12, 12);

        g.dispose();
        bs.show();
    }

    private void update() {
        handler.update();
    }
}
