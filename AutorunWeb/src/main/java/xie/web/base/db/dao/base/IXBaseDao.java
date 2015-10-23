package xie.web.base.db.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IXBaseDao <T, ID extends Serializable> extends JpaRepository<T, ID> {
}
