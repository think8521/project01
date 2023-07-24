package think.myapp.dao;

import java.util.List;
import think.myapp.vo.Spend;


public interface SpendDao {
  void insert(Spend spend);

  List<Spend> list();

  Spend findBy(int no);

  int update(Spend spend);

  int delete(int no);
}
