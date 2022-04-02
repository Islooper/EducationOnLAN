package controllers;

import controllers.dao.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;


    @FXML
    private AnchorPane ap;


    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(users);
    }

    private User users;
    private MyListener myListener;



    public void setData(User user , MyListener myListener) {
        this.users = user;
        this.myListener = myListener;
        nameLabel.setText("小明");
        priceLable.setText(user.getIp());
//        Image image = new Image(getClass().getResourceAsStream("/images/computer_green.png"));
        File file = new File("images/computer_green.png");
        String string = file.toURI().toString();
        Image image = new Image(string);
        img.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
