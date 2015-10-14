package xie.web.base.db.dao.base;

import java.io.Serializable;
import java.util.List;

import xie.web.base.db.entity.base.IXBaseEntity;

public interface IXBaseDao<VO extends IXBaseEntity> extends Serializable{

	/**
	 * 
	 * 根据id查询对象
	 * @param id
	 * @return VO对象
	 */
	VO findById(Serializable id);

	/**
	 * 
	 * 保存对象
	 * @param vo
	 * @return
	 */
	VO save(VO vo);
    
	/**
	 * 
	 * 更新对象
	 * @param vo
	 */
    void update(VO vo);
    
    /**
     * 
     * 获取所有的对象
     * @return VO对象集合
     */
    List<VO> findAll();

    /**
     * 
     * 删除对象
     * @param VO
     */
    void delete(VO VO);
    
    /**
     * 
     * 根据对象id删除对象
     * @param id
     */
    void delete(Serializable id);
}
