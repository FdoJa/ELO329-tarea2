public class Window {
    public Window(MagneticSensor sensor, WindowView view) {
        magneticSensor = sensor;
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }

    public State getState(){
        return state;
    }

    public void changeState() {  // is called when this window's view is clicked
        switch (state) {
            case OPEN, OPENING -> {
                state = State.CLOSING;
                wView.startClosing();

            }
            case CLOSE, CLOSING -> {
                state = State.OPENING;
                wView.startOpening();
                magneticSensor.getView().setOpenView();
            }
        }
    }
    public void finishMovement() { // is called when this window ends closing or opening
        if (state == State.CLOSING){
            state = State.CLOSE;
            magneticSensor.setClose(true);
            magneticSensor.getView().setCloseView();
        } else if (state == State.OPENING){
            state = State.OPEN;
            magneticSensor.setClose(false);
        }
    }

    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}
