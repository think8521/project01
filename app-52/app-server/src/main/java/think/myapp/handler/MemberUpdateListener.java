package think.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import think.myapp.dao.MemberDao;
import think.myapp.vo.Member;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class MemberUpdateListener implements ActionListener {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberUpdateListener(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setId(prompt.inputString("아이디(%s)? ", m.getId()));
    m.setPwd(prompt.inputString("새암호? "));

    try {
      memberDao.update(m);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}


