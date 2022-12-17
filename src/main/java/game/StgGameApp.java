package game;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class StgGameApp extends GameApplication {
    private Entity player;
    public double speed;
    public static void main(String[] args) {
        launch(args);
    }
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double x){
        speed = x;
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
        FXGL.onKey(KeyCode.RIGHT, () -> {
            player.translateX(getSpeed()); // move right speed pixels
        });

        FXGL.onKey(KeyCode.LEFT, () -> {
            player.translateX(-getSpeed()); // move left speed pixels
        });

        FXGL.onKey(KeyCode.UP, () -> {
            player.translateY(-getSpeed()); // move up speed pixels
        });

        FXGL.onKey(KeyCode.DOWN, () -> {
            player.translateY(getSpeed()); // move down speed pixels
        });

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
                .view(new Rectangle(25, 25, Color.YELLOW))
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
