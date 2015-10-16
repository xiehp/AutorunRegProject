package xie.web.base.db.entity.base;

import java.io.Serializable;

public interface IXBaseEntity extends Serializable {
	String toEntityString() throws IllegalArgumentException, IllegalAccessException;
	
	String getTableName();
}
