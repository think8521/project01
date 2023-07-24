package think.myapp.handler;

import think.myapp.dao.SpendDao;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendDeleteListener implements ActionListener {

  SpendDao spendDao;

  public SpendDeleteListener(SpendDao spendDao) {
    this.spendDao = spendDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (spendDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

}
