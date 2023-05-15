import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.stream.Collectors;

public class PersonView extends Group {
    public PersonView(double x, double y) {
        body = new Rectangle(0,0, 50,20);
        body.setFill(Color.GOLDENROD);
        body.setStroke(Color.BLUE);;
        body.setArcWidth(10);
        body.setArcHeight(10);

        head = new Circle(0, 0, 10);
        head.setFill(Color.BLACK);
        head.setStroke(Color.BLUE);

        head.relocate(x, y);
        body.relocate(x-body.getWidth()/2+head.getRadius(), y);

        getChildren().addAll(body, head);

        setOnMousePressed(this::onMousePressed);
        setOnMouseDragged(this::onMouseDragged);
    }

    public void setPersonModel(Person model){
        perModel = model;
    }

    private void onMousePressed(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            double x = event.getX();
            double y = event.getY();
            head.setCenterX(x);
            head.setCenterY(y);
            body.setLayoutX(x);
            body.setLayoutY(y);

            perModel.setX(x);
            perModel.setY(y);
        }

        if (event.isSecondaryButtonDown()) {
            ContextMenu menu = new ContextMenu();
            MenuItem menuItem = new MenuItem("Borrar");
            menuItem.setOnAction(event2 -> {
                getChildren().remove(body);
                getChildren().remove(head);
            });
            menu.getItems().add(menuItem);
            menu.show(head, event.getScreenX(), event.getScreenY());
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            double x = event.getX();
            double y = event.getY();
            head.setCenterX(x);
            head.setCenterY(y);
            body.setLayoutX(x);
            body.setLayoutY(y);

            perModel.setX(x);
            perModel.setY(y);
        }
    }
    private Circle head;
    private Rectangle body;
    private Person perModel;
}