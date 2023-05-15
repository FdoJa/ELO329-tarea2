public class Person {
    public Person(double x, double y, PersonView view){
        this.x = x;
        this.y = y;
        personView = view;
        personView.setPersonModel(this);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void  setX(double x){
        this.x = x;
    }

    public void  setY(double y){
        this.y = y;
    }

    private double x,y;
    private PersonView personView;
}