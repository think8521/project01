package think.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Member;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendDeleteListener implements ActionListener {

  char daily;
  SpendDao spendDao;
  SqlSessionFactory sqlSessionFactory;

  public SpendDeleteListener(char daily, SpendDao spendDao, SqlSessionFactory sqlSessionFactory) {
    this.daily = daily;
    this.spendDao = spendDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Spend s = new Spend();
    s.setNo(prompt.inputInt("번호? "));
    s.setWho((Member) prompt.getAttribute("loginUser"));
    s.setDaily(daily);

    try {
      if (spendDao.delete(s) == 0) {
        prompt.println("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        prompt.println("삭제했습니다.");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}
