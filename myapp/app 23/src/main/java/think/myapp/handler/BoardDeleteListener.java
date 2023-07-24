package think.myapp.handler;

import think.myapp.vo.Board;
import think.util.BreadcrumbPrompt;
import think.util.List;

public class BoardDeleteListener extends AbstractBoardListener {

  public BoardDeleteListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Board(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }
}


