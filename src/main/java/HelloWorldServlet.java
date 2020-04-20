import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        if(req.getParameterMap().containsKey("name") && !req.getParameter("name").isEmpty()) {
            out.println("<h1>Hello, " + req.getParameter("name") + "!</h1>");
        } else {
            out.println("<h1>Hello World!</h1>");
        }
    }
}
