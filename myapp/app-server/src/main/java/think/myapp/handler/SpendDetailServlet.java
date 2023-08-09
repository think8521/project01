package think.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import think.myapp.vo.Spend;

@WebServlet("/spend/detail")
public class SpendDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    Spend s = InitServlet.spendDao.findBy(request.getParameter("daily").charAt(0),
        Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>지출내역 관리 시스템 - 상세 정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지출내역 조회</h1>");

    if (s == null) {
      out.println("<p>해당 번호의 게시글이 없습니다!</p>");

    } else {
      out.println("<form action='/spend/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          s.getNo());
      out.printf("<tr><th>소비</th>" + " <td><input type='text' name='spend' value='%s'></td></tr>\n",
          s.getSpend());
      out.printf(
          "<tr><th>가격</th>" + " <td><input type='number' name='price' value='%d'></td></tr>\n",
          s.getPrice());
      out.printf(
          "<tr><th>생필품</th>\n" + " <td><select name='daily'>\n"
              + " <option value='Y' %s>생필품</option>\n"
              + " <option value='N' %s>사치품</option></select></td></tr>\n",
          (s.getDaily() == 'Y' ? "selected" : ""), (s.getDaily() == 'N' ? "selected" : ""));
      out.printf("<tr><th>작성자</th> <td>%s</td></tr>\n", s.getWho().getName());
      out.printf("<tr><th>등록일</th> <td>%tY-%1$tm-%1$td</td></tr>\n", s.getCreatedDate());
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/spend/delete?daily=%s&no=%d'>삭제</a>\n", s.getDaily(), s.getNo());
      out.printf("<a href='/spend/list?daily=%s'>목록</a>", s.getDaily());
      out.println("</div>");
      out.println("</form>");

      try {
        // s.setViewCount(s.getViewCount() + 1);
        // InitServlet.spendDao.updateCount(s);
        InitServlet.sqlSessionFactory.openSession(false).commit();

      } catch (Exception e) {
        InitServlet.sqlSessionFactory.openSession(false).rollback();
      }
    }

    out.println("</body>");
    out.println("</html>");

  }
}
