package think.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spend/form")
public class SpendFormServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    char daily = request.getParameter("daily").charAt(0);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>Project01</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>소비내역</h1>");
    out.println("<form action='/spend/add' method='post'>");
    out.println("소비 <input type='text' name='spend'><br>");
    out.println("가격 <input type='number' name='price'><br>");
    out.println("생필품? <input type='' name='price'><br>");
    out.printf("<input type='hidden' name='daily' value='%s'>\n", daily);
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
