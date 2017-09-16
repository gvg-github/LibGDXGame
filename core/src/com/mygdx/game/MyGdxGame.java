package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Texture imgA;
    Texture imgB;
    float x;
    float y;
    float vx;
    int width;
    int height;
    boolean direction;
    boolean incr;
    float xA;
    float yA;
    float vxA;

    float xB;
    float yB;
    float vxB;

    public MyGdxGame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
        img = new Texture("Ship.png");
        imgA = new Texture("Asteroid.png");
        imgB = new Texture("Bullet.png");
        x = 100.0f;
        y = 328.0f;
        vx = 100.0f;

        xA = x + 100.0f;
        yA = y - 50.0f;
        vxA = 150.0f;

        xB = 10.0f;
        yB = 100.0f;
        vxB = 1500.0f;
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        update(dt);
        batch.draw(img, x, y);
        batch.draw(imgA, xA, yA);
        batch.draw(imgB, xB, yB);
        batch.end();

    }

    public void update(float dt) {

        vx = ((float) Math.random()) * 1000;
        vxA = ((float) Math.random()) * 1500;
        vxB = ((float) Math.random()) * 2000;

        direction = (int) (Math.random() * 10) < 5 ? false : true;
        incr = (int) (Math.random() * 10) < 5 ? false : true;

        if (direction) {
            updateX(dt, incr);
        } else {
            updateY(dt, incr);
        }
    }

    public void updateX(float dt, boolean incr) {
        float tecX = 0;
        tecX = x + (vx * dt);

        if (tecX > width) {
            x = tecX - width;
        } else {
            x = tecX;
        }

        tecX = xA + (vxA * dt);

        if (tecX > width) {
            xA = tecX - width;
        } else {
            xA = tecX;
        }

        tecX = xB + (vxB * dt);

        if (tecX > width) {
            xB = tecX - width;
        } else {
            xB = tecX;
        }
    }

    public void updateY(float dt, boolean incr) {

        float tecY = 0;
        if (incr) {
            tecY = y + (vx * dt);
        } else tecY = y - (vx * dt);

        if (tecY < 0 || tecY > height) {
            if (tecY < 0) y = height + tecY;
            if (tecY > height) y = tecY - height;
        } else {
            y = tecY;
        }

        if (incr) {
            tecY = yA + (vxA * dt);
        } else tecY = yA - (vxA * dt);

        if (tecY < 0 || tecY > height) {
            if (tecY < 0) yA = height + tecY;
            if (tecY > height) yA = tecY - height;
        } else {
            yA = tecY;
        }

        if (incr) {
            tecY = yB + (vxB * dt);
        } else tecY = yB - (vxB * dt);

        if (tecY < 0 || tecY > height) {
            if (tecY < 0) yB = height + tecY;
            if (tecY > height) yB = tecY - height;
        } else {
            yB = tecY;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        imgA.dispose();
        imgB.dispose();
    }
}
