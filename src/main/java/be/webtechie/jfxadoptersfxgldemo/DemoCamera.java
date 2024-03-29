package be.webtechie.jfxadoptersfxgldemo;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.gesturerecog.HandTrackingService;
import javafx.scene.shape.Circle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class DemoCamera extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);

        settings.addEngineService(HandTrackingService.class);
    }

    @Override
    protected void initInput() {
    }

    @Override
    protected void initGame() {
        var circle = spawnCircle(50, 50);

        var service = FXGL.getService(HandTrackingService.class);
        service.addInputHandler((hand) -> {
            System.out.println(hand.getPoints());
            var pointsHand = hand.getPoints().getFirst();
            circle.setX((1 - pointsHand.getX()) * getAppWidth());
            circle.setY(pointsHand.getY() * getAppHeight());
        });
        service.start();
    }

    @Override
    protected void initPhysics() {

    }

    private Entity spawnCircle(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .viewWithBBox(new Circle(40, 40, 20))
                .collidable()
                .buildAndAttach();
    }
}