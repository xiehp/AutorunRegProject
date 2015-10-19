package xie.web.base.db.entity.impl;

import javax.persistence.*;

import xie.web.base.db.entity.base.XBaseCommonEntity;

import java.util.Date;

@Entity(name = "Register_Info")
@Table
//@Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
//public class XRegisterInfoEntity extends XBaseCommonEntity {
public class XRegisterInfoEntity extends XBaseCommonEntity  {

	//private static final long serialVersionUID = -1993148360252911166L;

	@Id
	//@Column(name = "Register_Info_Id")
	private Integer registerInfoId;

	//@Column(name = "Serial_Number")
	private String serialNumber;

	//@Column(name = "PC_Info")
	private String pcInfo;

	public Integer getRegisterInfoId() {
		return registerInfoId;
	}

	public void setRegisterInfoId(final Integer registerInfoId) {
		this.registerInfoId = registerInfoId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPcInfo() {
		return pcInfo;
	}

	public void setPcInfo(final String pcInfo) {
		this.pcInfo = pcInfo;
	}
}
