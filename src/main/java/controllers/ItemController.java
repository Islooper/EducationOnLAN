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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;



    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(users);
    }

    private User users;
    private MyListener myListener;



    public void setData(User user , MyListener myListener) {
        this.users = user;
        this.myListener = myListener;
        nameLabel.setText(user.getName());
        priceLable.setText(user.getIp());
//        Image image = new Image(getClass().getResourceAsStream("/images/computer_green.png"));
        Image image = new Image("file:/images/computer_green.png");
        img.setImage(image);
    }
}
