package sample.System;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

/**
 * Created by Ярослав on 25.09.2015.
 */
public class Client implements Runnable {

    public Client (){
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            byte[] data = new byte[4096];
            DatagramSocket s = new DatagramSocket();
            InetAddress addr = InetAddress.getLocalHost();
            StringTokenizer st = new StringTokenizer(addr.getHostAddress(), ".");
            String myMask = st.nextToken() + "." + st.nextToken() + "." + st.nextToken() + ".";
            addr = InetAddress.getByName(myMask + "255");
            FileInputStream fr;
            DatagramPacket pac;
            fr = new FileInputStream(new File("Analis.txt"));
            while (fr.read(data) != -1) {//создание пакета данных
                pac = new DatagramPacket(data, data.length, addr, 8033);
                //while (true)
                s.send(pac);//отправление пакета
            }
            fr.close();
            System.out.println("Файл отправлен");
        } catch (UnknownHostException e) {
            // неверный адрес получателя
            e.printStackTrace();
        } catch (SocketException e) {
            // возникли ошибки при передаче данных
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // не найден отправляемый файл
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
