package xie.web.base.db.dao.base;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.Type;

import xie.web.base.utils.DAOSessionUtil;
import xie.web.base.utils.PageInfoBean;

/**
 * 
 * 基础数据访问抽象类
 *
 * <pre>
 * Pattern : Value Object
 * Thread Safe : No
 * 
 * Change History
 * 
 * Name                 Date                    Description
 * -------              -------                 -----------------
 * 020191              2014-3-28            Create the class
 *
 * </pre>
 *
 * @author 020191
 * @version 1.0
 */
public abstract class XAbstractBaseDAO{

	protected abstract SessionFactory getSessionFactory();

	private static final Logger LOG = Logger.getLogger(XAbstractBaseDAO.class);

	/**
	 * 
	 * 根据id和对象类型获取对象
	 * 
	 * @param clazz 对象类
	 * @param id 对象id
	 * @return 指定id和对象类型的对象
	 */
	@SuppressWarnings("unchecked")
	protected <X> X get(final Class<X> clazz, final Serializable id) {
		try {
			return (X) this.getSession().get(clazz, id);
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 删除对象
	 * 
	 * @param entity 目标对象
	 */
	protected void delete(final Object entity) {
		try {
			this.getSession().delete(entity);
		} finally {
			releaseSession();
		}
	}

	/**
	 * 根据ID删除对象,load提高性能
	 * 
	 * @param clazz 对象类
	 * @param id 对象id
	 */
	protected <X> void delete(final Class<X> clazz, final Serializable id) {
		try {
			delete(this.getSession().load(clazz, id));
		} finally {
			releaseSession();
		}
	}

	/**
	 * 批量删除实体，不推荐
	 * 
	 * @param <X> 对象类型
	 * @param entities 对象组
	 */
	protected <X> void delete(final List<X> entities) {
		try {
			final int cacheSize = getBatchSize();
			final Session session = this.getSession();
			for (int i = 0; i < entities.size(); i++) {
				if (i % cacheSize == 0) {
					session.flush();
					session.clear();
				}

				final X entity = entities.get(i);
				session.delete(entity);
			}
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity 对象
	 * @return <X> X对象
	 */
	@SuppressWarnings("unchecked")
	protected <X> X save(final Object entity) {
		try {
			return (X) this.get(entity.getClass(), this.getSession().save(entity));
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 批量保存对象
	 * 
	 * @param entities 对象组
	 */
	protected <X> void save(final List<X> entities) {
		try {
			final int cacheSize = getBatchSize();
			final Session session = this.getSession();
			for (int i = 0; i < entities.size(); i++) {
				if (i % cacheSize == 0) {
					session.flush();
					session.clear();
				}

				final X entity = entities.get(i);
				session.save(entity);
			}
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 更新对象
	 * 
	 * @param entity 对象
	 */
	protected void update(final Object entity) {
		try {
			this.getSession().update(entity);
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 批量更新对象
	 * 
	 * @param entities 对象组
	 */
	protected <X> void update(final List<X> entities) {
		try {
			final int cacheSize = getBatchSize();
			final Session session = this.getSession();
			for (int i = 0; i < entities.size(); i++) {
				if (i % cacheSize == 0) {
					session.flush();
					session.clear();
				}

				final X entity = entities.get(i);
				session.update(entity);
			}
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 保存或更新对象
	 * 
	 * @param entity 对象
	 */
	protected void saveOrUpdate(final Object entity) {
		try {
			this.getSession().saveOrUpdate(entity);
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 批量保存或更新对象
	 * 
	 * @param entities 对象组
	 */
	protected <X> void saveOrUpdate(final List<X> entities) {
		try {
			final int cacheSize = getBatchSize();
			final Session session = this.getSession();
			for (int i = 0; i < entities.size(); i++) {
				if (i % cacheSize == 0) {
					session.flush();
					session.clear();
				}

				final X entity = entities.get(i);
				session.saveOrUpdate(entity);
			}
		} finally {
			releaseSession();
		}
	}

	/**
	 * hql更新
	 * 
	 * @param hql 更新语句
	 * @return
	 */
	protected int executeUpdate(final String hql) {
		try {
			return this.createQuery(hql).executeUpdate();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 有条件参数的hql更新
	 * 
	 * @param hql 更新语句
	 * @param params 条件参数
	 * @return
	 */
	protected int executeUpdate(final String hql, final List<? extends Object> params) {
		try {
			return this.createQuery(hql, params).executeUpdate();
		} finally {
			releaseSession();
		}
	}

	/**
	 * sql更新
	 * 
	 * @param sql 更新语句
	 * @return
	 */
	protected void executeUpdateBySQL(final String sql) {
		this.executeUpdateBySQL(sql, null);
	}

	/**
	 * 有条件参数的sql更新
	 * 
	 * @param sql 更新语句
	 * @param params 条件参数
	 * @return
	 */
	protected void executeUpdateBySQL(final String sql, final List<? extends Object> params) {
		try {
			this.doWork(new Work() {
				public void execute(final Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(sql);
					try {
						if (params != null && !params.isEmpty()) {
							final int size = params.size();
							for (int i = 0; i < size; i++) {
								ps.setObject(i + 1, params.get(i));
							}
						}
						ps.execute();
					} finally {
						ps.close();
					}
				}

			});
		} finally {
			releaseSession();
		}
	}

	/**
	 * 保存的实体对象
	 * 
	 * @param entity
	 */
	/*
	 * protected void merge(final Object entity) { getSession().merge(entity); }
	 */

	/**
	 * 批量保存实体
	 * 
	 * @param <X>
	 * @param entities
	 */
	/*
	 * protected <X> void merges(final List<X> entities) { final int cacheSize =
	 * getBatchSize(); Session session = this.getSession(); for (int i = 0; i <
	 * entities.size(); i++) { X entity = entities.get(i);
	 * session.merge(entity); if (i % cacheSize == 0) { session.flush();
	 * session.clear(); } } }
	 */

	/**
	 * 没有条件参数的HQL查询单个对象
	 * 
	 * @param hql 查询语句
	 * @return 单个对象
	 */
	protected Object get(final String hql) {
		try {
			return this.createQuery(hql).uniqueResult();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 单一条件参数的HQL查询单个对象
	 * 
	 * @param hql 查询语句
	 * @param param 条件参数
	 * @return 单个对象
	 */
	protected Object get(final String hql, final Object param) {
		try {
			return this.createQuery(hql).setParameter(0, param).uniqueResult();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 支持Map对象类型条件参数的HQL查询单个对象
	 * 
	 * @param hql 查询语句
	 * @param paramMap 条件参数
	 * @return 单个对象
	 */
	protected Object get(final String hql, final Map<String, ? extends Object> paramMap) {
		try {
			return this.createQuery(hql, paramMap).uniqueResult();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 支持多条件参数的HQL查询单个对象
	 * 
	 * @param hql 查询语句
	 * @param params 条件参数集
	 * @return 单个对象
	 */
	protected Object get(final String hql, final List<? extends Object> params) {
		try {
			return this.createQuery(hql, params).uniqueResult();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 没有条件参数的HQL查询
	 * 
	 * @param <X> 对象类型
	 * @param hql 查询语句
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final String hql) {
		try {
			return this.createQuery(hql).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 单一条件参数的HQL查询
	 * 
	 * @param <X> 对象类型
	 * @param hql 查询语句
	 * @param param 条件参数
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final String hql, final Object param) {
		try {
			return this.createQuery(hql).setParameter(0, param).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 支持多条件参数的HQL查询
	 * 
	 * @param <X> 对象类型
	 * @param hql 查询语句
	 * @param param 条件参数 集
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final String hql, final List<? extends Object> params) {
		try {
			return this.createQuery(hql, params).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持Map条件参数的HQL查询
	 * 
	 * @param hql 查询越剧
	 * @param paramMap 条件参数
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final String hql, final Map<String, ? extends Object> paramMap) {
		try {
			return this.createQuery(hql, paramMap).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 没有条件和排序参数的对象化查询
	 * 
	 * @param clazz 对象类
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final Class<X> clazz) {
		try {
			return this.createCriteria(clazz).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 单一条件的对象化查询
	 * 
	 * @param clazz 对象类
	 * @param criterion 单一条件
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final Class<X> clazz, final Criterion criterion) {
		try {
			return this.createCriteria(clazz).add(criterion).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持多条件没有排序的对象化查询
	 * 
	 * @param clazz 对象类
	 * @param criterions 条件集
	 * @return 集合
	 */
	protected <X> List<X> query(final Class<X> clazz, final List<Criterion> criterions) {
		return this.query(clazz, criterions, null);
	}

	/**
	 * 
	 * 单一排序的对象化查询
	 * 
	 * @param clazz 对象类
	 * @param order 排序方式
	 * @return 集合
	 */
	protected <X> List<X> query(final Class<X> clazz, final Order order) {
		final List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		return this.query(clazz, null, orderList);
	}

	/**
	 * 
	 * 支持条件和排序参数的对象化查询
	 * 
	 * @param clazz 对象类
	 * @param criterions 条件集
	 * @param orders 排序方式集
	 * @return 集合
	 */
	@SuppressWarnings("unchecked")
	protected <X> List<X> query(final Class<X> clazz, final List<Criterion> criterions, final List<Order> orders) {
		try {
			return this.createCriteria(clazz, criterions, orders).list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持有条件参数的获取总记录数的HQL查询
	 * 
	 * @param hql 查询语句
	 * @param paramMap 条件参数
	 * @return 总记录数
	 */
	@SuppressWarnings("rawtypes")
	protected int getTotalCount(final String hql, final Map paramMap) {
		try {
			final Query query = this.getSession().createQuery(hql);
			if (paramMap != null) {
				final Iterator it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}

			}
			return ((Long) query.list().get(0)).intValue();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 根据页码和条件参数查询对应的所有记录
	 * 
	 * @param hql 查询语句
	 * @param paramMap 条件参数
	 * @param pageInfoBean 页码信息
	 * @return 集合
	 */
	@SuppressWarnings("rawtypes")
	protected List<?> queryListByPage(final String hql, final Map paramMap, final PageInfoBean pageInfoBean) {
		try {
			final String strhql = " " + hql + " order ";
			final String hqlTemp = strhql.toLowerCase(Locale.getDefault());

			if (pageInfoBean != null) {
				final StringBuffer hqlCount = new StringBuffer("select count(*)");

				if (hqlTemp.indexOf(" group ") > 0) {
					hqlCount.append(strhql.subSequence(hqlTemp.indexOf(" from "), hqlTemp.indexOf(" group ")));
				} else {
					hqlCount.append(strhql.subSequence(hqlTemp.indexOf(" from "), hqlTemp.indexOf(" order ")));
				}

				final int size = getTotalCount(hqlCount.toString(), paramMap);

				pageInfoBean.setTotalRecordCount(size);
				pageInfoBean.doReviseData();
			}
			final Query query = getSession().createQuery(hql);
			if (paramMap != null) {
				final Iterator<?> it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}
			}
			if (pageInfoBean != null) {
				query.setFirstResult(pageInfoBean.getRecordCountInOnePage() * (pageInfoBean.getCurPageNo() - 1));
				query.setMaxResults(pageInfoBean.getRecordCountInOnePage());
			}
			return query.list();
		} finally {
			releaseSession();
		}
	}

	@SuppressWarnings("rawtypes")
	protected List<?> queryListByPage(final String querySql, final String countSql, final Map paramMap, final PageInfoBean pageInfoBean) {
		try {
			if (pageInfoBean != null) {
				final int size = getTotalCount(countSql, paramMap);
				pageInfoBean.setTotalRecordCount(size);
				pageInfoBean.doReviseData();
			}
			final Query query = getSession().createQuery(querySql);
			if (paramMap != null) {
				final Iterator<?> it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}
			}
			if (pageInfoBean != null) {
				query.setFirstResult(pageInfoBean.getRecordCountInOnePage() * (pageInfoBean.getCurPageNo() - 1));
				query.setMaxResults(pageInfoBean.getRecordCountInOnePage());
			}
			return query.list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持有条件参数的获取总记录数的SQL查询
	 * 
	 * @param sql 查询语句
	 * @param paramMap 条件参数
	 * @return 总记录数
	 */
	@SuppressWarnings("rawtypes")
	protected int getTotalSQLCount(final String sql, final Map paramMap) {
		try {
			final SQLQuery query = this.getSession().createSQLQuery(sql);
			if (paramMap != null) {
				final Iterator it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}

			}
			long start = Calendar.getInstance().getTimeInMillis();
			List list = query.list();// .get(0).toString()
			long end = Calendar.getInstance().getTimeInMillis();
			LOG.debug("getTotalSQLCount SQL: " + sql);
			LOG.debug("SQL took time: " + (end - start) + "millsec");
			return Integer.parseInt(list.get(0).toString());
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持有条件参数的SQL查询
	 * 
	 * @param sql 查询语句
	 * @param paramMap 条件参数
	 * @param resultValueTypeMap 参数类型
	 * @param pageInfoBean 页码信息
	 * @return 集合
	 */
	@SuppressWarnings({ "rawtypes" })
	protected List<?> queryListBySQLPage(final String sql, final Map paramMap, final LinkedHashMap<String, Type> resultValueTypeMap,
			final PageInfoBean pageInfoBean) {
		try {
			final String strhql = " " + sql + " order ";
			final String hqlTemp = strhql.toLowerCase(Locale.getDefault());

			if (pageInfoBean != null) {
				final StringBuffer hqlCount = new StringBuffer("select count(*)");

				if (hqlTemp.indexOf(" group ") > 0) {
					hqlCount.append(strhql.subSequence(hqlTemp.indexOf(" from "), hqlTemp.indexOf(" group ")));
				} else {
					hqlCount.append(strhql.subSequence(hqlTemp.indexOf(" from "), hqlTemp.indexOf(" order ")));
				}

				final int size = getTotalSQLCount(hqlCount.toString(), paramMap);
				pageInfoBean.setTotalRecordCount(size);
				pageInfoBean.doReviseData();
			}
			final SQLQuery query = getSession().createSQLQuery(sql);

			if (paramMap != null) {
				final Iterator<?> it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}
			}

			if (resultValueTypeMap != null) {
				final Iterator<String> it = resultValueTypeMap.keySet().iterator();
				while (it.hasNext()) {
					final String key = it.next();
					query.addScalar(key, resultValueTypeMap.get(key));
				}
			}

			if (pageInfoBean != null) {
				query.setFirstResult(pageInfoBean.getRecordCountInOnePage() * (pageInfoBean.getCurPageNo() - 1));
				query.setMaxResults(pageInfoBean.getRecordCountInOnePage());
			}

			return query.list();
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持有条件参数的SQL查询
	 * 
	 * @param sql 查询语句
	 * @param paramMap 条件参数
	 * @param resultValueTypeMap 参数类型
	 * @param entityClass 对象类型
	 * @param pageInfoBean 页码信息
	 * @return 对象集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <X> List<X> queryListBySQLPage(final String sql, final Map paramMap, final Map<String, Type> resultValueTypeMap,
			final Class<X> entityClass, final PageInfoBean pageInfoBean) {
		try {
			final String strhql = " " + sql + " order ";

			final String hqlTemp = strhql.toLowerCase(Locale.getDefault());

			if (pageInfoBean != null) {
				final StringBuffer hqlCount = new StringBuffer("select count(*)");

				if (hqlTemp.indexOf(" group ") > 0) {
					hqlCount.append(strhql.subSequence(hqlTemp.indexOf(" from "), hqlTemp.indexOf(" group ")));
				} else {
					hqlCount.append(strhql.subSequence(hqlTemp.indexOf(" from "), hqlTemp.indexOf(" order ")));
				}

				final int size = getTotalSQLCount(hqlCount.toString(), paramMap);

				pageInfoBean.setTotalRecordCount(size);
				pageInfoBean.doReviseData();
			}

			final SQLQuery query = getSession().createSQLQuery(sql);

			if (paramMap != null) {
				final Iterator<?> it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}
			}

			if (resultValueTypeMap != null) {
				final Iterator<String> it = resultValueTypeMap.keySet().iterator();
				while (it.hasNext()) {
					final String key = it.next();
					query.addScalar(key, resultValueTypeMap.get(key));
				}
			}

			if (pageInfoBean != null) {
				query.setFirstResult(pageInfoBean.getRecordCountInOnePage() * (pageInfoBean.getCurPageNo() - 1));
				query.setMaxResults(pageInfoBean.getRecordCountInOnePage());
			}

			long start = Calendar.getInstance().getTimeInMillis();
			final List<X> list = query.setResultTransformer(new AliasToBeanResultTransformer(entityClass)).list();
			long end = Calendar.getInstance().getTimeInMillis();
			LOG.debug("queryListBySQLPage SQL: " + sql);
			LOG.debug("SQL took time: " + (end - start) + "millsec");

			return list;
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 支持有条件参数的SQL查询
	 * 
	 * @param querySql 查询记录语句
	 * @param countSql 查询记录数语句
	 * @param paramMap 条件参数
	 * @param resultValueTypeMap 参数类型
	 * @param entityClass 对象类型
	 * @param pageInfoBean 页码信息
	 * @return 对象集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <X> List<X> queryListBySQLPage(final String querySql, final String countSql, final Map paramMap,
			final Map<String, Type> resultValueTypeMap, final Class<X> entityClass, final PageInfoBean pageInfoBean) {
		try {
			if (pageInfoBean != null) {
				final int size = getTotalSQLCount(countSql, paramMap);
				pageInfoBean.setTotalRecordCount(size);
				pageInfoBean.doReviseData();
			}

			final SQLQuery query = getSession().createSQLQuery(querySql);

			if (paramMap != null) {
				final Iterator<?> it = paramMap.keySet().iterator();
				while (it.hasNext()) {
					final Object key = it.next();
					setParam(query, key.toString(), paramMap.get(key));
				}
			}

			if (resultValueTypeMap != null) {
				final Iterator<String> it = resultValueTypeMap.keySet().iterator();
				while (it.hasNext()) {
					final String key = it.next();
					query.addScalar(key, resultValueTypeMap.get(key));
				}
			}

			if (pageInfoBean != null) {
				query.setFirstResult(pageInfoBean.getRecordCountInOnePage() * (pageInfoBean.getCurPageNo() - 1));
				query.setMaxResults(pageInfoBean.getRecordCountInOnePage());
			}

			long start = Calendar.getInstance().getTimeInMillis();
			final List<X> list = query.setResultTransformer(new AliasToBeanResultTransformer(entityClass)).list();
			long end = Calendar.getInstance().getTimeInMillis();
			LOG.debug("queryListBySQLPage SQL: " + querySql);
			LOG.debug("SQL took time: " + (end - start) + "millsec");

			return list;
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 打开一个"临时会话"
	 * 
	 * @param work work对象
	 */
	protected void doWork(final Work work) {
		try {
			this.getSession().doWork(work);
		} finally {
			releaseSession();
		}
	}

	/**
	 * 
	 * 设置条件参数
	 * 
	 * @param query 查询类
	 * @param key 关键字
	 * @param value 对象值
	 */
	private void setParam(final Query query, final String key, final Object value) {
		if (value instanceof java.util.List<?>) {
			query.setParameterList(key, (List<?>) value);
		} else if (value.getClass().isArray()) {
			query.setParameterList(key, (Object[]) value);
		} else {
			query.setParameter(key, value);
		}
	}

	/**
	 * 
	 * 支持无条件参数的HQL查询
	 * 
	 * @param hql 查询语句
	 * @return
	 */
	private Query createQuery(final String hql) {
		return this.getSession().createQuery(hql);
	}

	/**
	 * 
	 * 支持有条件参数的HQL查询
	 * 
	 * @param hql 查询语句
	 * @param params 条件参数
	 * @return
	 */
	private Query createQuery(final String hql, final List<? extends Object> params) {
		final Query query = this.createQuery(hql);

		if (params != null && !params.isEmpty()) {
			final int size = params.size();
			for (int i = 0; i < size; i++) {
				query.setParameter(i, params.get(i));
			}
		}

		return query;
	}

	/**
	 * 
	 * 支持MAP条件参数的HQL查询
	 * 
	 * @param hql 查询语句
	 * @param namedParames 条件参数
	 * @return
	 */
	private Query createQuery(final String hql, final Map<String, ? extends Object> namedParames) {
		final Query query = this.createQuery(hql);
		if (namedParames != null && !namedParames.isEmpty()) {
			final Iterator<String> iter = namedParames.keySet().iterator();
			while (iter.hasNext()) {
				final String key = iter.next();
				setParam(query, key, namedParames.get(key));
			}
		}
		return query;
	}

	/**
	 * 
	 * 支持无条件参数的SQL查询
	 * 
	 * @param sql 查询语句
	 * @return
	 */
	private SQLQuery createSQLQuery(final String sql) {
		return this.getSession().createSQLQuery(sql);
	}

	/**
	 * 
	 * 支持多条件参数的SQL查询
	 * 
	 * @param sql 查询语句
	 * @param params 条件参数集
	 * @return
	 */
	protected SQLQuery createSQLQuery(final String sql, final List<? extends Object> params) {
		final SQLQuery sqlQuery = this.createSQLQuery(sql);

		if (params != null && !params.isEmpty()) {
			final int size = params.size();
			for (int i = 0; i < size; i++) {
				sqlQuery.setParameter(i, params.get(i));
			}
		}

		return sqlQuery;
	}

	/**
	 * 
	 * 支持MAP条件参数的HQL查询
	 * 
	 * @param hql 查询语句
	 * @param namedParames 条件参数
	 * @return
	 */
	protected Query createSQLQuery(final String hql, final Map<String, ? extends Object> namedParames) {
		final Query query = this.createSQLQuery(hql);
		if (namedParames != null && !namedParames.isEmpty()) {
			final Iterator<String> iter = namedParames.keySet().iterator();
			while (iter.hasNext()) {
				final String key = iter.next();
				setParam(query, key, namedParames.get(key));
			}
		}
		return query;
	}

	/**
	 * 
	 * 获取Criteria
	 * 
	 * @param clazz 对象类
	 * @return Criteria对象
	 */
	private <X> Criteria createCriteria(final Class<X> clazz) {
		return this.getSession().createCriteria(clazz);
	}

	/**
	 * 
	 * 获取Criteria
	 * 
	 * @param clazz 对象类
	 * @param criterions 条件集
	 * @param orders 排序方式集
	 * @return Criteria对象
	 */
	private <X> Criteria createCriteria(final Class<X> clazz, final List<Criterion> criterions, final List<Order> orders) {
		final Criteria criteria = this.createCriteria(clazz);
		// 添加查询条件
		if (criterions != null && !criterions.isEmpty()) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		// 添加排序条件
		if (orders != null && !orders.isEmpty()) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		return criteria;
	}

	/**
	 * 
	 * 提供当前需要批量操作的数量
	 * 
	 * @return
	 */
	private int getBatchSize() {
		final Properties properties = ((SessionFactoryImpl) getSessionFactory()).getProperties();

		return Integer.valueOf(String.valueOf(properties.get("hibernate.jdbc.batch_size")));
	}

	/**
	 * 
	 * 返回当前用户的会话对象
	 * 
	 * @return
	 */
	protected Session getSession() {
		return DAOSessionUtil.openSession(getSessionFactory());
	}

	/**
	 * 
	 * 关闭会话
	 */
	protected void releaseSession() {
		DAOSessionUtil.closeSession();
	}
}
