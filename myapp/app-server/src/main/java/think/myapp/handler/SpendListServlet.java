package think.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;

@WebServlet("/spend/list")
public class SpendListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SpendDao spendDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

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
    out.println("<title>지출내역 관리 시스템 - 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>지출내역 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.printf("<a href='/spend/form.html'>지출내역 추가</a>\n");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>지출명</th> <th>작성자</th> <th>금액</th> <th>등록일</th></tr>");
    out.println("</thead>");

    List<Spend> list = InitServlet.spendDao.findAll(daily);

    out.println("<tbody>");
    for (Spend s : list) {
      out.printf(
          "<tr>" + " <td>%d</td>" + " <td><a href='/spend/detail?daily=%s&no=%d'>%s</a></td>"
              + " <td>%s</td>" + " <td>%d</td>" + " <td>%s</td></tr>\n",
          s.getNo(), s.getDaily(), s.getNo(), (s.getSpend().length() > 0 ? s.getSpend() : "제목없음"),
          s.getWho().getName(), s.getPrice(), dateFormatter.format(s.getCreatedDate()));
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

  // @Override
  // public void service(BreadcrumbPrompt prompt) throws IOException {
  // prompt.println("-----------------------------------");
  // prompt.println("번호, 지출자, 지출명, 금액, 등록일");
  // prompt.println("-----------------------------------");
  //
  // int total = 0;
  // List<Spend> list = spendDao.findAll(daily);
  // for (Spend s : list) {
  // prompt.printf("%d, %s, %s, %d원, %s\n", s.getNo(), s.getWho().getName(), s.getSpend(),
  // s.getPrice(), dateFormatter.format(s.getCreatedDate()));
  // total += s.getPrice();
  // }
  // prompt.printf("총액 : %d원\n", total);
  // }

}


