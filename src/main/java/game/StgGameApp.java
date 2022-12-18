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
    /*�����������*/
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("��������ҹ");
        gameSettings.setVersion("�����а棩");
        gameSettings.setHeight(750);
        gameSettings.setWidth(1000);
        gameSettings.setAppIcon("remi.png");
        gameSettings.setMainMenuEnabled(true);
    }

    /*�����������*/
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

    /*Ԥ����*/
    @Override
    protected void onPreInit() {
    }

    /*������Ϸ����������������ķ���*/
    @Override
    protected void initGameVars(Map<String, Object> vars) {
    }

    /*��Ϸ��ʼ����*/
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new CreatePrefabs());
        FXGL.spawn("bg1");
        player = FXGL.entityBuilder()
                .at(350, 700)
                .view(new Rectangle(25, 25, Color.YELLOW))
                .buildAndAttach();
    }

    /*������������*/
    @Override
    protected void initPhysics() {
    }

    /*����UI���*/
    @Override
    protected void initUI() {

    }

    /*updateÿ֡���ã�δ�ҵ���е֡�ؼ�*/
    @Override
    protected void onUpdate(double tpf) {
    }


}
