package game;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;

import java.util.Map;

public class StgGameApp extends GameApplication {
    public static void main(String[] args) {
        launch(args);
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
