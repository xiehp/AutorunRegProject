package xie.web.base.db.dao;

import org.springframework.stereotype.Repository;
import xie.web.base.db.dao.base.IXBaseDao;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;

@Repository
public interface ISerialNumberInfoDao extends IXBaseDao<XSerialNumberInfoEntity, String> {

	XSerialNumberInfoEntity findBySerialNumber(String serialNumber);
}