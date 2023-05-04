import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Stage1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();   // this way we get access to program's arguments
        if (args.size() != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = null;
        try {
            in = new Scanner(new File(args.get(0)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File: " + args.get(0));
            System.exit(-1);
        }
        House house = new House(in);
        in.close();
        Scene scene = new Scene(house, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("T2 Stage 1");

        // Evento en base a click en interfaz
        scene.setOnMouseClicked(event -> {
            // WindowView se compone de varios rectangulos, por lo que al cliquear sobre una se obtendrá un Rectangle
            if (event.getTarget() instanceof Rectangle) {
                // Dado el Rectangle, verificar si su clase padre es una WindowView, y si lo es abrir/cerrar la ventana
                Node parent = ((Rectangle) event.getTarget()).getParent();
                if (parent instanceof WindowView) {
                    WindowView wView = (WindowView) parent;
                    wView.getWindowModel().changeState();
                }
            }
        });

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
