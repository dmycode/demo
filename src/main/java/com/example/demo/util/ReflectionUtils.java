
/**  
* @Title: ReflectionUtils.java
* @Package cn.com.libertymutual.claims.util
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2018年10月25日
* @version V1.0  
*/

package com.example.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
* @ClassName: ReflectionUtils
* @Description: TODO(这里用一句话描述这个类的作用)
* @author dy
* @date 2018年10月25日
*/

public class ReflectionUtils {
	/**
	 * *循环向上转型,
	 *获取对象的 DeclaredMethod*
	 * @param object : 子类对象 
	 * @param methodName : 父类中的方法名 
	 * @param parameterTypes : 父类中的方法参数类型 
	 * @return 父类中的方法对象 
	 */
	public static Method getDeclaredMethod(Object object, String methodName,
			Class<?>... parameterTypes) {
		Method method = null;

		for (Class<?> clazz = object
				.getClass(); clazz != Object.class; clazz = clazz
						.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz =
				// clazz.getSuperclass(),最后就不会进入到父类中了

			}
		}

		return null;
	}

	/** 
	* 直接调用对象方法, 而忽略修饰符(private, protected, default) 
	* @param object : 子类对象 
	* @param methodName : 父类中的方法名 
	* @param parameterTypes : 父类中的方法参数类型 
	* @param parameters : 父类中的方法参数 
	* @return 父类中方法的执行结果 
	*/
	public static Object invokeMethod(Object object, String methodName,
			Class<?>[] parameterTypes, Object... parameters) {
		// 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		// 抑制Java对方法进行检查,主要是针对私有方法而言
		try {
			if (null != method) {
				method.setAccessible(true);

				// 调用object 的 method 所代表的方法，其方法的参数是 parameters
				return method.invoke(object, parameters);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 
	* 循环向上转型, 获取对象的 DeclaredField 
	* @param object : 子类对象 
	* @param fieldName : 父类中的属性名 
	* @return 父类中的属性对象 
	*/
	public static Field getDeclaredField(Object object, String fieldName) {
		Field field = null;

		Class<?> clazz = object.getClass();

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				return field;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz =
				// clazz.getSuperclass(),最后就不会进入到父类中了

			}
		}

		return null;
	}

	/** 
	* 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter 
	* @param object : 子类对象 
	* @param fieldName : 父类中的属性名 
	* @param value : 将要设置的值 
	*/

	public static void setFieldValue(Object object, String fieldName,
			Object value) {
		// 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName);
		// 抑制Java对其的检查
		field.setAccessible(true);
		try {
			// 将 object 中 field 所代表的值 设置为 value
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/** 
	* 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter 
	* @param object : 子类对象 
	* @param fieldName : 父类中的属性名 
	* @return : 父类中的属性值 
	*/
	public static Object getFieldValue(Object object, String fieldName) {
		// 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName);
		// 抑制Java对其的检查
		field.setAccessible(true);
		try {
			// 获取 object 中 field 所代表的属性值
			return field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	    * @Title: getAllFields
	    * @Description: 获取所有字段
	    * @param clazz
	    * @return 
	    * List<Field> 返回类型
	    * @author dy
		* @date 2018年10月25日
	    * @throws
	 */
	public static List<Field> getAllFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<>();
		Field[] dFields = clazz.getDeclaredFields();// 获取本类所有字段
		if (null != dFields && dFields.length > 0) {
			fieldList.addAll(Arrays.asList(dFields));
		}
		// 若父类是Object，则直接返回当前Field列表
		Class<?> superClass = clazz.getSuperclass();
		if (superClass == Object.class) {
			return Arrays.asList(dFields);
		}

		// 递归查询父类的field列表
		List<Field> superFields = getAllFields(superClass);
		if (null != superFields && !superFields.isEmpty()) {
			superFields.stream().filter(field -> !fieldList.contains(field)).// 不重复字段
					forEach(field -> fieldList.add(field));
		}
		return fieldList;
	}

	/**
	 * 
	    * @Title: getNullPropertyNames
	    * @Description: 获取所有为null的属性，beanutil.copyProperties调用
	    * @param source
	    * @return 
	    * String[] 返回类型
	    * @author dy
		* @date 2018年12月26日
	    * @throws
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null){
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
