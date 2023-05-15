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
        PIRs = new ArrayList<PIR_Detector>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200),
                e-> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
    }
    public VBox getView (){
        return view;
    }
    public boolean armAll() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"");

        // TOdas las zonas a armar deben estar cerradas
        if(close[0] && close[1] && close[2] ) {
            state = CentralState.ALL_ARMED;
            periodicCheck.play();
            return true;
        } else {
            System.out.println(msg);
            return false;
        }
    }
    public boolean armPerimeter() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"");

        // ZOnas 0 y 1 deben estár cerradas
        if(close[0] && close[1] ) {
            state = CentralState.PERIMETER_ARMED;
            periodicCheck.play();
            return true;
        } else {
            System.out.println(msg);
            return false;
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

    public void addNewPir(PIR_Detector m){
        PIRs.add(m);
    }
    private void checkZones(){
        boolean[] close = checkCloseZones();
        switch (state) {
            case ALL_ARMED -> {
                if (!close[0] || !close[1] || !close[2]){
                    siren.play();
                }
            }
            case PERIMETER_ARMED -> {
                if (!close[0] || !close[1]){
                    siren.play();
                }
            }
        }
    }

    public void checkPeople(ArrayList<Person> people){
        for (Person h : people){
            double x = h.getX();
            double y = h.getY();
            for (PIR_Detector s : PIRs){
                s.detection(x,y);
                if (s.getDetection()){
                    for (Sensor z : zones){
                        if (z.getZone() == 2){
                            z.setClose(false);
                            return;
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
    private ArrayList<PIR_Detector> PIRs;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;
    private final CentralView view;
}

