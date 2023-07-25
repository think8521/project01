package think.myapp.handler;

import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendDeleteListener implements ActionListener {

  SpendDao spendDao;

  public SpendDeleteListener(SpendDao spendDao) {
    this.spendDao = spendDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {

    Spend s = new Spend();
    s.setNo(prompt.inputInt("번호? "));

    if (spendDao.delete(s) == 0) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
    }
  }

}
