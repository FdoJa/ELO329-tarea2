import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Scanner;

public class House extends Pane {
    public House(Scanner in, Central central) {
        // reading <#_doors> <#_windows> <#_PIRs>
        int numDoors = in.nextInt();
        int numWindows = in.nextInt();
        int numPIRs = in.nextInt();
        for (int i = 0; i < numDoors; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            DoorView doorView = new DoorView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            new Door(sensor, doorView);
            getChildren().add(doorView);
        }
        for (int i = 0; i < numWindows; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            WindowView windowView= new WindowView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            new Window(sensor, windowView);
            getChildren().add(windowView);
        }

        for (int i = 0; i < numPIRs; i++){
            int x = 0,y = 0;
            double f = 0.0;

            try {
                x = in.nextInt();
                y = in.nextInt();
            } catch (NumberFormatException e) {
                try {
                    f = in.nextDouble();
                    x = (int) f;
                    y = in.nextInt();
                } catch (NumberFormatException e2){
                    f = in.nextDouble();
                    y = (int) f;
                }
            }

            int angle = in.nextInt();
            int sensing_angle = in.nextInt();
            int sensing_range = in.nextInt();
            int zone = in.nextInt();
            PIR_DetectorView pirView = new PIR_DetectorView(x, y, angle, sensing_angle, sensing_range);
            Sensor sensor = new Sensor(zone);
            PIR_Detector m = new PIR_Detector(x,y,angle, sensing_angle, sensing_range, sensor, pirView);
            central.addNewPir(m);
            getChildren().add(pirView);
        }

        setMinWidth(700);

    }
}