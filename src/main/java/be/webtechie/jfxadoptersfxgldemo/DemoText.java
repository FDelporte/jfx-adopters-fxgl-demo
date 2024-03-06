package be.webtechie.jfxadoptersfxgldemo;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.intelligence.tts.TextToSpeechService;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class DemoText extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);

        settings.addEngineService(TextToSpeechService.class);
    }

    @Override
    protected void initInput() {
    }

    @Override
    protected void initGame() {
        var counter = new AtomicInteger(0);
        var service = FXGL.getService(TextToSpeechService.class);
        service.readyProperty().addListener((a, b, c)
                        -> FXGL.run(() -> {
                    counter.getAndIncrement();
                    System.out.println("Speek loop " + counter.get());
                    service.speak("Hello JFX Adopters Meeting, this is run number " + counter.get());
                }, Duration.seconds(5))
        );
        service.start();
    }

    @Override
    protected void initPhysics() {

    }
}