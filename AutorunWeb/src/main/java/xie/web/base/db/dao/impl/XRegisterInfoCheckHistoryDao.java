package xie.web.base.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import xie.web.base.db.dao.IRegisterInfoCheckHistoryDao;
import xie.web.base.db.dao.base.XBaseDao;
import xie.web.base.db.entity.impl.XRegisterInfoCheckHistoryEntity;

//@Repository
public class XRegisterInfoCheckHistoryDao extends XBaseDao<XRegisterInfoCheckHistoryEntity> implements IRegisterInfoCheckHistoryDao<XRegisterInfoCheckHistoryEntity> {

	private static final long serialVersionUID = 1L;

	public List<XRegisterInfoCheckHistoryEntity> findListBySerialNumber(String serialNumber) {
		// getSession().createQuery(" from Register_Info where serialNumber = :serialNumber")

		final StringBuffer hql = new StringBuffer(400);
		hql.append("where serialNumber = :serialNumber ");

		// final Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("serialNumber", serialNumber);
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(serialNumber);

		return super.query(hql.toString(), paramList);
	}

	@Override
	public XRegisterInfoCheckHistoryEntity saveHistory(String serialNumber, String pcInfo, String existFlg) {
		XRegisterInfoCheckHistoryEntity entity = new XRegisterInfoCheckHistoryEntity();
		entity.setSerialNumber(serialNumber);
		entity.setPcInfo(pcInfo);
		entity.setExistFlg(existFlg);

		return save(entity);
	}
}
