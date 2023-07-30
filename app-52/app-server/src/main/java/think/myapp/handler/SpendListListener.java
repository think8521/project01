package think.myapp.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendListListener implements ActionListener {

  char daily;
  SpendDao spendDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public SpendListListener(char daily, SpendDao spendDao) {
    this.daily = daily;
    this.spendDao = spendDao;

  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("-----------------------------------");
    prompt.println("번호, 지출자, 지출명, 금액, 등록일");
    prompt.println("-----------------------------------");

    int total = 0;
    List<Spend> list = spendDao.findAll(daily);
    for (Spend s : list) {
      prompt.printf("%d, %s, %s, %d원, %s\n", s.getNo(), s.getWho().getName(), s.getSpend(),
          s.getPrice(), dateFormatter.format(s.getCreatedDate()));
      total += s.getPrice();
    }
    prompt.printf("총액 : %d원\n", total);
  }

}


