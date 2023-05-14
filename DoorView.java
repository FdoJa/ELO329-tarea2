import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class DoorView extends Group {
    public DoorView(int x, int y, int angle) {
        makeDoorViewWithoutSensor();

        Bounds bounds = getBoundsInParent();
        double centerX = bounds.getMinX() + bounds.getWidth() / 2.0;
        double centerY = bounds.getMinY() + bounds.getHeight() / 2.0;

        getTransforms().add(new Rotate(-angle, centerX, centerY));  // to rotate at anchor pivot (40,50)


        relocate(x, y);
        System.out.println("Coordenadas (x,y) de puerta-> x:" + getLayoutX() + "y:" + getLayoutY());
        prepareOpen_CloseTransition();
    }

    private void makeDoorViewWithoutSensor() {
        Polygon origenPillar = new Polygon();
        origenPillar.getPoints().addAll(0d, 0d,
                0d, 20d,
                10d, 20d,
                10d, 10d,
                20d, 10d,
                20d, 0d);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Polygon(
                160d, 0d,
                160d, 10d,
                170d, 10d,
                170d, 20d,
                180d, 20d,
                180d, 0d);
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        slidingSheet = new Rectangle(10, 10, 160, 10);
        slidingSheet.setFill(Color.BURLYWOOD);
        Rectangle border = new Rectangle(0, 0, 180, 180);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.GRAY);
        border.getStrokeDashArray().addAll(4d, 4d);
        getChildren().addAll(border);
        getChildren().addAll(origenPillar, switchPillar, slidingSheet);
    }

    public void setDoorModel(Door model) {
        doorModel = model;
        setOnMouseClicked(event -> {
            doorModel.changeState();
        });
    }

    public void addMagneticSensorView(MagneticSensorView msView) {
        placeMagneticSensor(msView);
        getChildren().add(msView);

    }

    private void placeMagneticSensor(MagneticSensorView mv) {
        mv.getSwitchView().setY(switchPillar.getBoundsInLocal().getHeight() - 1);
        mv.getSwitchView().setX(slidingSheet.getX() + slidingSheet.getWidth());

        mv.getMagnetView().translateXProperty().bind(slidingSheet.xProperty().add(slidingSheet.widthProperty()).add(-mv.getMagnetView().getWidth()));
        mv.getMagnetView().translateYProperty().bind(slidingSheet.yProperty().add(slidingSheet.heightProperty()).add(0));
    }

    private void prepareOpen_CloseTransition(){
        transition = new TranslateTransition(Duration.millis(0), slidingSheet);
        transition.setCycleCount(1);
        transition.setOnFinished(event -> {
            doorModel.finishMovement();
        });
    }
    public void startOpening(){
        transition.stop();
        slidingSheet.setWidth(10);
        slidingSheet.setHeight(160);
        /*
        transition.setFromX(slidingSheet.getTranslateX());
        transition.setToX(160);
        */
        transition.play();
    }
    public void startClosing(){
        transition.stop();
        slidingSheet.setWidth(160);
        slidingSheet.setHeight(10);
        /*
        transition.setFromX(slidingSheet.getTranslateX());
        transition.setToX(0);
        */
        transition.play();
    }

    private TranslateTransition transition;
    private Door doorModel;
    private Polygon switchPillar;
    private Rectangle slidingSheet;
}