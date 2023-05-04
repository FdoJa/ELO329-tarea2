public class Window {
    public Window(int zone, WindowView view) {
        magneticSensor = new MagneticSensor(zone);
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }
    public void changeState() {  // is called when this window's view is clicked
        switch (state) {
            case OPEN:
            case OPENING:
                state = State.CLOSING;
                wView.startClosing();
                break;
            case CLOSE:
            case CLOSING:
                state = State.OPENING;
                wView.startOpening();
                break;
        }
    }
    public void finishMovement() { // is called when this window ends closing or opening
        if (state == State.CLOSING){
            state = State.CLOSE;
        } else if (state == State.OPENING){
            state = State.OPEN;
        }
    }
    public WindowView getView(){
        return wView;
    }
    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}
