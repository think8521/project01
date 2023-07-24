package think.myapp.handler;

import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;
import think.util.List;

public class SpendAddListener extends AbstractSpendListener {

  public SpendAddListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Spend s = new Spend();
    s.setSpend(prompt.inputString("지출명? "));
    s.setPrice(prompt.inputString("금액? "));
    s.setDaily(inputDaily((char) 0, prompt));

    this.list.add(s);
  }
}
