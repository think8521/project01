package think.myapp.handler;

import think.myapp.dao.MemberDao;
import think.myapp.vo.Member;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class MemberAddListener implements ActionListener {

  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setId(prompt.inputString("아이디? "));
    m.setPwd(prompt.inputString("암호? "));

    memberDao.insert(m);
  }
}


