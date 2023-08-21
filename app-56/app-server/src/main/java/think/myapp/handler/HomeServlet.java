package think.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import think.myapp.vo.Member;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>지출내역 관리 시스템</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지출 내역 관리 시스템🪙</h1>");
    out.println("<ul>");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("  <li><a href='/auth/form.html'>로그인</a></li>");
    } else {
      out.printf("  <li>%s <a href='/auth/logout'>로그아웃</a></li>", loginUser.getName());
    }

    out.println("  <li><a href='/member/list'>회원</a></li>");
    out.println("  <li><a href='/spend/list?daily=Y'>생필품</a></li>");
    out.println("  <li><a href='/spend/list?daily=N'>사치품</a></li>");
    // out.println(" <li><a href='/auth/form.html'>로그인</a></li>");

    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");

  }
}
