package game.componets;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.time.LocalTimer;
import game.EtamaFactory;
import game.GameType;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.time.LocalTime;

public class BossMoveComponent extends Component {
    double HP = 1000;
    LocalTimer timeVal;
    LocalTimer tempTime;
    LocalTimer moveTimer;
    private LocalTimer shootTimer;
    double etamaSpeed=1000;
    boolean flag1=true;
    boolean flag2=false;
    boolean flag3=false;
    boolean flag4=false;
    boolean shootflag1=false;
    int moveflag=0;
    Entity[] etama = new Entity[24];
    Entity hitPoint;
    public BossMoveComponent(){
        timeVal= FXGL.newLocalTimer();
        shootTimer=FXGL.newLocalTimer();
        tempTime=FXGL.newLocalTimer();
        moveTimer=FXGL.newLocalTimer();
        FXGL.getGameWorld().addEntityFactory(new EtamaFactory());
    }
    @Override
    public void onUpdate(double tpf) {
        if(timeVal.elapsed(Duration.seconds(2))&&flag1)
        {
            entity.translateY(5);
            if(timeVal.elapsed(Duration.seconds(2.5))){
                tempTime.capture();
                flag1=false;
                flag2=true;
            }
        }
        if(flag2){
            tempTime.capture();
            Point2D center =entity.getCenter();
            int l=50;
            for(int i=1;i<=12;i++){
            etama[i-1] = FXGL.getGameWorld().spawn("etama1",new SpawnData(entity.getCenter().getX()+i*l,entity.getCenter().getY()+100));
            etama[i+12-1] = FXGL.getGameWorld().spawn("etama1",new SpawnData(entity.getCenter().getX()-i*l-5,entity.getCenter().getY()+100));
            }
            flag2=false;
            flag3=true;
        }
        if(flag3){
            tempTime.capture();
            flag3=false;
            flag4=true;
        }
        if(flag4&&shootTimer.elapsed(Duration.seconds(0.2))){
            Point2D center =entity.getCenter();
            shootTimer.capture();
            int l=30;
            for(int i=1;i<12;i++){
                FXGL.getGameWorld().spawn("etama2",new SpawnData(etama[i-1].getCenter().getX()-10,etama[i-1].getCenter().getY()));
                FXGL.getGameWorld().spawn("etama2",new SpawnData(etama[i+12-1].getCenter().getX()-5,etama[i+12-1].getCenter().getY()));
            }
        }
        if(moveflag==1)
        {

            moveflag=0;
        }

        if(tempTime.elapsed(Duration.seconds(12))){
          flag4=false;
          for(int i=0;i<24;i++){
              etama[i].removeFromWorld();
          }
        }

    }

}
