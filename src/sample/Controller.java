package sample;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class Controller {
    public TextField textB;
    public TextField textC;
    public TextField textA;
    @FXML
    private ImageView imageZadanie;
    @FXML
    Button buttonGo;

    @FXML
    private TextField serverPort;

    public Controller() throws MalformedURLException {
    }


    public void buttonActGo(ActionEvent actionEvent) throws IOException {
        DatagramSocket st=new DatagramSocket();
        DatagramPacket dp = null;
        InetAddress loc=InetAddress.getByName("localhost");
        byte [] buf =new byte[100];
        buf=textA.getText().getBytes();
        dp=new DatagramPacket(buf, buf.length,loc, Integer.parseInt(serverPort.getText()) );
        st.send(dp);
        buf=textB.getText().getBytes();
        dp=new DatagramPacket(buf, buf.length,loc, Integer.parseInt(serverPort.getText()) );
        st.send(dp);
        buf=textC.getText().getBytes();
        dp=new DatagramPacket(buf, buf.length,loc,8888 );
        st.send(dp);
        buf=new byte[100];
        dp=new DatagramPacket(buf, 100);
        st.receive(dp);
        String buff=new String(dp.getData());
        buff=buff.substring(0, dp.getLength());
        showAlertWithHeaderText(buff);
    }
    private void showAlertWithHeaderText(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Клиент");
        alert.setHeaderText("Results: "+ s);
        alert.setContentText("Получен ответ от сервера!");
        alert.showAndWait();

    }
}
