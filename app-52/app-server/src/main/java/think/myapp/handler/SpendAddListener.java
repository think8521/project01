package think.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Member;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;

public class SpendAddListener implements SpendActionListener {

  char daily;
  SpendDao spendDao;
  SqlSessionFactory sqlSessionFactory;

  public SpendAddListener(char daily, SpendDao spendDao, SqlSessionFactory sqlSessionFactory) {
    this.daily = daily;
    this.spendDao = spendDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Spend s = new Spend();
    s.setSpend(prompt.inputString("지출명? "));
    s.setPrice(prompt.inputInt("금액? "));
    s.setWho((Member) prompt.getAttribute("loginUser"));
    s.setDaily(daily);

    try {
      spendDao.insert(s);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
