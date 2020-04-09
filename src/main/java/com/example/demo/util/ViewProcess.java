
/**
 * @Title: ViewProcess.java
 * @Package cn.com.libertymutual.claims.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dy
 * @date 2018年10月25日
 * @version V1.0
 */

package com.example.demo.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.annotation.ActualValue;
import com.example.demo.vo.web.ServiceResult;

/**
 * @author dy
 * @ClassName: ViewProcess
 * @Description: 对需要展示的对象进行处理，需在对象中增加ActualValue注解并继承BaseEntity
 * 或在实体中增加 Map<Object, String> actualValue 字段
 * @date 2018年10月25日
 */
@Component
public class ViewProcess {
    // 太多了！！！！！！！！！！！好多没有学过！！！！！！百度也不懂怎么用！！！！！！
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String STRING_RESOURCE_PREFIX = "COD_";
    private static final String STRING_RESOURCE_SPLIT = "_";
    private static final String STRING_RESOURCE_MSG = "MSG";
    private static final String STRING_RESOURCE_ERROR = "ERR";
    private static final String SET_ACTUAL_VALUE = "setActualValue";
    // private static修饰符:只能在自己的类中访问；final变量：引用不能被更改，可以像其中增加，删除和改变内容
    @Autowired
    private JdbcTemplate readJdbcTemplate;

    /**
     * @param object
     * @param langId void 返回类型
     * @throws
     * @Title: process
     * @Description: 处理了serviceResult、list、pojo的情况
     * @author dy
     * @date 2018年10月25日
     */
    public void process(Object object, String langId) {
        if (object instanceof ServiceResult) {
            // instanceof运算符：指出对象是否是特定类的一个实例，返回布尔值
            ServiceResult serviceResult = (ServiceResult) object;
            if (serviceResult.isSuccess()) {
                object = serviceResult.getResult();
                if (object instanceof List) {
                    List<?> list = (List<?>) object;
                    for (Object obj : list) {
                        setActualValue(obj, langId);
                    }
                } else {
                    setActualValue(object, langId);
                }
            }
        } else {
            if (object instanceof List) {
                List<?> list = (List<?>) object;
                for (Object obj : list) {
                    setActualValue(obj, langId);
                }
            } else if (object instanceof PageImpl) {
                PageImpl<?> pageImpl = (PageImpl<?>) object;
                List<?> list = pageImpl.getContent();
                for (Object obj : list) {
                    setActualValue(obj, langId);
                }
            } else {
                setActualValue(object, langId);
            }
        }

    }

    /**
     * @param object
     * @param langId void 返回类型
     * @throws
     * @Title: setActualValue
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author dy
     * @date 2018年10月25日
     */
    public void setActualValue(Object object, String langId) {
        try {
            Map<Object, String> actualValueMap = new HashMap<Object, String>();
            List<Field> fields = ReflectionUtils
                    .getAllFields(object.getClass());
            for (Field field : fields) {
                if (field.isAnnotationPresent(ActualValue.class)) {
                    ActualValue actualValue = field
                            .getAnnotation(ActualValue.class);
                    String tableName = actualValue.tableName().toUpperCase();
                    String value = "";
                    field.setAccessible(true);
                    if (field.get(object) == null) {
                        continue;
                    }
                    String fieldValue = field.get(object).toString();
                    if (actualValue.i18n()) {
                        String key = "";
                        switch (tableName) {
                        case "V_CODELIST":
                            key = STRING_RESOURCE_PREFIX + tableName
                                    + STRING_RESOURCE_SPLIT
                                    + actualValue.codeList().toUpperCase()
                                    + fieldValue;
                            break;
                        case "T_MESSAGE":
                            key = STRING_RESOURCE_MSG + STRING_RESOURCE_SPLIT
                                    + fieldValue;
                            break;
                        case "T_ERROR_TYPE":
                            key = STRING_RESOURCE_ERROR + STRING_RESOURCE_SPLIT
                                    + fieldValue;
                            break;
                        case "T_STRING_RESOURCE":
                            key = fieldValue;
                            break;
                        default:
                            key = STRING_RESOURCE_PREFIX + tableName
                                    + STRING_RESOURCE_SPLIT + fieldValue;
                            break;
                        }
                        value = getStringData(key, langId);
                    } else {
                        if ("T_BASE_CODELIST".equals(tableName)) {
                            value = getCodeListValue(actualValue.codeList(),
                                    fieldValue);
                        } else {
                            value = getTableValue(tableName,
                                    actualValue.tableId(),
                                    actualValue.tableValue(), fieldValue);
                        }
                    }
                    if (!StringUtils.isEmpty(value)) {
                        actualValueMap.put(field.getName(), value);
                    }
                }
            }
            Class<?>[] classs = new Class[] { Map.class };
            ReflectionUtils.invokeMethod(object, SET_ACTUAL_VALUE, classs,
                    actualValueMap);
        } catch (IllegalArgumentException | IllegalAccessException e1) {
            logger.error("通过反射执行方法时发生错误 case {}", e1.getMessage(), e1);
        }
    }

    public String getStringData(String key, String langId) {
        String value = "";
        try {
            String sql = "select  str_data  from t_string_resource where str_id=? and lang_id=? ";
            value = readJdbcTemplate.queryForObject(sql, String.class, key,
                    langId);
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    /**
     * @param tableName
     * @param tableKey
     * @param tableValue
     * @param fieldValue
     * @return String 返回类型
     * @throws
     * @Title: getTableValue
     * @Description: 适用于没有存储在T_STRING_RESOUCE的表
     * @author dy
     * @date 2018年10月25日
     */
    public String getTableValue(String tableName, String tableKey,
            String tableValue, String fieldValue) {
        String value = "";
        try {
            String sql = "select " + tableValue + " from " + tableName
                    + " where " + tableKey + "=?  ";
            value = readJdbcTemplate.queryForObject(sql, String.class,
                    fieldValue);
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    public String getCodeListValue(String codeList, String fieldValue) {
        String value = "";
        try {
            String sql = "select b.code_description from  t_Base_Codelist b  where b.code_list=? and code_value=?";
            value = readJdbcTemplate.queryForObject(sql, String.class, codeList,
                    fieldValue);
        } catch (Exception e) {
            return null;
        }
        return value;
    }
}
