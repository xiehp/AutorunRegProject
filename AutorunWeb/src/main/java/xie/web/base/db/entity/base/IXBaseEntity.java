package xie.web.base.db.entity.base;

import java.io.Serializable;

public interface IXBaseEntity extends Serializable {
	String getEntityString() throws IllegalArgumentException, IllegalAccessException;
	
	String getTableName();
}
