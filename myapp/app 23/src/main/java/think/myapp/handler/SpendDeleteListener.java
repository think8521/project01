package think.myapp.handler;

import think.myapp.vo.Spend;
import think.util.BreadcrumbPrompt;
import think.util.List;

public class SpendDeleteListener extends AbstractSpendListener {

  public SpendDeleteListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Spend(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

}
