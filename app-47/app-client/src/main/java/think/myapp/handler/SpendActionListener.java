package think.myapp.handler;

import think.myapp.vo.Spend;
import think.util.ActionListener;
import think.util.BreadcrumbPrompt;

public interface SpendActionListener extends ActionListener {

  static char inputDaily(char daily, BreadcrumbPrompt prompt) {
    String label;
    if (daily == 0) {
      label = "생필품?\n";
    } else {
      label = String.format("생필품(%s)?\n", daily == 'Y' ? "Yes" : "No");
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
