package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToolbarController implements Initializable {

//    private BookReturnCallback callback;

//    public void setBookReturnCallback(BookReturnCallback callback) {
////        this.callback = callback;
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadAddMember(ActionEvent event) {
//        LibraryAssistantUtil.loadWindow(getClass().getResource("/library/assistant/ui/addmember/member_add.fxml"), "Add New Member", null);
    }

    @FXML
    private void loadAddBook(ActionEvent event) {
        loadWindow(getClass().getResource("/fxml/add_book.fxml"), "文件上传", null);
    }

    @FXML
    private void loadMemberTable(ActionEvent event) {
        loadWindow(getClass().getResource("/fxml/chat_room.fxml"), "聊天室", null);
    }

    @FXML
    private void loadBookTable(ActionEvent event) {
//        LibraryAssistantUtil.loadWindow(getClass().getResource("/library/assistant/ui/listbook/book_list.fxml"), "Book List", null);
    }

    @FXML
    private void loadSettings(ActionEvent event) {
//        LibraryAssistantUtil.loadWindow(getClass().getResource("/library/assistant/ui/settings/settings.fxml"), "Settings", null);
    }

    @FXML
    private void loadIssuedBookList(ActionEvent event) {
//        Object controller = LibraryAssistantUtil.loadWindow(getClass().getResource("/library/assistant/ui/issuedlist/issued_list.fxml"), "Issued Book List", null);
//        if (controller != null) {
//            IssuedListController cont = (IssuedListController) controller;
//            cont.setBookReturnCallback(callback);
//        }
    }


    public static final String ICON_IMAGE_LOC = "/images/icon.png";
    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controller;
    }


//    public static void setStageIcon(Stage stage) {
//        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
//    }


}
