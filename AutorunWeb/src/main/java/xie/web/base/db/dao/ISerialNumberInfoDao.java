package xie.web.base.db.dao;

import org.springframework.data.repository.CrudRepository;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;

//public interface IRegisterInfoDao<VO extends XRegisterInfoEntity> extends IXBaseDao<VO> {
//
//	public VO findBySerialNumber(String serialNumber);
//}

//@NoRepositoryBean
public interface ISerialNumberInfoDao extends CrudRepository<XSerialNumberInfoEntity, Long> {

	XSerialNumberInfoEntity findBySerialNumber();
}