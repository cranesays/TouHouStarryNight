package game.componets;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import java.util.Timer;

public class BossAnimationComponent extends Component {
    private int speed = 0;
    private Timer timVal;
    private boolean rightFlag = false;
    private boolean leftFlag = false;
    private AnimatedTexture texture;
    private AnimationChannel anmIdle, anmRight, anmLeft, anmSide;
    public BossAnimationComponent() {
        anmIdle = new AnimationChannel(FXGL.image("boss/enm_reimu.png"), 4, 256/4, 256/3, Duration.seconds(0.5), 0, 3);
        anmRight = new AnimationChannel(FXGL.image("boss/enm_reimu.png"), 4, 256/4, 256/3, Duration.seconds(0.5), 4, 7);
        anmLeft = new AnimationChannel(FXGL.image("boss/enm_reimu.png"),4, 256/4, 256/3, Duration.seconds(0.5), 8, 11);
        //anmSide = new AnimationChannel(FXGL.image("player/remi.png"), 4, 49, 45, Duration.seconds(1), 4, 4);
        texture = new AnimatedTexture(anmIdle);
    }
    @Override
    public void onAdded() {
        //entity.getTransformComponent().setScaleOrigin(new Point2D(24.5, 22.5));
        entity.getViewComponent().addChild(texture);
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(49,45)));
    }
    @Override
    public void onUpdate(double tpf) {
        if (speed != 0) {

            if (!rightFlag||!leftFlag) {
                if(speed>0&&!rightFlag) {
                    texture.loopAnimationChannel(anmRight);
                    rightFlag=true;
                    leftFlag=false;
                }
                if(speed<0&&!leftFlag)
                {
                    texture.loopAnimationChannel(anmLeft);
                    leftFlag=true;
                    rightFlag=false;
                }
            }
            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.loopAnimationChannel(anmIdle);
                rightFlag = false;
                leftFlag = false;
            }
            entity.translateX(speed);
        }
    }
    public void moveRight() {
        speed = 5;//此处的速度仅为侧面
    }
    public void moveLeft() {
        speed = -5;
        //getEntity().setScaleX(1);
    }
}

