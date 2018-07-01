/**
* @描述: DemoMapper.java
* @作者: suvan
* @创建日期: 2018-07-01 07:20:08
*/
package com.suvan.demo.plugin.mapper;

import com.suvan.demo.plugin.model.Demo;
import com.suvan.demo.plugin.model.DemoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemoMapper {
    /**
     *  根据指定的条件获取数据库记录数
     *
     * @param example
     */
    long countByExample(DemoExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录
     *
     * @param example
     */
    int deleteByExample(DemoExample example);

    /**
     *  根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  批量写入数据库记录
     *
     * @param list
     */
    int insertBatch(List<Demo> list);

    /**
     *  新写入数据库记录
     *
     * @param record
     */
    int insert(Demo record);

    /**
     *  动态字段,写入数据库记录
     *
     * @param record
     */
    int insertSelective(Demo record);

    /**
     *  根据指定的条件查询符合条件的数据库记录
     *
     * @param example
     */
    List<Demo> selectByExample(DemoExample example);

    /**
     *  根据指定主键获取一条数据库记录
     *
     * @param id
     */
    Demo selectByPrimaryKey(Integer id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);

    /**
     *  批量根据主键更新记录
     *
     * @param list
     */
    int updateBatchByPrimaryKeySelective(List<Demo> list);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Demo record);

    /**
     *  批量根据主键更新记录
     *
     * @param list
     */
    int updateBatchByPrimaryKey(List<Demo> list);

    /**
     *  根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(Demo record);
}