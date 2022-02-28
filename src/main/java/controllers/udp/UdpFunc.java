package controllers.udp;

import controllers.OnlineUsers;
import controllers.common.UdpPkgTag;
import controllers.dao.User;
import controllers.common.UdpPkgTag;

import java.io.IOException;
import java.net.*;

public class UdpFunc {

    DatagramSocket udp;

    /**
     * 在指定端口监听udp消息
     *
     * @param port
     */
    public UdpFunc(int port) {
        try {
            udp = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向指定的ip地址的端口发送消息
     *
     * @param message 要发送的消息
     * @param port    指定的端口
     * @param ip      指定的ip地址
     */
    public void send(String message, int port, String ip) {
        try {
            byte[] temp = message.getBytes();
            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket send_packet = new DatagramPacket(temp, temp.length, address, port);
            udp.send(send_packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从端口读取报文并返回报文数据
     */
    public void read() {
        System.out.println("开启监听");
        while (true) {
            // 创建一个包裹
            byte[] bys = new byte[2048];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);
            // 接收数据
            try {
                udp.receive(dp);
                // 解析数据
                String ip = dp.getAddress().getHostAddress();
                String s = new String(dp.getData(), 0, dp.getLength());

                String[] data = s.split(":");
                switch (Integer.parseInt(data[0])) {
                    // 心跳包
                    case UdpPkgTag.HEART:
                        User user = new User();
                        user.setName(data[1]);
                        user.setIp(data[2]);
                        user.setUserName(data[3]);
                        user.setRole(Integer.parseInt(data[4]));

                        OnlineUsers.getInstance().append(user);
                    case UdpPkgTag.CHAT:

                }
                System.out.println("from " + ip + " data is : " + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
