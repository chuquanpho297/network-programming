package com.networking.meetingclient.socket;

import com.networking.meetingclient.util.JavaFxUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

@Setter
@Getter
@RequiredArgsConstructor
public class TCPClient {
    private final static Logger LOGGER =
            Logger.getLogger(TCPClient.class.getName());
    private BufferedReader inFromServer;
    private int clientPort;
    private String clientAddress;
    private int serverPort;
    private String serverAddress;
    private Socket socket;
    private DataOutputStream outToServer;

    public TCPClient(int serverPort, String serverAddress) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        this.createSocket();
        this.createWriterToServer();
        this.createReaderFromServer();
    }

    private void createReaderFromServer() {
        try {
            if (this.socket == null) {
                LOGGER.warning("Socket is null.");
                return;
            }
            this.inFromServer = new BufferedReader(new InputStreamReader(
                    this.socket.getInputStream()));
        } catch (IOException e) {
            LOGGER.warning("Failed to create reader from server: " + e.getMessage());
        }
    }

    private void createWriterToServer() {
        try {
            if (this.socket == null) {
                LOGGER.warning("Socket is null.");
                return;
            }
            this.outToServer = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            LOGGER.warning("Failed to create writer to server: " + e.getMessage());
        }
    }


    public void send(String message) {
        try {
            if (this.outToServer == null) {
                LOGGER.warning("Socket is null.");
                return;
            }
            System.out.printf("TO SERVER %s:%s : %s\n", this.serverAddress, this.serverPort, message);
            this.outToServer.writeBytes(message + '\n');
        } catch (IOException e) {
            LOGGER.warning("Failed to send message: " + e.getMessage());
        }
    }

    public String receive() {
        try {
            if (this.inFromServer == null) {
                LOGGER.warning("Socket is null.");
                return null;
            }
            String receivedMess = this.inFromServer.readLine();
            System.out.printf("FROM SERVER %s:%s : %s\n", this.serverAddress, this.serverPort, receivedMess);
            return receivedMess;
        } catch (IOException e) {
            LOGGER.warning("Failed to receive message: " + e.getMessage());
            return null;
        }
    }

    public String sendAndReceive(String message) {
        this.send(message);
        return this.receive();
    }

    public void closeSocketConnection() {
        try {
            LOGGER.info("Closing socket");
            this.socket.close();
        } catch (IOException e) {
            LOGGER.warning("Failed to close socket");
        }
    }

    private void createSocket() {
        try {
            this.socket = new Socket(this.serverAddress, this.serverPort);
            this.clientAddress = this.socket.getLocalAddress().getHostAddress();
            this.clientPort = this.socket.getLocalPort();
            LOGGER.info("Socket created");
        } catch (IOException e) {
            LOGGER.warning("Failed to create socket: " + e.getMessage());
        }
    }
}
