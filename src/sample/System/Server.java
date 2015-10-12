package sample.System;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

/**
 * Created by ������� on 25.09.2015.
 */


public class Server implements Runnable {
    public Thread t;
    String name;

    public Server (String name){
        this.name = name;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        File file = new File(name + ".txt");
        System.out.println("����� ������, ������� 1�");
        try { // ����� �����
            acceptFile(file, 8033, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void acceptFile(File file, int port,int pacSize) throws IOException {
        byte data[] = new byte[pacSize];
        DatagramPacket pac = new DatagramPacket(data, data.length);
        DatagramSocket s = new DatagramSocket(port);
        FileOutputStream os = new FileOutputStream(file);
        try {
            /* ��������� ������� ��������: ���� � ������� 10 ������
            �� ������� �� ������ ������, ����� ������ �������������*/
            s.setSoTimeout(10000);
            while (true) {
                s.receive(pac);
                os.write(data);
                os.flush();
            }
        } catch (SocketTimeoutException e) {
            // ���� ����� �������� �����
            //e.printStackTrace();
            System.out.println("������� ����� ��������, ����� ������ ��������");
        } finally {
            os.close();
            s.close();
        }
    }
}
