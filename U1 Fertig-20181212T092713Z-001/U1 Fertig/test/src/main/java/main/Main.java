package main;


import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.View;


public class Main extends Application {
    View view;
    Controller controller;
    Scene scene;
    static Model model;


    public static void main(String[]args){
        launch(args);
    } //launcht alles
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MP3 Player");
        view = new View();
        controller = new Controller();
        model= new Model();
        controller.link(view, model);

        scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Model getModel(){
        return model;
    }
}
