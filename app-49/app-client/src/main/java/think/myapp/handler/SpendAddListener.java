package think.myapp.handler;

import think.myapp.ClientApp;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;

public class SpendAddListener implements SpendActionListener {

  SpendDao spendDao;

  public SpendAddListener(SpendDao spendDao) {
    this.spendDao = spendDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Spend s = new Spend();
    s.setSpend(prompt.inputString("지출명? "));
    s.setPrice(prompt.inputInt("금액? "));
    s.setDaily(SpendActionListener.inputDaily((char) 0, prompt));
    s.setWho(ClientApp.loginUser);


    spendDao.insert(s);
  }
}
