package think.myapp.handler;

import java.util.List;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;

public class SpendUpdateListener extends AbstractSpendListener {

  public SpendUpdateListener(List<Spend> list) {
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

    s.setSpend(prompt.inputString("지출내역(%s)? ", s.getSpend()));
    s.setPrice(prompt.inputString("금액(%s)? ", s.getPrice()));
    System.out.printf("생필품 여부: %s\n", toDailyString(s.getDaily()));
  }

}
