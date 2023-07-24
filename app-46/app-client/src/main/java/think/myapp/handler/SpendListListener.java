package think.myapp.handler;

import java.util.List;
import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendListListener implements ActionListener {

  SpendDao spendDao;

  public SpendListListener(SpendDao spendDao) {
    this.spendDao = spendDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    int total = 0;
    List<Spend> list = spendDao.list();
    for (Spend s : list) {
      System.out.printf("%d, %s, %s원, %s\n", s.getNo(), s.getSpend(), s.getPrice(),
          s.getDaily() == 'Y' ? "Yes" : "No");
      total += Integer.parseInt(s.getPrice());
    }
    System.out.printf("총액 : %d원\n", total);
  }

}


