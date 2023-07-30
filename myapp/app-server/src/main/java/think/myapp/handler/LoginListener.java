package think.myapp.handler;

import java.io.IOException;
import think.myapp.dao.MemberDao;
import think.myapp.vo.Member;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class LoginListener implements ActionListener {

  MemberDao memberDao;

  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    while (true) {
      Member m = new Member();
      m.setId(prompt.inputString("아이디? "));
      m.setPwd(prompt.inputString("암호? "));

      Member loginUser = memberDao.findByIdAndPwd(m);
      if (loginUser == null) {
        prompt.println("회원 정보가 일치하지 않습니다.");
      } else {
        prompt.setAttribute("loginUser", loginUser);
        break;
      }
    }
  }

}
