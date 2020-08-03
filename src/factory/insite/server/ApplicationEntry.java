package factory.insite.server;

import factory.insite.server.net.ServerThread;

public class ApplicationEntry {

    public static void main(String[] args){
         ServerThread server = new ServerThread(Configuration.getInstance());
         Thread serverThread = new Thread(server);
         // PREPARE start
            serverThread.setName("FST_ServerThread");
            serverThread.setPriority(7);
         // PREPARE end
         serverThread.start();
         try {
             serverThread.join();
         }catch (InterruptedException e){
             System.out.println("ServerThread interrupted... Exiting...");
         }
         System.out.println("Server stopped");
    }
}
