package think.myapp.handler;

import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;
import think.util.List;

public class SpendListListener extends AbstractSpendListener {

  public SpendListListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Spend s = (Spend) this.list.get(i);
      System.out.printf("%d, %s, %s원, %s\n", s.getNo(), s.getSpend(), s.getPrice(),
          toDailyString(s.getDaily()));
    }
  }

}
