package game.componets;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class EtamaComponent extends Component {
    public EtamaComponent(){

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(FXGL.texture("bullet/etama_remu_0.png"));
    }
}
