package xie.web.base.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xie.web.base.db.dao.ISerialNumberInfoDao;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;
import xie.web.base.db.service.ISerialNumberInfoService;
import xie.web.base.db.service.XBaseService;

@Service
@Transactional
public class XSerialNumberInfoService extends XBaseService implements ISerialNumberInfoService {

	@Autowired
	private ISerialNumberInfoDao serialNumberInfoDao;

	@Override
	public XSerialNumberInfoEntity findById(String serialNumberId) {
		return serialNumberInfoDao.findById(serialNumberId);
	}

	@Override
	public XSerialNumberInfoEntity findBySerialNumber(String serialNumber) {
		return serialNumberInfoDao.findBySerialNumber(serialNumber);
	}
}
