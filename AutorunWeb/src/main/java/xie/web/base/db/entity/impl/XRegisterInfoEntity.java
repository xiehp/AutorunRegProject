package xie.web.base.db.entity.impl;

import javax.persistence.*;

import xie.web.base.db.entity.base.XBaseCommonEntity;

import java.util.Date;

@Entity
@Table(name = "Register_Info")
// @Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XRegisterInfoEntity extends XBaseCommonEntity {

	private static final long serialVersionUID = -6428151895201230707L;

	/** 注册码ID */
	@Column(name = "Serial_Number_Id", nullable = false)
	private String serialNumberId;

	/** 原始注册码 */
	@Column(name = "Serial_Number", nullable = false)
	private String serialNumber;

	/** 机器信息 */
	@Column(name = "Pc_Info")
	private String pcInfo;

	/** 注册时间 */
	@Column(name = "Regist_Date", nullable = false)
	private Date registDate;

	/** 同一个电脑的注册次数 */
	@Column(name = "Regist_Count", nullable = false)
	private int registCount;

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

	public int getRegistCount() {
		return registCount;
	}

	public void setRegistCount(int registCount) {
		this.registCount = registCount;
	}
}
