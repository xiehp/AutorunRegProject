package xie.web.base.db.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import xie.web.base.db.entity.base.XBaseCommonEntity;

@Entity
@Table(name = "Serial_Number_Info")
//@Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XSerialNumberInfoEntity extends XBaseCommonEntity {

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String serialNumber;

	private Integer maxRegistCount;

	private Integer nowRegistCount;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
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
}
