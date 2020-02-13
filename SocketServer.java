import java.io.* ;
import java.net.* ;

class SocketServer {
/* Sets up a server on port 8181 which you can telnet to.
 * If you send a message, it will reply and then disconnect.
*/
private static String serverName;

public static void main(String [] args) throws IOException {
  // Initial
  serverName = args[0];
  final int port = 8181 ;
  ServerSocket listener = new ServerSocket(port) ;
  System.out.println(serverName + " is running") ;
  // Listen for clients ....
  while (true) {
    Socket client = listener.accept() ;
    new SessionHandler(client).start() ;
    }
  }


  public static String getName() {
    return serverName;
  }


}

class SessionHandler extends Thread {
// an instance created by the server for each client

private BufferedReader in ;
private PrintWriter out ;
private Socket client ;

  SessionHandler(Socket s) {
    client = s ;
    }

  public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(
         client.getInputStream())) ;
      out = new PrintWriter(client.getOutputStream(), true) ;

      // Newcode
      String clientreply = in.readLine();
      String wantedReply = "whoRU";

      if (clientreply.equals(wantedReply)) {
      out.println("My name is " + SocketServer.getName());
    } else {
      out.println("If you ask me nicely, I will tell you who I am");
    }

      System.out.println("A client said: " + clientreply) ;
    } catch (Exception e) { System.out.println("Server error " + e) ; }
    try { client.close() ; }
    catch (Exception e) {;}
  }
}
