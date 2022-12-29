package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Orientation;

public class BackGround implements EntityFactory {
    //��̬����
    @Spawns("bg1")
    public Entity RollBG(SpawnData data){
        ScrollingBackgroundView bg1 =new ScrollingBackgroundView(
                FXGL.image("bg/RollBg.png")
                ,FXGL.getAppWidth(),FXGL.getAppHeight(), Orientation.VERTICAL);

        return FXGL.entityBuilder(data)
                .view(bg1)
                .build();

    }
    //�����ͨ����Ʒ�ƶ���ʹ��������
    @Spawns("reference")
    public Entity reference(SpawnData data){
        return FXGL.entityBuilder()
                .build();
    }
}
