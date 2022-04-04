package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Api;
import util.OkHttpUtil;

import java.io.File;
import java.util.List;

public class FileController {

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton saveButton;

    @FXML
    private Text text;

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) text.getScene().getWindow();
        stage.close();
    }

    File file;
    @FXML
    void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("文件选择");
        file =  fileChooser.showOpenDialog(saveButton.getScene().getWindow());

        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );

        if (file != null){
            text.setText("选择文件路径："+file.getAbsolutePath());
        }
    }


    @FXML
    void upload(ActionEvent event) {
        if (file == null){
            return;
        }

        OkHttpUtil client = new OkHttpUtil();
        client.uploadFile(Api.uploadFile , file , file.getName());
    }

}
