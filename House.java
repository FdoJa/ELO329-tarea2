import javafx.scene.layout.Pane;

import java.util.Scanner;

public class House extends Pane {
    public House(Scanner in) {
        // reading <#_doors> <#_windows> <#_PIRs>
        in.nextInt();
        int numWindows = in.nextInt();
        in.nextInt();
        for (int i = 0; i < numWindows; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            WindowView windowView= new WindowView(x, y, angle);
            Window window = new Window(zone , windowView);
            getChildren().add(window.getView());
        }
    }
}

