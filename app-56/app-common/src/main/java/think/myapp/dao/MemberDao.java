package think.myapp.dao;


import java.util.List;
import think.myapp.vo.Member;

public interface MemberDao {
  void insert(Member member);

  List<Member> findAll();

  Member findBy(int no);

  Member findByIdAndPwd(Member member);

  int update(Member member);

  int delete(int no);
}
