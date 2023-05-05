import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class WindowView extends Group {
    public WindowView(int x, int y, int angle){
        System.out.println("Coordenada x:" + x +"COordenada y: " + y );
        makeWindowViewWithoutSensor();
        // Rotar según pivote, en este caso centro de la ventana
        getTransforms().add(new Rotate(angle,90, 10));
        relocate(x,y);
        prepareOpen_CloseTransition();
    }
    private void makeWindowViewWithoutSensor(){
        Rectangle origenPillar = new Rectangle(0, 0, 20, 20);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Rectangle(180, 0, 20, 20);
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        Rectangle fixedGlas = new Rectangle(21, 4, 82, 6);
        fixedGlas.setFill(Color.LIGHTBLUE);
        slidingGlas = new Rectangle(97,11,82,6);
        slidingGlas.setFill(Color.LIGHTBLUE);
        Rectangle border = new Rectangle(0, 0, 200, 20);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(4d,4d );
        getChildren().add(border);
        getChildren().addAll(origenPillar, switchPillar, fixedGlas,slidingGlas);
    }

    public void setWindowModel(Window model) {
        winModel = model;
        setOnMouseClicked(event -> {
            winModel.changeState();
        });
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        //...
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        mv.getMagnetView().setX(slidingGlas.getX()+slidingGlas.getWidth()-mv.getMagnetView().getWidth());
        mv.getMagnetView().setY(slidingGlas.getY()+slidingGlas.getHeight());
        mv.getSwitchView().setY(switchPillar.getBoundsInLocal().getHeight());
        mv.getMagnetView().translateXProperty().bind(slidingGlas.translateXProperty()); // so it moves along with window
    }
    private void prepareOpen_CloseTransition(){
        transition = new TranslateTransition(Duration.millis(2000), slidingGlas);
        transition.setCycleCount(1);
        transition.setOnFinished(event -> {
            winModel.finishMovement();
        });
    }
    public void startOpening(){
        transition.stop();
        transition.setFromX(slidingGlas.getTranslateX());// in case the user decides to close before it opens.
        transition.setToX(82);
        transition.play();
    }
    public void startClosing(){
        transition.stop();
        transition.setFromX(slidingGlas.getTranslateX());  // in case the user decides to open before it closes.
        transition.setToX(0); // original position
        transition.play();
    }
    private TranslateTransition transition;
    private Window winModel;
    private Rectangle switchPillar;
    private Rectangle slidingGlas;

}

