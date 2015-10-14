package xie.web.base.db.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import xie.web.base.db.entity.base.IXBaseEntity;

/**
 * 
 * 通用数据库方法抽象类
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
public abstract class XAbstractCommonDAO<VO extends IXBaseEntity> extends XAbstractBaseDAO {
	private static final long serialVersionUID = 1L;

	private final Class<VO> entityClass;

	// private Class<? extends Serializable> pkClass;

	/**
	 * 
	 * 构造函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public XAbstractCommonDAO() {
		super();
		final Type genType = getClass().getGenericSuperclass();
		final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
		/*
		 * final Field [] fields = entityClass.getDeclaredFields(); for(final
		 * Field filed:fields){ for(final Annotation
		 * anno:filed.getAnnotations()){
		 * if("javax.persistence.Id".equals(anno.annotationType().getName()) ||
		 * "javax.persistence.EmbeddedId"
		 * .equals(anno.annotationType().getName())){ pkClass = (Class<? extends
		 * Serializable>) filed.getGenericType(); break; }
		 * 
		 * } if(pkClass!=null){ break; } }
		 */
	}

	/**
	 * 
	 * 获取实例
	 * 
	 * @return VO对象
	 */
	protected Class<VO> getEntityClass() {
		return entityClass;
	}

	/*
	 * protected Class<? extends Serializable> getPkClass() { return pkClass; }
	 * 
	 * protected void checkPkType(final Serializable id){ if(
	 * getPkClass()!=null){ if(!getPkClass().isAssignableFrom(id.getClass())){
	 * throw new SysException("Please input PK type: " +
	 * getPkClass().getName()); } } }
	 */

	/**
	 * 
	 * 根据id获得VO对象
	 * 
	 * @param id
	 * @return VO对象
	 */
	public VO findById(final Serializable id) {
		// checkPkType(id);

		return (VO) super.get(getEntityClass(), id);
	}

	/**
	 * 
	 * 保存VO对象
	 * 
	 * @param vo
	 * @return VO对象
	 */
	public VO save(final VO vo) {
		return super.save(vo);
	}

	/**
	 * 
	 * 更新VO对象
	 * 
	 * @param vo
	 */
	public void update(final VO vo) {
		super.update(vo);
	}

	/**
	 * 
	 * 获取所有的VO对象
	 * 
	 * @return VO对象集合
	 */
	public List<VO> findAll() {
		return super.query(getEntityClass());
	}

	/**
	 * 
	 * 删除VO对象
	 * 
	 * @param vo
	 */
	public void delete(final VO vo) {
		super.delete(vo);
	}

	/**
	 * 
	 * 根据对象id删除对象
	 * 
	 * @param id
	 */
	public void delete(final Serializable id) {
		// checkPkType(id);

		super.delete(getEntityClass(), id);
	}
}
