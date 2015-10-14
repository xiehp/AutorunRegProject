package xie.web.base.db.dao.impl;

import org.springframework.data.repository.CrudRepository;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.study.Customer;

//@Repository
//public class XRegisterInfoDao extends XBaseDao<XRegisterInfoEntity> implements IRegisterInfoDao<XRegisterInfoEntity> {

//private static final long serialVersionUID = 1L;

//	public XRegisterInfoEntity findBySerialNumber(String serialNumber) {
//		// getSession().createQuery(" from Register_Info where serialNumber = :serialNumber")
//
//		final StringBuffer hql = new StringBuffer(400);
//		final Map<String, Object> paramMap = new HashMap<String, Object>();
//
//		hql.append("from Register_Info where serialNumber = :serialNumber ");
//
//		paramMap.put("serialNumber", serialNumber);
//
//		return (XRegisterInfoEntity) super.get(hql.toString(), paramMap);
//	}

public interface XRegisterInfoDao {

	//public XRegisterInfoEntity findBySerialNumber(String serialNumber);

}
