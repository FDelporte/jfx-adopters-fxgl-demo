package be.webtechie.jfxadoptersfxgldemo;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

/**
 * This is the starting point for an FXGL (game) application.
 * It doesn't add anything to the screen, but you can add game elements and interaction in these methods.
 * Look at DemoGame.java for a working example.
 */
public class Template extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
    }

    @Override
    protected void initInput() {
    }

    @Override
    protected void initGame() {

    }

    @Override
    protected void initPhysics() {

    }
}