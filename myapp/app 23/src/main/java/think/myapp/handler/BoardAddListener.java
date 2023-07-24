package think.myapp.handler;

import think.myapp.vo.Board;
import think.util.BreadcrumbPrompt;
import think.util.List;

public class BoardAddListener extends AbstractBoardListener {

  public BoardAddListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter(prompt.inputString("작성자? "));
    board.setPassword(prompt.inputString("암호? "));
    this.list.add(board);
  }
}


