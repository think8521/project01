package think.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import think.myapp.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setId(request.getParameter("id"));
    member.setPwd(request.getParameter("pwd"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
    out.println("<title>지출내역 관리 시스템 - 회원정보 수정</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원정보 수정</h1>");

    try {
      if (InitServlet.memberDao.update(member) == 0) {
        out.println("<p>회원이 없습니다.</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

}


