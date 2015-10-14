package xie.web.base.db.entity.base;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Version;

public abstract class XBaseCommonEntity extends XBaseEntity {

	private static final long serialVersionUID = 1L;

	/** 用户分组标识 */
	@Column(name = "SITE_ID")
	private String siteId;

	@Column(name = "CREAT_USER")
	private String creatUser;

	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	@Column(name = "UPDATE_DATE")
	private Timestamp updateDate;

	/** 排他版本 */
	@Version
	@Column(name = "COLUMN_VERSION")
	private Integer column_Version;

	/** 删除标志 */
	@Column(name = "DELETE_FLAG")
	private Integer deleteFlag;

	public String getCreatUser() {
		return creatUser;
	}

	public void setCreator(final String creatUser) {
		this.creatUser = creatUser;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdater(final String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(final Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSite() {
		return siteId;
	}

	public void setSite(final String siteId) {
		this.siteId = siteId;
	}
}
