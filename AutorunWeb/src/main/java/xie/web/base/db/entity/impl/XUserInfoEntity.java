package xie.web.base.db.entity.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USER_INFO")
//@Cache(region = "P2BCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class XUserInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@Column(name = "UPDATER")
	private String updater;

	@Column(name = "UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name = "DELETE_FLAG")
	private Integer deleteFlag;

	@Column(name = "LAST_LOGIN_TIME")
	private Timestamp lastLoginTime;

	@Column(name = "SITE")
	private String site;

	/*
	 * @Column(name = "ORG_ID") private String orgId;
	 * 
	 * @Column(name = "USER_TYPE") private String userType;
	 */
	@Column(name = "ACTIVATE_FLAG")
	private Integer activateFlag;

	@Id
	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "EMAIL")
	private String email;

	@Transient
	private String hideMobile;

	public String getCreator() {
		return creator;
	}

	public void setCreator(final String creator) {
		this.creator = creator;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(final String updater) {
		this.updater = updater;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(final Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(final Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSite() {
		return site;
	}

	public void setSite(final String site) {
		this.site = site;
	}

	/*
	 * public String getOrgId() { return orgId; }
	 * 
	 * public void setOrgId(String orgId) { this.orgId = orgId; }
	 * 
	 * public String getUserType() { return userType; }
	 * 
	 * public void setUserType(String userType) { this.userType = userType; }
	 */
	public Integer getActivateFlag() {
		return activateFlag;
	}

	public void setActivateFlag(final Integer activateFlag) {
		this.activateFlag = activateFlag;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public String getHideMobile() {
		return hideMobile;
	}

	public void setHideMobile(final String hideMobile) {
		this.hideMobile = hideMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

}
