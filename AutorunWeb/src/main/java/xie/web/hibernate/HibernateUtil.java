package xie.web.hibernate;

import java.net.URL;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;

public class HibernateUtil {
	public static SessionFactory sessionFactory;

	public HibernateUtil() {

	}

	synchronized public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
//				Class.forName("com.mysql.jdbc.Driver");

				/** 此方法在Hibernate4中被标记为过时 */
				// sessionFactory = new Configuration().configure().buildSessionFactory();

				/** Hibernate4取得SessionFactory的方法 */
				URL url = HibernateUtil.class.getResource("/config/hibernate.cfg.xml");
				System.out.println(url.toString());
				// Configuration cfg = new Configuration().configure(new File("src/hibernate.cfg.xml"));
				Configuration cfg = new Configuration().configure(url);
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
				System.out.println(111111111);
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
				System.out.println(222);

			} catch (Throwable e) {
				throw new ExceptionInInitializerError(e);
			}
		}

		return sessionFactory;
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println(sessionFactory);
		Session aaa = sessionFactory.openSession();
		Session bbb = sessionFactory.openSession();

		System.out.println(aaa == bbb);
		System.out.println(aaa);
		System.out.println(bbb);

		String hql = "from Register_Info";
		Query query = aaa.createQuery(hql);
		query.setCacheable(true); // 设置缓存
		List<XRegisterInfoEntity> list = query.list();
		show(list);
		aaa.close();
	}

	// 遍历集合
	private static void show(List<XRegisterInfoEntity> list) {
		for (XRegisterInfoEntity emp : list) {
			System.out.println(emp);
		}
	}
}
