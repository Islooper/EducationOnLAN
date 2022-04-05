package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.alibaba.fastjson.JSONObject;
import controllers.common.UdpConfig;
import controllers.common.UdpPkgTag;
import controllers.udp.UdpFunc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Api;
import util.OkHttpUtil;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author oXCToo
 */
public class LoginController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                try {
                    System.out.println("123");
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/main.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblErrors.setTextFill(Color.GREEN);
        lblErrors.setText("Server is up : Good to go");
    }

    public LoginController() {
//        con = ConnectionUtil.conDB();
    }

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
//            // 发送登陆表单
//            OkHttpUtil http = new OkHttpUtil();
//            HashMap<String, String> form = new HashMap<String, String>();
//            form.put("user_name", email);
//            form.put("password", password);
//            JSONObject res = http.sendPostByForm(Api.loginApi, form);
//            if (res.getInteger("code") != 0) {
//                setLblError(Color.TOMATO, "用户名或密码错误");
//                status = "Fail";
//                return status;
//            }

//            // 获取当前用户信息

//
//            // 角色为学生发送心跳包。角色为老师开启udp监听
//            UdpFunc udpFunc = new UdpFunc(UdpConfig.PORT);
            // 学生
//            if (userLoader.role == 1){
//                // 发送数据
//                String message = UdpPkgTag.HEART +":" + userLoader.name + ":" +userLoader.ip + ":" +
//                        userLoader.userName + ":"+
//                        userLoader.role;
//                sendUdpPkg(message , udpFunc);
//            }else {
//                // 监听数据
////                udpListenerOn(udpFunc);
//            }
            UserLoader userLoader = UserLoader.getInstance();
            userLoader.ip = "127.0.0.1";
            userLoader.userName = "looper";
            userLoader.role = 1;
            userLoader.name = "刘大卫";
            return "Success";
        }

        return status;
    }

    public void sendUdpPkg(String message  , UdpFunc udpFunc) {
        Thread thread = new Thread() {

            @Override
            public void run() {
                while (true){
                    udpFunc.send( message , UdpConfig.PORT, UdpConfig.HOST);
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

    public void udpListenerOn (UdpFunc udpFunc){
        // TODO 监听到的数据处理
        Thread thread = new Thread() {

            @Override
            public void run() {
                udpFunc.read();
            }

        };
        thread.setName("receive message thread");
        thread.start();
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }



}
