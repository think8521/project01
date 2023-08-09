package think.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import think.myapp.vo.Member;
import think.myapp.vo.Spend;

@WebServlet("/spend/add")
public class SpendAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }
    System.out.println(request.getParameter("daily"));
    char daily = request.getParameter("daily").charAt(0);
    int price = Integer.parseInt(request.getParameter("price"));

    Spend s = new Spend();
    s.setSpend(request.getParameter("spend"));
    s.setPrice(price);
    s.setWho(loginUser);
    s.setDaily(daily);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/spend/list?daily=%s'>\n", daily);
    out.println("<title>지출내역 관리 시스템 - 추가</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지출내역 추가</h1>");
    try {
      InitServlet.spendDao.insert(s);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}
