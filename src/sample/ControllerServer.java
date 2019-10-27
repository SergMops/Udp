package sample;


import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.SocketException;

public class ControllerServer {

    @FXML
    private TextArea textAreaLog;

    @FXML
    private Button buttonStartServer;

    public TextArea getTextAreaLog() {
        return textAreaLog;
    }

    @FXML
    public TextField textAreaPort;

    @FXML
    void startServerAct(ActionEvent event) throws IOException, InterruptedException {
        Task< Void > task = new Task < Void > () {
            @Override
            protected Void call() throws Exception {
                textAreaLog.appendText("Сервер успешно запущен. Port:"+textAreaPort.getText());
        new Server(Integer.parseInt(textAreaPort.getText()), getTextAreaLog());
                return null;
            }
        };
        buttonStartServer.setVisible(false);
        textAreaPort.setVisible(false);
        new Thread(task).start();
    }
}