package think.myapp.handler;


import think.myapp.dao.MemberDao;
import think.myapp.vo.Member;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class MemberDetailListener implements ActionListener {

  MemberDao memberDao;

  public MemberDetailListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    System.out.printf("번호: %s\n", m.getNo());
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("아이디: %s\n", m.getId());
  }
}


