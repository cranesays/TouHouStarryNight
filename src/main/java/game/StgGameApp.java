package game;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.KeepOnScreenComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.time.LocalTimer;
import game.componets.*;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class StgGameApp extends GameApplication {
    private Entity player;
    private Entity boss;
    private double HP =1000;
    private Entity hitPoint;
    private double HPPositionX=80;
    private double HPPositionY=20;
    private Entity reference;
    public double speed;
    private LocalTimer shootTimer;
    private double bulletspeed = 1000;
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double x){
        speed = x;
    }
    private int textLocationX =780;
    private int textLocationY =40;
    public static void main(String[] args) {
        launch(args);
    }
    /*界面基础设置*/
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("东方星月夜");
        gameSettings.setVersion("（试行版）");
        gameSettings.setHeight(750);
        gameSettings.setWidth(1000);
        gameSettings.setAppIcon("remi.png");
        gameSettings.setMainMenuEnabled(true);
    }
    /*设置输入控制*/
    @Override
    protected void initInput() {
        setSpeed(5);

        FXGL.onKey(KeyCode.UP, () -> {
            player.translateY(-getSpeed()-1); // move up speed pixels
        });

        FXGL.onKey(KeyCode.DOWN, () -> {
            player.translateY(getSpeed()-1); // move down speed pixels
        });

/*
        FXGL.onKey(KeyCode.RIGHT, () -> {
            player.getComponent(PlayerAnimationComponent.class).moveRight();
        });
*/
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerAnimationComponent.class).moveRight();
            }
        }, KeyCode.RIGHT);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerAnimationComponent.class).moveLeft();
            }
        }, KeyCode.LEFT);


/*

        FXGL.onKey(KeyCode.LEFT, () -> {
            player.getComponent(PlayerAnimationComponent.class).moveLeft();
        });
*/
        /*FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerAnimationComponent.class).moveLeft();
            }
        }, KeyCode.LEFT);*/
        FXGL.getInput().addAction(new UserAction("Shoot") {
            @Override
            protected void onAction() {
                if(!shootTimer.elapsed(Duration.seconds(0.1))){
                    return;
                }
                shootTimer.capture();
                //子弹实体1
                Entity bullet1 = FXGL.entityBuilder()
                        .type(GameType.BULLET)
                        .at(player.getCenter().getX()+15-24,player.getCenter().getY())
                        .bbox(BoundingShape.box(30,15))//注意生成的位置
                        .collidable()
                        .with(new BulletComponent())
                        .with(new ProjectileComponent(new Point2D(0,-1),bulletspeed))//子弹组件
                        .buildAndAttach();
                Entity bullet2 = FXGL.entityBuilder()
                        .type(GameType.BULLET)
                        .at(player.getCenter().getX()-15-24,player.getCenter().getY())
                        .bbox(BoundingShape.box(30,15))
                        .collidable()
                        .with(new BulletComponent())
                        .with(new ProjectileComponent(new Point2D(0,-1),bulletspeed))//子弹组件
                        .with(new OffscreenCleanComponent())
                        .buildAndAttach();

            }
        },KeyCode.Z);

    }
    /*预处理*/
    @Override
    protected void onPreInit() {
    }
    /*设置游戏变量，用于其他类的访问*/
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score",0);
        vars.put("HiScore",0);
        vars.put("Power",0);
        vars.put("Graze",0);
        vars.put("Heart",999);
        //int score = FXGL.geti("score")+1//分数+1
        //FXGL.set("score",score);//导入分数
        //FXGL.INC("score".10)//自增10
    }
    /*游戏初始设置*/
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new BackGround());//实体工厂，背景
        FXGL.spawn("bg1");//添加背景
        reference = FXGL.spawn("reference");//添加背景参照物

        player = FXGL.entityBuilder()
                .type(GameType.PLAYER)
                .at(350, 700)
                //.view(new Rectangle(25, 25, Color.YELLOW))
                //.view("sakuya&remi.png")
                .with(new PlayerAnimationComponent())
                .bbox(BoundingShape.circle(2))
                .collidable()
                .with(new KeepOnScreenComponent())
                .view(new Circle(3,Color.RED))
                .buildAndAttach();
        boss = FXGL.entityBuilder()
                .at(350,-100)
                .type(GameType.BOSS)
                .with(new BossAnimationComponent())
                .bbox(BoundingShape.box(256/4-10,256/3-30))
                .collidable()
                .with(new BossMoveComponent())
                .buildAndAttach();

        hitPoint = FXGL.entityBuilder()
                .view(new Rectangle(HP/2,5,Color.LIGHTGREEN))
                .at(reference.getCenter().getX()+HPPositionX,reference.getCenter().getY()+HPPositionY)
                .with(new ReferMoveComponent())
                .buildAndAttach();


        FXGL.getGameScene().getViewport().bindToEntity(reference,0,0);
        shootTimer = FXGL.newLocalTimer();
    }
    /*设置物理引擎*/
    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameType.BULLET,GameType.BOSS) {
            @Override
            protected void onCollisionBegin(Entity bullet, Entity boss) {
                Point2D center =bullet.getCenter();//查找中心
                bullet.removeFromWorld();//移除子弹
                Circle circle =new Circle(10, Color.GRAY);//圆圈动画
                //创造爆炸实体
                //播放
                Entity boom=FXGL.entityBuilder().at(center).view(circle).buildAndAttach();//爆炸实体
                //.with(new ExpireCleanComponent(duration)//超过指定时间移除实体
                ScaleTransition st=new ScaleTransition(Duration.seconds(0.1),circle);
                st.setToX(3);//水平方向缩放
                st.setToY(3);//垂直方向缩放
                //褪色
                FadeTransition ft=new FadeTransition(Duration.seconds(0.1),circle);
                ft.setByValue(0.5);
                ft.setToValue(0);
                //合并
                ParallelTransition pt = new ParallelTransition(st,ft);
                pt.setOnFinished(event->boom.removeFromWorld());
                pt.play();

                HP=HP-1;
                System.out.println(HP);
                hitPoint.removeFromWorld();
                hitPoint=FXGL.entityBuilder()
                        .view(new Rectangle(HP/2,5,Color.LIGHTGREEN))
                        .at(reference.getCenter().getX()+HPPositionX,reference.getCenter().getY()+HPPositionY)
                        .with(new ReferMoveComponent())
                        .buildAndAttach();
            }
        });
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameType.ETAMA,GameType.PLAYER) {
            @Override
            protected void onCollisionBegin(Entity etama, Entity player) {
                etama.removeFromWorld();

                Point2D center = etama.getCenter();
                Circle circle =new Circle(10, Color.LIGHTPINK);//圆圈动画
                //创造爆炸实体
                //播放
                Entity boom=FXGL.entityBuilder().at(center).view(circle).buildAndAttach();//爆炸实体
                //.with(new ExpireCleanComponent(duration)//超过指定时间移除实体
                ScaleTransition st=new ScaleTransition(Duration.seconds(0.1),circle);
                st.setToX(3);//水平方向缩放
                st.setToY(3);//垂直方向缩放
                //褪色
                FadeTransition ft=new FadeTransition(Duration.seconds(0.1),circle);
                ft.setByValue(0.5);
                ft.setToValue(0);
                //合并
                ParallelTransition pt = new ParallelTransition(st,ft);
                pt.setOnFinished(event->boom.removeFromWorld());
                pt.play();

                int heart = FXGL.geti("Heart")-1;//分数+1
                FXGL.set("Heart",heart);//导入分数
                //FXGL.INC("score".10)//自增10
            }
        });
        if(HP<=0){
            boss.removeFromWorld();
        }
    }
    /*设置UI组件*/
    @Override
    protected void initUI() {
        FXGL.addUINode(new Rectangle(250,750),750,0);
        //Text score =FXGL.addVarText("score",850,40);
        Text score =FXGL.getUIFactoryService().newText(FXGL.getip("Score").asString("Score: %d"));
        score.setLayoutX(textLocationX);score.setLayoutY(textLocationY*2);
        FXGL.addUINode(score);
        Text hiScore =FXGL.getUIFactoryService().newText(FXGL.getip("HiScore").asString("HiScore: %d"));
        hiScore.setLayoutX(textLocationX);hiScore.setLayoutY(textLocationY);
        FXGL.addUINode(hiScore);
        Text heart =FXGL.getUIFactoryService().newText(FXGL.getip("Heart").asString("Player: %d"));
        heart.setLayoutX(textLocationX);heart.setLayoutY(textLocationY*3);
        FXGL.addUINode(heart);
        Text graze =FXGL.getUIFactoryService().newText(FXGL.getip("Graze").asString("Graze: %d"));
        graze.setLayoutX(textLocationX);graze.setLayoutY(textLocationY*5);
        FXGL.addUINode(graze);
        Text power =FXGL.getUIFactoryService().newText(FXGL.getip("Power").asString("Power: %d"));
        power.setLayoutX(textLocationX);power.setLayoutY(textLocationY*4);
        FXGL.addUINode(power);

        //score.setFill(Color.BLUE)//设置字号为蓝色
        //score.fontProperty().unbind();//解除字号绑定
        //score.setFont(Font.font(35));//设置字号

        //FXGL.addUINode(score);
        //Entity scoreboard =entityBuilder().viewWithBBox(new Rectangle(200,750)).buildAndAttach();

    }
    /*update每帧调用，未找到机械帧控件*/
    @Override
    protected void onUpdate(double tpf) {
        //参照物，玩家相对速度
        reference.translateY(-1);
        player.translateY(-1);
        boss.translateY(-1);

    }
}
