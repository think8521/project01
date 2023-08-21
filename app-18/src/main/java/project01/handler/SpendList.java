package project01.handler;

import project01.vo.Spend;

public class SpendList {
  private static final int DEFAULT_SIZE = 3;

  private Spend[] Spends = new Spend[DEFAULT_SIZE];
  private int length;

  public void add(Spend s) {
    if (this.length == Spends.length) {
      increase();
    }

    this.Spends[this.length++] = s;
    return true;
  }

  private void increase() {
    // 기존 배열 보다 50% 큰 배열을 새로 만든다.
    Spend[] arr = new Spend[Spends.length + (Spends.length >> 1)];

    // 기존 배열의 값을 새 배열로 복사한다.
    for (int i = 0; i < Spends.length; i++) {
      arr[i] = Spends[i];
    }

    // Spends 레퍼런스가 새 배열을 가리키도록 한다.
    Spends = arr;

    System.out.println("배열 확장: " + Spends.length);
  }

  public Spend[] list() {
    Spend[] arr = new Spend[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = Spends[i];
    }
    return arr;
  }

  public Spend get(int no) {
    for (int i = 0; i < this.length; i++) {
      Spend s = this.Spends[i];
      if (s.getNo() == no) {
        return s;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = this.indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.Spends[i] = this.Spends[i + 1];
    }
    this.Spends[--this.length] = null;
    return true;
  }

  private int indexOf(int SpendNo) {
    for (int i = 0; i < this.length; i++) {
      Spend Spend = this.Spends[i];
      if (Spend.getNo() == SpendNo) {
        return i;
      }
    }
    return -1;
  }
}







