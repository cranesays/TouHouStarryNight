package game.componets;

import com.almasb.fxgl.entity.component.Component;

public class ReferMoveComponent extends Component {
    public ReferMoveComponent(){

    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateY(-1);
    }
}
