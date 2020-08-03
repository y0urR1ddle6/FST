package factory.insite.server.net;

import factory.insite.kernel.session.AuthSession;
import factory.insite.server.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final Configuration configuration;
    @Override
    public void run() {
        try(InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream()){
            new AuthSession(in,out).start(configuration);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected <- " + clientSocket.getInetAddress().getHostAddress());
    }

    public ClientHandler(Socket clientSocket, Configuration configuration) {
        this.clientSocket = clientSocket;
        this.configuration = configuration;
    }
}
