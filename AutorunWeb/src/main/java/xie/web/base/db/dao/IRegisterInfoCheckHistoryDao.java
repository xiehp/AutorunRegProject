package xie.web.base.db.dao;

import java.util.List;

import xie.web.base.db.dao.base.IXBaseDao_back;
import xie.web.base.db.entity.impl.XRegisterInfoCheckHistoryEntity;

public interface IRegisterInfoCheckHistoryDao<VO extends XRegisterInfoCheckHistoryEntity> extends IXBaseDao_back<VO> {

	public List<VO> findListBySerialNumber(String serialNumber);

	/**
	 * 
	 * @param serialNumber
	 * @param pcInfo
	 * @param existFlg 0, 1
	 * @return
	 */
	public VO saveHistory(String serialNumber, String pcInfo, String existFlg);
}
