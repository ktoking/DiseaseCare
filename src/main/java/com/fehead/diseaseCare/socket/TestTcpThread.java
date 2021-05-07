package com.fehead.diseaseCare.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TestTcpThread {
    public static void main(String[] args) {
        for (int k = 0; k < 10; k++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1",17781);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    for (int i = 0; i < 5; i++) {
                        bufferedWriter.write(""+i);
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
