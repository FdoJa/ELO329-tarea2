import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ArrayList;

public class Central {
    public Central(Siren siren){
        view = new CentralView(this);
        zones = new ArrayList<Sensor>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200),
                e-> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
    }
    public VBox getView (){
        return view;
    }
    public void armAll() {
        System.out.println("Entro en armAll");
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"");

        // TOdas las zonas a armar deben estar cerradas
        if(close[0] && close[1] && close[2] ) {
            state = CentralState.ALL_ARMED;
            periodicCheck.play();
        } else {
            System.out.println("2");
            System.out.println(msg);
        }
    }
    public void armPerimeter() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"");

        // ZOnas 0 y 1 deben estár cerradas
        if(close[0] && close[1] ) {
            state = CentralState.PERIMETER_ARMED;
            periodicCheck.play();
        } else {
            System.out.println(msg);
            return;
        }
    }
    public void disarm() {
        state = CentralState.DISARMED;
        siren.stop();
        periodicCheck.stop();
    }
    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        for (Sensor sensor : zones)
            close[sensor.getZone()] &=sensor.isClose();
        return close;
    }
    public void addNewSensor(Sensor s){
        zones.add(s);
    }
    private void checkZones(){
        boolean[] close = checkCloseZones();
        switch (state) {
            case ALL_ARMED -> {
                System.out.println("Revisando todos los sensores");
                for (Sensor sensor : zones){
                    if (!sensor.isClose()){
                        siren.play();
                    }
                }
            }
            case PERIMETER_ARMED -> {
                for (Sensor sensor : zones){
                    if (sensor.getZone() == 0 || sensor.getZone() == 1){
                        if (!sensor.isClose()){
                            siren.play();
                        }
                    }
                }
            }
        }
    }
    enum CentralState {
        ALL_ARMED, PERIMETER_ARMED, DISARMED
    }
    private final ArrayList<Sensor> zones;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;
    private final CentralView view;
}

