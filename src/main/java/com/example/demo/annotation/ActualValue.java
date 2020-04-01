
/**  
* @Title: ActualValue.java
* @Package cn.com.libertymutual.claims.cominterfaces.annotation
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2018年10月24日
* @version V1.0  
*/

package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: ActualValue
* @Description: 标注在编码字段上前端展示实际值
* @author dy
* @date 2018年10月24日
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ActualValue {

	/**
	 * 
	    * @Title: storeTable
	    * @Description:键值对储存的表 example：T_STRING_RESOURCE
	    * @return 
	    * String 返回类型
	    * @author dy
		* @date 2018年10月24日
	    * @throws
	 */
	String tableName();

	/**
	 * 
	    * @Title: tableId
	    * @Description: 指向key存储的字段名,国际化为false时，tableId必填
	    * @return 
	    * String 返回类型
	    * @author dy
		* @date 2018年10月24日
	    * @throws
	 */
	String tableId() default "";

	/**
	 * 
	    * @Title: tableValue
	    * @Description: 指向value存储的字段名,国际化为false时，tableValue必填
	    * @return 
	    * String 返回类型
	    * @author dy
		* @date 2018年10月25日
	    * @throws
	 */
	String tableValue() default "";
	/**
	 * 
	    * @Title: i18n
	    * @Description: 如果不需要进行国际化，i18n必须设为false
	    * @return 
	    * boolean 返回类型
	    * @author dy
		* @date 2018年10月24日
	    * @throws
	 */
	boolean i18n() default true;

	/**
	 * 
	    * @Title: codeList
	    * @Description: 当tableName=vcodeList时 codeList 必填
	    * @return 
	    * String 返回类型
	    * @author dy
		* @date 2018年10月30日
	    * @throws
	 */
	String codeList() default "";
}
