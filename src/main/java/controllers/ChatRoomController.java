package controllers;

import controllers.common.UdpConfig;
import controllers.dao.Message;
import controllers.udp.UdpFunc;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {
    @FXML
    private TextArea textSend;

    @FXML
    private Label labUserName;
    @FXML
    private ListView chatWindow;

    private ObservableList<Message> chatReccder;

    private static ChatRoomController instance;


    public ChatRoomController() {
        super();
        instance = this;
    }

    public static ChatRoomController getInstance() {
        return instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 获取当前用户
        UserLoader userLoader = UserLoader.getInstance();
        labUserName.setText(userLoader.name);
        textSend.setText("测试发送数据");

        chatReccder = FXCollections.observableArrayList();

        Message message = new Message();
        message.setContent("测试接受数据");
        message.setSpeaker("小明");
        message.setTimer("2022-04-05 14:30:00");
        chatReccder.add(message);

        chatWindow.setItems(chatReccder);

        chatWindow.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                return new ChatCell();
            }
        });
    }

    public static class ChatCell extends ListCell<Message> {
        @Override
        protected void updateItem(Message item, boolean empty) {
            super.updateItem(item, empty);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //inorder to avoid the
                    if (item != null) {
                        VBox box = new VBox();
                        HBox hbox = new HBox();
                        TextFlow txtContent = new TextFlow(createTextNode(item.getContent()));
                        Label labUser = new Label(item.getSpeaker() + "[" + item.getTimer() + "]");
                        labUser.setStyle("-fx-background-color: #7bc5cd; -fx-text-fill: white;");

                        hbox.getChildren().addAll(labUser);
                        UserLoader userLoader = UserLoader.getInstance();
                        if (item.getSpeaker().equals(userLoader.name)) {
                            txtContent.setTextAlignment(TextAlignment.RIGHT);
                            hbox.setAlignment(Pos.CENTER_RIGHT);
                            box.setAlignment(Pos.CENTER_RIGHT);
                        }
                        box.getChildren().addAll(hbox, txtContent);
                        setGraphic(box);
                    } else {
                        setGraphic(null);
                    }
                }
            });
        }
    }


    private static Node createTextNode(String text) {
        Text textNode = new Text(text);
        textNode.setFont(Font.font("Arial", 15));// 字体样式和大小
        return textNode;
    }


    @FXML
    void send(ActionEvent event) {
        // 获取文本内容
        String comment = textSend.getText();

        // 获取当前用户
        UserLoader userLoader = UserLoader.getInstance();

        // 新建message
        Message message = new Message();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        message.setTimer(df.format(new Date()));
        message.setContent(comment);
        message.setSpeaker(userLoader.name);

        // 广播数据包
//        sendUdpPkg(message.toString() , new UdpFunc(UdpConfig.PORT));
        chatReccder.add(message);

        // 清空现有输入框
        textSend.setText("");
    }

    public  ObservableList<Message> getListView() {
        return chatReccder;
    }


    public void sendUdpPkg(String message  , UdpFunc udpFunc) {
        Thread thread = new Thread() {

            @Override
            public void run() {
                while (true){
                    udpFunc.send( message , UdpConfig.PORT, UdpConfig.BROADCAST);
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("发送数据失败");
                    }
                }
            }

        };
        thread.setName("send message thread");
        thread.start();
    }
}
