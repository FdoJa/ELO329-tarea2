public class Door {
    public Door(MagneticSensor sensor, DoorView view) {
        magneticSensor = sensor;
        state = State.CLOSE;
        dView = view;
        dView.addMagneticSensorView(magneticSensor.getView());
        dView.setDoorModel(this);
    }

    public State getState(){
        return state;
    }

    public void changeState() {
        switch (state) {  // this is the enhanced version of switch statement. It does not require breaks.
            case OPEN, OPENING -> {
                state = State.CLOSING;
                dView.startClosing();
            }
            case CLOSE, CLOSING -> {
                state = State.OPENING;
                magneticSensor.setSensorOpen();
                dView.startOpening();
            }
        }
    }
    public void finishMovement(){
        if (state == State.CLOSING){
            state = State.CLOSE;
            magneticSensor.setSensorClose();
        } else if (state == State.OPENING) {
            state = State.OPEN;
            magneticSensor.setSensorOpen();
        }
    }

    private final DoorView dView;
    private final MagneticSensor magneticSensor;
    private State state;
}