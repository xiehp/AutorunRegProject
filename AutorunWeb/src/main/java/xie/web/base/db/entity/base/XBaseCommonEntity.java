package xie.web.base.db.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
//@EntityListeners(value = EntityUserAndDateListener.class)
public abstract class XBaseCommonEntity extends XBaseEntity {

	private static final long serialVersionUID = 1L;

	@PrePersist
	public void prePersist() {
		Date date = new Date();
		String user = "system";
		setCreatUser(user);
		setCreateDate(date);
		setUpdateUser(user);
		setUpdateDate(date);
	}

	@PreUpdate
	public void preUpdate() {
		Date date = new Date();
		String user = "system";
		setUpdateUser(user);
		setUpdateDate(date);
	}

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name="hibernate-uuid", strategy="uuid")
	@Column(name = "ID")
	private String id;

	/**
	 * 用户分组标识
	 */
	@Column(name = "SITE_ID")
	private String siteId;

	@Column(name = "CREAT_USER")
	private String creatUser;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	/**
	 * 排他版本
	 */
	@Version
	@Column(name = "COLUMN_VERSION")
	private Integer columnVersion;

	/**
	 * 删除标志
	 */
	@Column(name = "DELETE_FLAG")
	private Integer deleteFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(final String siteId) {
		this.siteId = siteId;
	}

	public String getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getColumnVersion() {
		return columnVersion;
	}

	public void setColumnVersion(Integer columnVersion) {
		this.columnVersion = columnVersion;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(final Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
