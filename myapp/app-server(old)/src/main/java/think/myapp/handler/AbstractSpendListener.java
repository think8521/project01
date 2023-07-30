package think.myapp.handler;

import java.util.List;
import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public abstract class AbstractSpendListener implements ActionListener {

  protected List<Spend> list;

  public AbstractSpendListener(List<Spend> list) {
    this.list = list;
  }

  protected static String toDailyString(char daily) {
    return daily == 'Y' ? "Yes" : "No";
  }

  protected Spend findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Spend s = this.list.get(i);
      if (s.getNo() == no) {
        return s;
      }
    }
    return null;
  }

  protected char inputDaily(char dailyNecessity, BreadcrumbPrompt prompt) {
    String label;
    if (dailyNecessity == 0) {
      label = "생필품?\n";
    } else {
      label = String.format("생필품(%s)?\n", toDailyString(dailyNecessity));
    }

    while (true) {
      String menuNo = prompt.inputString(label + "  1. Yes\n" + "  2. No\n" + "> ");

      switch (menuNo) {
        case "1":
          return Spend.Yes;
        case "2":
          return Spend.No;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }
}
