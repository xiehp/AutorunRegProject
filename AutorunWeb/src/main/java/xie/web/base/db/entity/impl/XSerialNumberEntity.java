package xie.web.base.db.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import xie.web.base.db.entity.base.XBaseCommonEntity;
import xie.web.base.db.entity.base.XBaseEntity;

@Entity(name = "Serial_Number_INFO")
@Table
//@Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XSerialNumberEntity extends XBaseCommonEntity {

	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "Serial_Number_Id")
	private Integer serialNumberId;

	@Column(name = "Serial_Number")
	private String serialNumber;

	public Integer getSerialNumberId() {
		return serialNumberId;
	}

	public void setSerialNumber(final Integer serialNumberId) {
		this.serialNumberId = serialNumberId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
