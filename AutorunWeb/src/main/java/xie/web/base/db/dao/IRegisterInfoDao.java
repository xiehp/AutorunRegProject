package xie.web.base.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import xie.web.base.db.dao.base.IXBaseDao;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;

@Repository
public interface IRegisterInfoDao extends IXBaseDao<XRegisterInfoEntity, String> {

	int countBySerialNumberId(String serialNumberId);

	int countBySerialNumberIdAndPcInfo(String serialNumberId, String pcInfo);

	List<XRegisterInfoEntity> findBySerialNumberIdAndPcInfo(String serialNumberId, String pcInfo);
}