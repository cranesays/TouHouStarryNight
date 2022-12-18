package game.componets;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.Node;

public class BulletComponent extends Component {
    public BulletComponent(){

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(FXGL.texture("bullet/etama_remi_1.png"));
    }
}
