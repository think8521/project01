package think.myapp.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import think.myapp.dao.MemberDao;
import think.myapp.dao.MySQLMemberDao;
import think.myapp.dao.MySQLSpendDao;
import think.myapp.dao.SpendDao;
import think.util.SqlSessionFactoryProxy;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  public static SqlSessionFactory sqlSessionFactory;
  public static MemberDao memberDao;
  public static SpendDao spendDao;

  public void init() throws ServletException {
    System.out.println("InitServlet.init() 호출됨!");

    try {
      sqlSessionFactory = new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
          .build(Resources.getResourceAsStream("think/myapp/config/mybatis-config.xml")));

      spendDao = new MySQLSpendDao(sqlSessionFactory);
      memberDao = new MySQLMemberDao(sqlSessionFactory);


    } catch (Exception e) {
      System.out.println("InitServlet.init() 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
