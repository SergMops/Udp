package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerWindow extends Application{
    public static void main(String[] args) throws SocketException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("serverWindow.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 435, 400));
        primaryStage.show();
    }
}
