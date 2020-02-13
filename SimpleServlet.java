import java.io.* ;

import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleServlet extends HttpServlet {


  private static String initarg;

  public void init(ServletConfig config)
	throws ServletException
    {
	super.init(config);
        initarg = config.getInitParameter("example") ;
        System.out.println("Initial parameter: example = " + initarg) ;
    }

  public static String getName() {
    return initarg;
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();
    String [] given = req.getParameterValues("input String");
    System.out.println("A client said: " + given[0]) ;


    String configName = SimpleServlet.getName();
    String messagereply;
    if (given[0].equals("whoRU")) {
      messagereply = " My Name is " + configName;
    }
    else {
      messagereply = "If you ask nicely i will tell you who i am";
    }
    toClient.println("<html><HEAD><TITLE>Simple Servlet Example</TITLE>") ;
    toClient.println("</HEAD><BODY BGCOLOR=\"white\">") ;
    toClient.println("<H1>This is output from the example Servlet</H1>");
    toClient.println(messagereply);
    toClient.println("<p>");
    toClient.println("<HR></BODY></HTML>");
    toClient.close();
  }
}
