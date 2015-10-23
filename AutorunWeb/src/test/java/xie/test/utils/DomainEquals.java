package xie.test.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainEquals {

	/**
	 * 日志操作类
	 */
	private static Logger logger = LoggerFactory.getLogger(DomainEquals.class);

	public DomainEquals() {
	}

	/**
	 * 比较两个BEAN或MAP对象的值是否相等 如果是BEAN与MAP对象比较时MAP中的key值应与BEAN的属性值名称相同且字段数目要一致
	 *
	 * @param source source
	 * @param target target
	 * @return boolean
	 */
	public static boolean domainEquals(Object source, Object target) {
		if (source == null || target == null) {
			return false;
		}
		boolean rv = true;
		if (source instanceof Map) {
			rv = mapOfSrc(source, target, rv);
		} else {
			rv = classOfSrc(source, target, rv);
		}
		// logger.info("THE EQUALS RESULT IS " + rv);
		return rv;
	}

	/**
	 * 源目标为MAP类型时
	 *
	 * @param source source
	 * @param target target
	 * @param rv rv
	 * @return boolean
	 */
	private static boolean mapOfSrc(Object source, Object target, boolean rv) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = (HashMap) source;
		for (String key : map.keySet()) {
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				if (tarMap.get(key) == null) {
					rv = false;
					break;
				}
				if (!map.get(key).equals(tarMap.get(key))) {
					rv = false;
					break;
				}
			} else {
				String tarValue = getClassValue(target, key) == null ? "" : getClassValue(target, key).toString();
				if (!tarValue.equals(map.get(key))) {
					rv = false;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 源目标为非MAP类型时
	 *
	 * @param source source
	 * @param target target
	 * @param rv rv
	 * @return boolean
	 */
	private static boolean classOfSrc(Object source, Object target, boolean rv) {
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
				if (tarMap.get(nameKey) == null) {
					rv = false;
					break;
				}
				if (!tarMap.get(nameKey).equals(srcValue)) {
					rv = false;
					break;
				}
			} else {
				String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey).toString();
				String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey).toString();
				if (!srcValue.equals(tarValue)) {
					rv = false;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 根据字段名称取值
	 *
	 * @param obj obj
	 * @param fieldName fieldName
	 * @return Object
	 */
	public static Object getClassValue(Object obj, String fieldName) {
		if (obj == null) {
			return null;
		}
		try {
			Class beanClass = obj.getClass();
			Method[] ms = beanClass.getMethods();
			for (int i = 0; i < ms.length; i++) {
				// 非get方法不取
				if (!ms[i].getName().startsWith("get")) {
					continue;
				}
				Object objValue = null;
				try {
					objValue = ms[i].invoke(obj, new Object[] {});
					logger.debug("fieldName：{}, value：{}", fieldName, objValue);
				} catch (Exception e) {
					logger.debug("反射取值出错：{}", e.toString());
					continue;
				}
				if (objValue == null) {
					continue;
				}
				if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase()) || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
					return objValue;
				} else if (fieldName.toUpperCase().equals("SID") && (ms[i].getName().toUpperCase().equals("ID") || ms[i].getName().substring(3).toUpperCase().equals("ID"))) {
					return objValue;
				}
			}
		} catch (Exception e) {
			// logger.info("取方法出错！" + e.toString());
		}
		return null;
	}

	public static void main(String args[]) {
	}

}