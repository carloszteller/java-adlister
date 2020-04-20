import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PageViewCounterServlet", urlPatterns = "/count")
public class PageViewCounterServlet extends HttpServlet {
    private int counter = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        counter++;

        out.println("<p>Page views: " + counter + " (Pass \"?reset=true\" as a parameter in the URL to reset the counter)</p>");

        if(req.getParameterMap().containsKey("reset") && req.getParameter("reset").equals("true")) {
            resetCounter();
            res.sendRedirect("/count");
        }
    }

    public int resetCounter() {
        return counter = 0;
    }
}
