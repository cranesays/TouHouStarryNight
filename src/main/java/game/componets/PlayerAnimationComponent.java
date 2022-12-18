package game.componets;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.Timer;

public class PlayerAnimationComponent extends Component {
    private int speed = 0;
    private Timer timVal;
    private boolean rightFlag = false;
    private boolean leftFlag = false;
    private AnimatedTexture texture;
    private AnimationChannel anmIdle, anmRight, anmLeft, anmSide;
    public PlayerAnimationComponent() {
        anmIdle = new AnimationChannel(FXGL.image("player/remi.png"), 5, 230/5, 45, Duration.seconds(5), 0, 3);
        anmRight = new AnimationChannel(FXGL.image("player/remi.png"), 5, 230/5, 45, Duration.seconds(0.5), 4, 9);
        anmLeft = new AnimationChannel(FXGL.image("player/remi.png"),5, 230/5, 45, Duration.seconds(0.5), 14, 19);
        //anmSide = new AnimationChannel(FXGL.image("player/remi.png"), 4, 49, 45, Duration.seconds(1), 4, 4);
        texture = new AnimatedTexture(anmIdle);
    }
    @Override
    public void onAdded() {
        //entity.getTransformComponent().setScaleOrigin(new Point2D(24.5, 22.5));
        texture.setTranslateX(-23);
        texture.setTranslateY(-22.5);
        entity.getViewComponent().addChild(texture);
        //entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(46,45)));
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
        //getEntity().setScaleX(-1);
    }
    public void moveLeft() {
        speed = -5;
        //getEntity().setScaleX(1);
    }
}
