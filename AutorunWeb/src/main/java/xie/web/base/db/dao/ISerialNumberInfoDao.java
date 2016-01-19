package xie.web.base.db.dao;

import org.springframework.stereotype.Repository;
import xie.web.base.db.dao.base.IXBaseDao;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;

@Repository
public interface ISerialNumberInfoDao extends IXBaseDao<XSerialNumberInfoEntity, String> {

	XSerialNumberInfoEntity findById(String serialNumberId);

	XSerialNumberInfoEntity findBySerialNumber(String serialNumber);
}