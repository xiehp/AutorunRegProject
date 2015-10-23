package xie.web.base.db.service;

import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;

public interface ISerialNumberInfoService {

	XSerialNumberInfoEntity findBySerialNumber(String serialNumber);
}
