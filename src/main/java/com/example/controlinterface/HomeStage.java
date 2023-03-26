// Se define el paquete donde se encuentra la clase
package com.example.controlinterface;

// Se importan las clases necesarias para crear la aplicación de JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

// Se define la clase HomeStage
public class HomeStage extends Application {

    // Se sobrescribe el método "start" de la clase Application para inicializar la vista principal
    @Override
    public void start(Stage stage) throws IOException {

        // Se carga la vista principal
        Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        // Se crea una nueva escena con la vista principal
        Scene scene = new Scene(root);
        // Se establece la escena en la ventana principal
        stage.setScene(scene);
        // Se muestra la ventana principal
        stage.show();
        // Se establece el icono de la ventana
        stage.getIcons().add(new Image("C:/Users/Harold/Desktop/Practices/java/class1/ControlInterface/src/main/resources/CSS/logo.png"));
        // Se establece el título de la ventana
        stage.setTitle("Fundacion Universitaria Compensar");
    }



    // Método principal de la aplicación
    public static void main(String[] args) {
        launch();
    }
}