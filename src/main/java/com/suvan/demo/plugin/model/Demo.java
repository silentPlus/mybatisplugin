/**
* @描述: Demo.java
* @作者: suvan
* @创建日期: 2018-07-03 08:11:36
*/
package com.suvan.demo.plugin.model;

import com.suvan.mybatis.paging.model.BasePagingModel;

public class Demo extends BasePagingModel {
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * updatetime
     */
    private String updatetime;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return updatetime 
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * 
     * @param updatetime 
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}