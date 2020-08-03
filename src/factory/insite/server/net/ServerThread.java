package factory.insite.server.net;

import factory.insite.server.Configuration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
    private boolean alive = true;
    private ServerSocket serverSocket = null;
    private Configuration configuration;

    public ServerThread(Configuration configuration) {
        this.configuration = configuration;
    }

    private void showBanner(){
        System.out.println("Starting server on " + configuration.getHost() + ":" + configuration.getPort());
    }

    @Override
    public void run() {
        showBanner();
        openServerSocket();
        while (isAlive()){
            Socket clientSocket = null;
            try{
                clientSocket = this.serverSocket.accept();
                System.out.println("Connected -> " + clientSocket.getInetAddress().getHostAddress());
            }catch (IOException e){
                if(!isAlive()){
                    System.out.println("Server stopped");
                    return;
                }
                e.printStackTrace();
            }
            Thread clientHandler = new Thread(
                    new ClientHandler(clientSocket,configuration)
            );
            clientHandler.setName("ClientHandler");
            clientHandler.start();
        }
    }

    private synchronized boolean isAlive() {
        return this.alive;
    }

    public synchronized void stopServerThread(){
        this.alive = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openServerSocket(){
        try {
            this.serverSocket = new ServerSocket(
                    configuration.getPort(),
                    configuration.getBacklog(),
                    InetAddress.getByName(configuration.getHost())
                   );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
