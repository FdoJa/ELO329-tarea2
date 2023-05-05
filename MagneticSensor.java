public class MagneticSensor extends Sensor {
    public MagneticSensor(int z){
        super(z);
        view= new MagneticSensorView();
    }
    public void setSensorOpen() {
        setClose(false);
        getView().setOpenView();
    }
    public void setSensorClose() {
        setClose(true);
        getView().setCloseView();
    }
    public MagneticSensorView getView(){ return view;}
    private final MagneticSensorView view;
}
