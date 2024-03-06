package be.webtechie.jfxadoptersfxgldemo;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.*;

public class DemoGame extends GameApplication {

    private Entity player;

    public enum Type {
        PLAYER, ENEMY
    }

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
        onKey(UP, () -> player.translateY(-5));
        onKey(DOWN, () -> player.translateY(5));
        onKey(LEFT, () -> player.translateX(-5));
        onKey(RIGHT, () -> player.translateX(5));
    }

    @Override
    protected void initGame() {
        player = createPlayer();

        run(() -> {
            createEnemy();
        }, Duration.seconds(0.5));

        var text = new Text();
        text.setFont(Font.font(72));
        text.textProperty().bind(getip("score").asString());
        text.textProperty().subscribe(newValue -> {
            animationBuilder()
                    .duration(Duration.seconds(0.4))
                    .interpolator(Interpolators.BOUNCE.EASE_OUT())
                    .rotate(text)
                    .from(0)
                    .to(360)
                    .buildAndPlay();

            animationBuilder()
                    .duration(Duration.seconds(0.4))
                    .autoReverse(true)
                    .repeat(2)
                    .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                    .scale(text)
                    .from(new Point2D(1, 1))
                    .to(new Point2D(3, 3))
                    .buildAndPlay();
        });

        addUINode(text, 150, 150);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(Type.PLAYER, Type.ENEMY, (p, e) -> {
            e.removeFromWorld();

            inc("score", +1);
        });
    }

    private Entity createPlayer() {
        return entityBuilder()
                .type(Type.PLAYER)
                .at(100, 100)
                .bbox(BoundingShape.box(40, 40))
                .view(new Rectangle(40, 40, Color.BLUE))
                .collidable()
                .buildAndAttach();
    }

    private Entity createEnemy() {
        return entityBuilder()
                .type(Type.ENEMY)
                .at(FXGLMath.random(50, getAppWidth() - 50), FXGLMath.random(50, getAppHeight() - 50))
                .bbox(BoundingShape.box(40, 40))
                .view(new Rectangle(40, 40, Color.RED))
                .collidable()
                .buildAndAttach();
    }
}