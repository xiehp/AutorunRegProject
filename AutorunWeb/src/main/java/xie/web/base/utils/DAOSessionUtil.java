package xie.web.base.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 
 * DAO会话工具类
 *
 * <pre>
 * Pattern : Value Object
 * Thread Safe : No
 * 
 * Change History
 * 
 * Name                 Date                    Description
 * -------              -------                 -----------------
 * 020191              2014-3-31            Create the class
 *
 * </pre>
 *
 * @author 020191
 * @version 1.0
 */
public class DAOSessionUtil {
	private final static ThreadLocal<DAOSessionBean> DAO_SESSION_BEAN = new ThreadLocal<DAOSessionBean>();

	/**
	 * 
	 * 如果当前线程中不存在session,则新生成一个session并返回它 否则返回已存在的session
	 * 
	 * @param sessionFactory 会话工厂
	 * @return Session对象
	 */
	public static Session openSession(final SessionFactory sessionFactory) {

		final Session session;
		final SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		if (sessionHolder != null && sessionHolder.getSession() != null) {
			session = sessionHolder.getSession();
		} else {
			DAOSessionBean sessionBean = DAO_SESSION_BEAN.get();
			if (sessionBean == null) {
				sessionBean = new DAOSessionBean();
				DAO_SESSION_BEAN.set(sessionBean);
				sessionBean.setSession(sessionFactory.openSession());
			}

			sessionBean.addRefTimes();
			session = sessionBean.getSession();
		}

		return session;
	}

	/**
	 * 
	 * 如果是新生成的session，则关闭它
	 */
	public static void closeSession() {
		final DAOSessionBean sessionBean = DAO_SESSION_BEAN.get();
		if (sessionBean != null) {
			sessionBean.delRefTimes();
			if (sessionBean.getRefTimes() == 0) {
				try {
					if (sessionBean.getSession().isOpen()) {
						sessionBean.getSession().close();
					}
				} finally {
					DAO_SESSION_BEAN.remove();
				}
			}
		}
	}
}