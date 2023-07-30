package think.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendDetailListener implements ActionListener {

  char daily;
  SpendDao spendDao;
  SqlSessionFactory sqlSessionFactory;

  public SpendDetailListener(char daily, SpendDao spendDao, SqlSessionFactory sqlSessionFactory) {
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

    prompt.printf("번호: %s\n", s.getNo());
    prompt.printf("작성자: %s\n", s.getWho().getName());
    prompt.printf("아이디: %s\n", s.getWho().getId());
    prompt.printf("지출명: %s\n", s.getSpend());
    prompt.printf("금액: %s\n", s.getPrice());
    prompt.printf("등록일: %tY-%1$tm-%1$td\n", s.getCreatedDate());

    try {
      // s.setViewCount(s.getViewCount() + 1);
      // spendDao.updateCount(s);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
