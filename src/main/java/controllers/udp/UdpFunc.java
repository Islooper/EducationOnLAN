package controllers.udp;

import java.io.IOException;
import java.net.*;

public class UdpFunc {

    DatagramSocket udp;

    /**
     * 在指定端口监听udp消息
     * @param port
     */
    UdpFunc(int port) {
        try {
            udp=new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向指定的ip地址的端口发送消息
     * @param message 要发送的消息
     * @param port 指定的端口
     * @param ip 指定的ip地址
     */
    void send(String message,int port,String ip) {
        try {
            byte temp[]=message.getBytes();
            InetAddress address=InetAddress.getByName(ip);
            DatagramPacket send_packet=new DatagramPacket(temp,temp.length,address,port);
            udp.send(send_packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从端口读取报文并返回报文数据
     * @return 报文数据
     */
    String read() {
        byte temp[]=new byte[2048];
        DatagramPacket receive_packet=new DatagramPacket(temp,temp.length);
        try {
            udp.receive(receive_packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result=new String(receive_packet.getData(),0,receive_packet.getLength());
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        UdpFunc udpFunc = new UdpFunc(8888);
        udpFunc.send("hello",8888 , "127.0.0.1");
        Thread.sleep(1000);
        System.out.println(udpFunc.read());;
    }
}
