package think.myapp.handler;

import think.myapp.dao.SpendDao;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public class SpendDetailListener implements ActionListener {

  SpendDao spendDao;

  public SpendDetailListener(SpendDao spendDao) {
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

    System.out.printf("번호: %s\n", s.getNo());
    System.out.printf("작성자: %s\n", s.getWho().getName());
    System.out.printf("지출명: %s\n", s.getSpend());
    System.out.printf("금액: %s\n", s.getPrice());
    System.out.printf("생필품 여부: %s\n", s.getDaily() == 'Y' ? "Yes" : "No");
  }
}
