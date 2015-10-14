package xie.web.base.utils;

import org.hibernate.Session;

/**
 * 
 * DAO session 对象类
 * 
 * @version 1.0
 */
public class DAOSessionBean {
	private Session session;
	private int refTimes = 0;

	public Session getSession() {
		return session;
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	public int getRefTimes() {
		return refTimes;
	}

	public void setRefTimes(final int refTimes) {
		this.refTimes = refTimes;
	}

	public void addRefTimes() {
		refTimes = refTimes + 1;
	}

	public void delRefTimes() {
		refTimes = refTimes - 1;
	}
}
