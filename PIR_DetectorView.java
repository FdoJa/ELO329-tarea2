import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PIR_DetectorView extends Group {
    public PIR_DetectorView(int x, int y, int angle, int sensing, int range){
        makePIRsView(x, y , range, angle, sensing);
    }

    public void makePIRsView(int x, int y, int radius, int direction, int sensing){
        int start = direction-sensing/2;
        int end = direction+sensing/2;
        int length = end - start;

        arcPIR = new Arc(x, y, radius, radius, start, length);
        arcPIR.setType(ArcType.ROUND);
        arcPIR.setFill(Color.LIGHTPINK);

        double centerX = arcPIR.getCenterX();
        double centerY = arcPIR.getCenterY();

        camera = new Rectangle(0,0, 50,25);
        camera.setFill(Color.GREEN);
        camera.getTransforms().add(new Rotate(direction,camera.getWidth()/2, camera.getHeight()/2));
        camera.relocate(centerX-camera.getWidth()/2,centerY-camera.getHeight()/2);

        getChildren().addAll(arcPIR, camera);
    }

    public void setPirModel(PIR_Detector model) {
        pirModel = model;

    }

    private PIR_Detector pirModel;
    private Arc arcPIR;
    private Rectangle camera;

}
