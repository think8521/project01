package think.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import think.myapp.vo.Spend;

public class MySQLSpendDao implements SpendDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLSpendDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Spend spend) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("think.myapp.dao.SpendDao.insert", spend);
  }

  @Override
  public List<Spend> findAll(char daily) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("think.myapp.dao.SpendDao.findAll", daily);
  }

  @Override
  public Spend findBy(char daily, int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("dailyChar", daily);
    paramMap.put("spendNo", no);

    return sqlSession.selectOne("think.myapp.dao.SpendDao.findBy", paramMap);
  }

  @Override
  public int update(Spend spend) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("think.myapp.dao.SpendDao.update", spend);
  }

  @Override
  public int updateCount(Spend spend) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("think.myapp.dao.SpendDao.updateCount", spend);
  }

  @Override
  public int delete(Spend spend) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("think.myapp.dao.SpendDao.delete", spend);
  }
}
