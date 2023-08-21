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

@WebServlet("/spend/update")
public class SpendUpdateServlet extends HttpServlet {

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



    Spend s = new Spend();
    s.setNo(Integer.parseInt(request.getParameter("no")));
    s.setSpend(request.getParameter("spend"));
    s.setPrice(Integer.parseInt(request.getParameter("price")));
    s.setDaily(request.getParameter("daily").charAt(0));
    System.out.println(request.getParameter("daily"));
    s.setWho(loginUser);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/spend/list?daily=%s'>\n",
        request.getParameter("daily"));
    out.println("<title>지출내역 관리 시스템 - 수정</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지출내역 수정</h1>");
    try {
      if (InitServlet.spendDao.update(s) == 0) {
        out.println("<p>변경 권한이 없습니다.</p>");
      } else {
        out.println("<p>변경했습니다!</p>");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}
