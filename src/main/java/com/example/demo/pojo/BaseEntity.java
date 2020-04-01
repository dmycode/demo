
/**  
* @Title: BaseEntity.java
* @Package cn.com.libertymutual.claims.combean.pojo
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2018年9月21日
* @version V1.0  
*/

package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @ClassName: BaseEntity
* @Description: 实体类基类，实现时间戳功能,必须加入AuditingEntityListener，并且在启动类加入@EnableJpaAuditing才能生效
* @author dy
* @date 2018年9月21日
*/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFY_DATE")
    private Date lastModifyDate;
    @Transient
    private Map<Object, String> actualValue;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Map<Object, String> getActualValue() {
        return actualValue;
    }

    public void setActualValue(Map<Object, String> actualValue) {
        this.actualValue = actualValue;
    }

}
