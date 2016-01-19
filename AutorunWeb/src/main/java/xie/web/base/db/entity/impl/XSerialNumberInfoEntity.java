package xie.web.base.db.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import xie.web.base.db.entity.base.XBaseCommonEntity;

import java.util.Date;

@Entity
@Table(name = "Serial_Number_Info")
// @Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XSerialNumberInfoEntity extends XBaseCommonEntity {

	private static final long serialVersionUID = 1L;

	/** 原始注册码 */
	@Column(name = "Serial_Number", nullable = false, unique = true)
	private String serialNumber;

	/** 编码后的注册码 */
	@Column(name = "Encode_Serial_Number")
	private String encodeSerialNumber;

	/** 最大注册次数 */
	@Column(name = "Max_Regist_Count", nullable = false)
	private Integer maxRegistCount;

	/** 当前已注册次数 */
	@Column(name = "Now_Regist_Count", nullable = false)
	private Integer nowRegistCount;

	/** 首次注册时间 */
	@Column(name = "First_Regist_Date", nullable = false)
	private Date firstRegistDate;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEncodeSerialNumber() {
		return encodeSerialNumber;
	}

	public void setEncodeSerialNumber(String encodeSerialNumber) {
		this.encodeSerialNumber = encodeSerialNumber;
	}

	public Integer getMaxRegistCount() {
		return maxRegistCount;
	}

	public void setMaxRegistCount(Integer maxRegistCount) {
		this.maxRegistCount = maxRegistCount;
	}

	public Integer getNowRegistCount() {
		return nowRegistCount;
	}

	public void setNowRegistCount(Integer nowRegistCount) {
		this.nowRegistCount = nowRegistCount;
	}

	public Date getFirstRegistDate() {
		return firstRegistDate;
	}

	public void setFirstRegistDate(Date firstRegistDate) {
		this.firstRegistDate = firstRegistDate;
	}
}
