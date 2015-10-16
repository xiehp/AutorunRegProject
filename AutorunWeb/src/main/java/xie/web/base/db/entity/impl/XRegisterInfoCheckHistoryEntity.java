package xie.web.base.db.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import xie.web.base.db.entity.base.XBaseCommonEntity;

@Entity(name = "register_info_check_history")
//@Table
// @Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XRegisterInfoCheckHistoryEntity extends XBaseCommonEntity {

	private static final long serialVersionUID = -1993148360252911166L;

	@Id
	@Column(name = "Register_Info_Check_History_Id")
	private Integer registerInfoCheckHistoryId;

	@Column(name = "Serial_Number")
	private String serialNumber;

	@Column(name = "PC_Info")
	private String pcInfo;

	@Column(name = "Exist_Flg")
	private String existFlg;

	public Integer getRegisterInfoCheckHistoryId() {
		return registerInfoCheckHistoryId;
	}

	public void setRegisterInfoCheckHistoryId(final Integer registerInfoCheckHistoryId) {
		this.registerInfoCheckHistoryId = registerInfoCheckHistoryId;
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

	public String getExistFlg() {
		return existFlg;
	}

	public void setExistFlg(String existFlg) {
		this.existFlg = existFlg;
	}
}
