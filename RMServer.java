import java.rmi.registry.* ;
import java.rmi.RemoteException ;
import java.rmi.server.UnicastRemoteObject ;

public class RMServer implements RemoteServer {

      public String getId(String s) throws RemoteException {

        String wantedReply = "whoRU";
        if (s.equals(wantedReply)) {
        return "Server replying: My server name is " + RMServer.getName();
        }
        else {
        return "If you ask me nicely, I will tell you who I am";
        }
      }



      public static String getName() {
        return serverName;
      }

      private static String serverName;

      public static void main(String[] args) {
         try {
           RMServer ms = new RMServer() ;
           RemoteServer stub = (RemoteServer) UnicastRemoteObject.exportObject(ms, 0) ;
           serverName = args[0];

           Registry registry = LocateRegistry.getRegistry() ;
           registry.rebind("myIDserver", stub) ;
           System.out.println("Server is running") ;
         } catch (Exception e) {
           System.out.println("Server failed with exception " + e) ;
           System.exit(1) ;
         }
      }

}
