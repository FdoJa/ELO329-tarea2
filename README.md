# Tapia Nicolas Rojas Aymara Guzman Maria Delgado Fernando TareaN2


# Tarea 2: Simulacion de una alarma domestica a través de una interfaz gráfica y eventos
## Integrantes
- Fernando Delgado
- Maria Guzman
- Aymara Rojas
- Nicolás Tapia

## Archivos que componen el proyecto
* El archivo de entrada "config.txt"
* El archivo makefile
* El archivo "siren.wav" el cual corresponde al sonido de la alarma
* Las clases "Central" "CentralView" "Door" "DoorView" "House" "MagneticSensor" "MagneticSensorView" "Person" "PersonView" "PIR_detector" "PIR_detectorView" "Sensor" "Siren" "SirenView" "Window" "WindowView" "Stage4" "State" "SwitchSatate" (todo con el tipo de formato.java) 

## Requerimientos
1) Consola o terminal para ejecutar el programa (Ej: Cmd Windows o Terminal Linux)
2) Tener instalado la ultima version de java
3) Tener instalado la version 17 del Software Development Kit(SDK)
4) Tener instalado javaFX, en su versión 17
5) Tener instalado make

## Como compilar
1) Dirigirse al directorio en donde está guardado el proyecto 
2) Abrir el archivo makefile y cambiar el parametro JFX_OPTIONS para que apunte al directorio de javaFX donde usted lo haya instalado
3) Iniciar la terminal en el stage que desea compilar 
4) Ejecutar el comando "make"

## Ejecucción 
* Para ejecutar el programa realizar el comando "make run".
* Para limpiar la carpeta de los archivos de compilación ejecutar el comando "make 

## Consideraciones
* Se ha utilizado una branch para cada stage, esta branch es solo de introducción.
* Debido a complicaciones, el stage4 no realiza el correcto funcionamiento para detectar a las personas en los PIRs; por lo que, aún cuando se tenga acceso correcto a la vista de personas y PIRs, la alarma no sonará.
* Se ha utilizado la función relocate(x,y) para localizar a los distintos objetos sobre el plano de la interfaz, por lo que su ubicación puede ser distinta a lo esperado dado que relocate coloca el punto de la esquina superior derecha del objeto en la posición (x,y) como si este fuera este su centro.
* Las puertas se abren de inmediato sin una animación de rotación, pero si cumplen con que se abren rotando.

## Documentacion Javadoc
Para generar la documentacion en IntelliJ IDEA, se debe de abrir el proyecto en este IDE y luego ir a la pestaña de "Tools" y seleccionar la opcion "Generate JavaDoc". Luego de esto se debe de seleccionar la carpeta donde se guardara la documentacion y presionar "Ok". Finalmente se debe de abrir el archivo index.html o cualquier otro con la misma extension, que se encuentren en la carpeta seleccionada para ver la documentacion.

