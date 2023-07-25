package think.myapp.handler;

import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendUpdateListener implements ActionListener {

  SpendDao spendDao;

  public SpendUpdateListener(SpendDao spendDao) {
    this.spendDao = spendDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int spendNo = prompt.inputInt("번호? ");

    Spend s = spendDao.findBy(spendNo);
    if (s == null) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    s.setSpend(prompt.inputString("지출내역(%s)? ", s.getSpend()));
    s.setPrice(prompt.inputInt("금액(%s)? ", s.getPrice()));
    s.setDaily(SpendActionListener.inputDaily(s.getDaily(), prompt));

    spendDao.update(s);
  }

}
