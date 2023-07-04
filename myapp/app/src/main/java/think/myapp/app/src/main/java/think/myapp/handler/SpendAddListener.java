package think.myapp.handler;

import java.util.List;
import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;

public class SpendAddListener extends AbstractSpendListener {

  public SpendAddListener(List<Spend> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Spend s = new Spend();
    s.setNo(Spend.userId++);
    s.setSpend(prompt.inputString("지출명? "));
    s.setPrice(prompt.inputString("금액? "));
    s.setDaily(inputDaily((char) 0, prompt));

    this.list.add(s);
  }
}
