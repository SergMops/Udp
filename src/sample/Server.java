package sample;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.math.BigInteger;
import java.net.*;

public class Server {
    byte [] buf=new byte[100];
    DatagramPacket PacketServer=new DatagramPacket(buf, 100);
    DatagramSocket socketServer;
    TextArea log;
    double sum1=0, sum2=0;
    Server(int port, TextArea a) throws IOException, InterruptedException {
        socketServer=new DatagramSocket(port);
        log=a;
        listen();
    }
    public void listen() throws IOException, InterruptedException {
        while(true) {
            int a, b, c;
            socketServer.receive(PacketServer);
            String buffer = new String(PacketServer.getData());
            buffer = buffer.substring(0, PacketServer.getLength());
            a = Integer.parseInt(buffer);
            System.out.println(a);
            log.appendText("Полученно число а=" + a + " от клиента--->" + PacketServer.getAddress() + ":" + PacketServer.getPort() + "\n");
            //a=Integer.parseInt(str);
            socketServer.receive(PacketServer);
            byte abc[] = new byte[100];
            abc = PacketServer.getData();
            String buffer2 = new String(abc);
            buffer2 = buffer2.substring(0, PacketServer.getLength());
            b = Integer.parseInt(buffer2);
            log.appendText("Полученно число b=" + b + " от клиента--->" + PacketServer.getAddress() + ":" + PacketServer.getPort() + "\n");
            socketServer.receive(PacketServer);
            String buffer3 = new String(PacketServer.getData());
            buffer3 = buffer3.substring(0, PacketServer.getLength());
            c = Integer.parseInt(buffer3);
            log.appendText("Полученно число c=" + c + " от клиента--->" + PacketServer.getAddress() + ":" + PacketServer.getPort() + "\n");
            Thread t1 = new Thread(() -> {
                for (int i = a; i < b; i++) {
                    sum1 += 2 * a * (a + 1);
                }
            }), t2 = new Thread(() -> {
                for (int i = b; i < c; i++) {
                    sum1 += 1 / (b ^ 2 - 1);
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(sum1 + sum2);
            sendBack();
            sum1=0;
            sum2=0;
        }
    }
    public void sendBack() throws IOException {
        String str=String.valueOf(sum1+sum2);
        byte [] send=str.getBytes();
        PacketServer=new DatagramPacket(send, send.length, InetAddress.getByName("localhost"),PacketServer.getPort());
        socketServer.send(PacketServer);
        log.appendText("Сервер отправил ответ клиенту.\nОтвет-> "+str+" Клиент->"+PacketServer.getAddress()+":"+PacketServer.getPort()+"\n");
    }
}
