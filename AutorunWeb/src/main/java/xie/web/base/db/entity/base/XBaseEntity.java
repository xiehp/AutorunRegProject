package xie.web.base.db.entity.base;

import java.lang.reflect.Field;

import javax.persistence.Table;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;

public abstract class XBaseEntity implements IXBaseEntity {

	private static final long serialVersionUID = 1L;

	String ggggggggggggggg = "FGFFFFFFF";

	@Override
	public String getTableName() {
		Table tableAnnotation = this.getClass().getAnnotation(Table.class);
		if (tableAnnotation != null) {
			return tableAnnotation.name();
		}
		return null;
	}

	public String getEntityString() {
		StringBuilder sb = new StringBuilder("");

		Field[] fields = getClass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return "";
		}

		try {
			for (Field field : fields) {
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}

				field.setAccessible(true);
				sb.append(field.getName() + ": " + field.get(this));
				field.setAccessible(false);

				sb.append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

		XRegisterInfoEntity aaa = new XRegisterInfoEntity();
//		System.out.println("TableName : " + aaa.getTableName());
//		System.out.println(aaa.getEntityString());
	}
}
