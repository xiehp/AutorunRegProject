package xie.web.base.db.service;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;

public interface IRegisterInfoService {

	XRegisterInfoEntity register(String serialNumber, String pcInfo);

	XRegisterInfoEntity findBySerialNumber(String serialNumber);
}
