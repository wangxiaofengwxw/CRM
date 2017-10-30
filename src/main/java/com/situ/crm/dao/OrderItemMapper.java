package com.situ.crm.dao;

import com.situ.crm.pojo.OrderItem;
import com.situ.crm.pojo.OrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int countByExample(OrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int deleteByExample(OrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int insert(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int insertSelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    List<OrderItem> selectByExample(OrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    OrderItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_item
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByPrimaryKey(OrderItem record);
}