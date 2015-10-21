package xie.web.base.db.entity.impl;

import javax.persistence.*;

import xie.web.base.db.entity.base.XBaseCommonEntity;

import java.util.Date;

@Entity
@Table(name = "Register_Info")
//@Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XRegisterInfoEntity extends XBaseCommonEntity  {

	//private static final long serialVersionUID = -1993148360252911166L;

	@Column(name = "Serial_Number_Id")
	private String serialNumberId;

	@Column(name = "Serial_Number")
	private String serialNumber;

	@Column(name = "Pc_Info")
	private String pcInfo;

	@Column(name = "Regist_Date", nullable = false)
	private Date registDate;

	public String getSerialNumberId() {
		return serialNumberId;
	}

	public void setSerialNumberId(String serialNumberId) {
		this.serialNumberId = serialNumberId;
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

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
}
