package xie.web.base.db.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;

//public interface IRegisterInfoDao<VO extends XRegisterInfoEntity> extends IXBaseDao<VO> {
//
//	public VO findBySerialNumber(String serialNumber);
//}

//@NoRepositoryBean
public interface IRegisterInfoDao extends CrudRepository<XRegisterInfoEntity, Long> {

	public XRegisterInfoEntity findBySerialNumber(String serialNumber);

}