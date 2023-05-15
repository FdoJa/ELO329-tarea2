import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stage4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();
        if (args.size() != 1) {
            System.out.println("Usage: java Stage3 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = null;
        try {
            in = new Scanner(new File(args.get(0)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Siren siren = new Siren("http://profesores.elo.utfsm.cl/~agv/elo329/JavaProg/JavaFX/AudioMediaDemo/siren.wav");
        Central central = new Central(siren);
        House house = new House(in, central);
        in.close();
        people = new ArrayList<Person>();


        ToolBar toolbar = new ToolBar();

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(event -> {
            PersonView personView = new PersonView(10,10);
            Person p = new Person(10,10, personView);
            people.add(p);

            personView.setOnMouseClicked(event1 -> {
                central.checkPeople(people);
            });
            house.getChildren().add(personView);
        });

        toolbar.getItems().add(insertButton);

        VBox vBox = new VBox(20);
        Separator hSeparator = new Separator(Orientation.HORIZONTAL);
        vBox.getChildren().addAll(siren.getView(),hSeparator, central.getView());
        vBox.setPadding(new Insets(10, 10,10,10));
        vBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(house);
        borderPane.setCenter(new Separator(Orientation.VERTICAL));
        borderPane.setRight(vBox);
        borderPane.setPadding(new Insets(10,10,10,10));
        borderPane.setTop(toolbar);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ELO329: T2 Stage 4");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public ArrayList<Person> people;
}