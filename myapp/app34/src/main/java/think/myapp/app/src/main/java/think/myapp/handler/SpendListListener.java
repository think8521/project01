package think.myapp.handler;

import java.util.List;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;

public class SpendListListener extends AbstractSpendListener {

  public SpendListListener(List<Spend> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("--------------------------------");
    System.out.println("번호, 지출명, 금액, 생필품");
    System.out.println("--------------------------------");

    int total = 0;
    for (int i = 0; i < this.list.size(); i++) {
      Spend s = this.list.get(i);
      try {
        total += Integer.parseInt(s.getPrice());
      } catch (Exception e) {
        System.out.println("↓ 에러! 아직 숫자가 아니면 더할 수 없음 🙏");
      }
      System.out.printf("%d, %s, %s원, %s\n", s.getNo(), s.getSpend(), s.getPrice(),
          toDailyString(s.getDaily()));
    }
    System.out.printf("총액 : %d원\n", total);
  }

}


