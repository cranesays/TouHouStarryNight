package game;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import java.util.Map;

public class StgGameApp extends GameApplication {
    private Entity player;
    public double speed;
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double x){
        speed = x;
    }
    public static void main(String[] args) {
        launch(args);
    }
    /*界面基础设置*/
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("东方星月夜");
        gameSettings.setVersion("（试行版）");
        gameSettings.setHeight(750);
        gameSettings.setWidth(1000);
        gameSettings.setAppIcon("remi.png");
        gameSettings.setMainMenuEnabled(true);
    }

    /*设置输入控制*/
    @Override
    protected void initInput() {
        setSpeed(5);

        FXGL.onKey(KeyCode.UP, () -> {
            player.translateY(-getSpeed()); // move up speed pixels
        });

        FXGL.onKey(KeyCode.DOWN, () -> {
            player.translateY(getSpeed()); // move down speed pixels
        });

        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerAnimationComponent.class).moveRight();
            }
        }, KeyCode.RIGHT);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerAnimationComponent.class).moveLeft();
            }
        }, KeyCode.LEFT);

    }

    /*预处理*/
    @Override
    protected void onPreInit() {
    }

    /*设置游戏变量，用于其他类的访问*/
    @Override
    protected void initGameVars(Map<String, Object> vars) {
    }

    /*游戏初始设置*/
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new CreatePrefabs());
        FXGL.spawn("bg1");
        player = FXGL.entityBuilder()
                .at(350, 700)
                //.view(new Rectangle(25, 25, Color.YELLOW))
                //.view("sakuya&remi.png")
                .with(new PlayerAnimationComponent())
                .buildAndAttach();
    }

    /*设置物理引擎*/
    @Override
    protected void initPhysics() {
    }

    /*设置UI组件*/
    @Override
    protected void initUI() {
    }

    /*update每帧调用，未找到机械帧控件*/
    @Override
    protected void onUpdate(double tpf) {
    }


}
