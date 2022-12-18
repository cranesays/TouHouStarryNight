package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import game.componets.BossAnimationComponent;

public class Prefabs extends StgGameApp {
    @Spawns("boss")
    public Entity boss(SpawnData data){
        return   FXGL.entityBuilder()
                .at(350,100)
                .type(GameType.BOSS)
                .with(new BossAnimationComponent())
                .bbox(BoundingShape.box(256/4-10,256/3-30))
                .collidable()
                .buildAndAttach();
    }
}
