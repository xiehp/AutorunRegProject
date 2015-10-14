package xie.web.base.db.dao.base;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import xie.web.base.db.entity.base.IXBaseEntity;

public abstract class XBaseDao<VO extends IXBaseEntity> extends XAbstractCommonDAO<VO>implements IXBaseDao<VO> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
		// return HibernateUtil.getSessionFactory();
	}

}
