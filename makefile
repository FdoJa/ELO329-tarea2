JFLAGS = -g
JC = javac
JVM= java
JFX_OPTIONS = --module-path /home/fer/javaFX/javafx-sdk-17.0.7/lib --add-modules javafx.controls,javafx.media
FILE="config.txt"

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(JFX_OPTIONS) $*.java

CLASSES = \
	Stage4.java \
	House.java \
	MagneticSensor.java \
	MagneticSensorView.java \
	Sensor.java \
	State.java \
	SwitchState.java \
	Window.java \
	WindowView.java \
	Central.java \
	CentralView.java \
	Door.java \
	DoorView.java \
	Siren.java \
	SirenView.java \
	PIR_Detector.java \
	PIR_DetectorView.java \
	Person.java \
	PersonView.java

MAIN = Stage4

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(JFX_OPTIONS) $(MAIN) $(FILE)

clean:
	$(RM) *.class

