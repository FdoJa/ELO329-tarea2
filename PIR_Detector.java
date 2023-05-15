import java.security.PublicKey;

public class PIR_Detector {
    public PIR_Detector(float x, float y, int direction_angle, int sensing_angle, int sensing_range, Sensor sensor, PIR_DetectorView view){
        this.x = x;
        this.y = y;
        this.directon_angle = Math.toRadians(direction_angle);
        this.sensing_angle = Math.toRadians(sensing_angle);
        this.sensing_range = sensing_range;

        PIRsensor = sensor;
        state = State.CLOSE;
        pView = view;
        pView.setPirModel(this);
    }

    public void inAreaOfDetection(){
        PIRsensor.setClose(false);
    }

    public void notInAreaOfDetection(){
        PIRsensor.setClose(true);
    }

    public boolean getDetection(){
        return PIRsensor.isClose();
    }

    public void detection(double x, double y){
        double dx = x - this.x;
        double dy = y - this.y;
        double distance = Math.sqrt(dx*dx + dy*dy);

        if (distance > sensing_range){
            notInAreaOfDetection();
            return;
        }

        if (x == this.x && y == this.y){
            inAreaOfDetection();
            return;
        }

        double start = directon_angle/2;
        double end = directon_angle/2;
        double length = end - start;

        double angle = Math.atan2(dy,dx);

        if (angle > start && angle < length){
            inAreaOfDetection();
        } else {
            notInAreaOfDetection();
        }

    }

    private float x,y;
    private int sensing_range;
    private double directon_angle, sensing_angle;
    private final PIR_DetectorView pView;
    private final Sensor PIRsensor;
    private State state;
}
