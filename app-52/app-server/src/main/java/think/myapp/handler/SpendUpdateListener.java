package think.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Member;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendUpdateListener implements ActionListener {

  char daily;
  SpendDao spendDao;
  SqlSessionFactory sqlSessionFactory;

  public SpendUpdateListener(char daily, SpendDao spendDao, SqlSessionFactory sqlSessionFactory) {
    this.daily = daily;
    this.spendDao = spendDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int spendNo = prompt.inputInt("번호? ");

    Spend s = spendDao.findBy(daily, spendNo);
    if (s == null) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    s.setSpend(prompt.inputString("지출내역(%s)? ", s.getSpend()));
    s.setPrice(prompt.inputInt("금액(%s)? ", s.getPrice()));
    s.setWho((Member) prompt.getAttribute("loginUser"));

    try {
      if (spendDao.update(s) == 0) {
        prompt.println("게시글 변경 권한이 없습니다.");
      } else {
        prompt.println("변경했습니다!");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}
