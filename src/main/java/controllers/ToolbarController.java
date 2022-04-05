package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToolbarController implements Initializable {

//    private BookReturnCallback callback;

//    public void setBookReturnCallback(BookReturnCallback callback) {
////        this.callback = callback;
//    }

    @FXML
    private JFXButton snapShotBtn;


    private String filePreStr; // 默认前缀（选择存储路径例如： D：\\）
    private String defName = "cameraImg";  // 默认截图名称
    static int serialNum = 0;  //截图名称后面的数字累加
    private String imageFormat; // 图像文件的格式
    private String defaultImageFormat = "png"; //截图后缀
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //获取全屏幕的宽高尺寸等数据


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filePreStr = defName;
        imageFormat = defaultImageFormat;
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
    private void snapShot(ActionEvent event) {

        Stage stage = (Stage) snapShotBtn.getScene().getWindow();
        stage.setIconified(true);

        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        System.out.println(com.getAbsolutePath());
        snapshotRun(com.getAbsolutePath()+"/" , "png");
        snapshot();

    }

    public void snapshotRun(String s, String format) {
        filePreStr = s;
        imageFormat = format;
    }

    public void snapshot() {
        try {
            //拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(
                    new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //根据文件前缀变量和文件格式变量，自动生成文件名
            String name = filePreStr + String.valueOf(timestamp.getTime()) + "." + imageFormat;
            System.out.println(name);
            File f = new File(name);
            System.out.println("Save File-" + name);
            //将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
            System.out.println("..Finished");

        } catch (Exception e) {
            System.out.println(e);
        }
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
