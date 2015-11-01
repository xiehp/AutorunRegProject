package xie.web.base.db.service;

import java.util.List;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;

public interface IRegisterInfoService {

	//XRegisterInfoEntity register(String serialNumber, String pcInfo);

	/**
	 * 将数据添加到注册信息表中，如果注册码表信息中数据不存在，则增加
	 * 
	 * @param serialNumber 原始注册码
	 * @param encodeSerialNumber 编码后注册码
	 * @param pcInfo 机器信息
	 * @return
	 */
	XRegisterInfoEntity register(String serialNumber, String encodeSerialNumber, String pcInfo);

	XRegisterInfoEntity findById(String Id);

	List<XRegisterInfoEntity> findBySerialNumberIdAndPcInfo(String serialNumber, String pcInfo);

	int countBySerialNumberIdAndPcInfo(String serialNumberId, String pcInfo);
}
