package think.myapp.dao;

import java.util.List;
import think.myapp.vo.Spend;


public interface SpendDao {
  void insert(Spend spend);

  List<Spend> findAll(char daily);

  Spend findBy(char daily, int no);

  int update(Spend spend);

  int updateCount(Spend spend);

  int delete(Spend spend);
}
