package com.networking.meetingclient.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(9000);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            new Thread(() -> {
                try {
                    String clientAddress = connectionSocket.getInetAddress().getHostAddress();
                    int clientPort = connectionSocket.getPort();
                    System.out.println("Client connected: " + clientAddress + ":" + clientPort);
                    BufferedReader inFromClient =
                            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                    String clientSentence;
                    while ((clientSentence = inFromClient.readLine()) != null) {
                        System.out.printf("Received %s:%d : %s%n", clientAddress, clientPort, clientSentence);
                        String capitalizedSentence = clientSentence.toUpperCase() + '\n';
                        System.out.printf("Send %s:%d : %s%n", clientAddress, clientPort, capitalizedSentence);
                        outToClient.writeBytes(capitalizedSentence);
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                } finally {
                    try {
                        connectionSocket.close();
                    } catch (IOException e) {
                        System.out.println("Failed to close socket: " + e.getMessage());
                    }
                }
            }).start();
        }
    }
}