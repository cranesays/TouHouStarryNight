package game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.ui.FXGLButton;

public class CreatePrefabs implements EntityFactory {
    @Spawns("bg0")
    public Entity bgFront(SpawnData data){
        return FXGL.entityBuilder(data).view("bg/backgorund3.png").build();
    }

}
