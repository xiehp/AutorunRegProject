package xie.web.base.db.dao;

import org.springframework.stereotype.Repository;
import xie.web.base.db.dao.base.IXBaseDao;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;

@Repository
public interface IRegisterInfoDao extends IXBaseDao<XRegisterInfoEntity, String> {

	int countBySerialNumber(String serialNumber);

	int countBySerialNumberId(String serialNumberId);

	XRegisterInfoEntity findBySerialNumberAndPcInfo(String serialNumber, String pcInfo);
}