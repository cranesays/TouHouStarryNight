package game;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class PlayerAnimationComponent extends Component {
    private int speed = 0;

    private AnimatedTexture texture;
    private AnimationChannel anmIdle, anmMove, anmSide;

    public PlayerAnimationComponent() {
        anmIdle = new AnimationChannel(FXGL.image("player/remi.png"), 1, 50, 45, Duration.seconds(1), 0, 0);
        anmMove = new AnimationChannel(FXGL.image("player/remi.png"), 2, 50, 45, Duration.seconds(0.5), 0, 4);
        anmSide = new AnimationChannel(FXGL.image("player/remi.png"), 2, 50, 45, Duration.seconds(1), 4, 4);
        texture = new AnimatedTexture(anmIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(25, 22.5));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(speed * tpf);

        if (speed != 0) {

            if (texture.getAnimationChannel() == anmIdle) {
                texture.loopAnimationChannel(anmMove);
            }

            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.loopAnimationChannel(anmIdle);
            }
        }
    }

    public void moveRight() {
        speed = 150;
        getEntity().setScaleX(-1);
    }

    public void moveLeft() {
        speed = -150;
        getEntity().setScaleX(1);
    }
}
