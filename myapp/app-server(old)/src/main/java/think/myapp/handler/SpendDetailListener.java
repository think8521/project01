package think.myapp.handler;

import java.util.List;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;

public class SpendDetailListener extends AbstractSpendListener {

  public SpendDetailListener(List<Spend> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int spendNo = prompt.inputInt("번호? ");

    Spend s = this.findBy(spendNo);
    if (s == null) {
      System.out.println("해당 번호의 지출내역이 없습니다!");
      return;
    }

    System.out.printf("지출명: %s\n", s.getSpend());
    System.out.printf("금액: %s\n", s.getPrice());
    System.out.printf("생필품 여부: %s\n", toDailyString(s.getDaily()));
  }
}
