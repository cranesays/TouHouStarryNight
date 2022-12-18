package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import game.componets.EtamaComponent;
import javafx.geometry.Point2D;

public class EtamaFactory implements EntityFactory {
    @Spawns("etama1")
    public Entity etama1(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(GameType.ETAMA)
                //.at(entity.getCenter().getX()+100,entity.getCenter().getY())
                .bbox(BoundingShape.box(15,10))//注意生成的位置
                .collidable()
                .with(new EtamaComponent())
                .with(new ProjectileComponent(new Point2D(0,-1),60))//子弹组件
                .build();
    }
    @Spawns("etama2")
    public Entity etama2(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(GameType.ETAMA)
                //.at(entity.getCenter().getX()+100,entity.getCenter().getY())
                .bbox(BoundingShape.box(15,10))//注意生成的位置
                .collidable()
                .view("bullet/etama_remu_1.png")
                //.with(new EtamaComponent())
                .with(new ProjectileComponent(new Point2D(0,1),600))//子弹组件
                .build();
    }

}
