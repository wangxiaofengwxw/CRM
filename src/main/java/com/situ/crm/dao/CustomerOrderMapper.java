package com.situ.crm.dao;

import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int countByExample(CustomerOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int deleteByExample(CustomerOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int insert(CustomerOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int insertSelective(CustomerOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    List<CustomerOrder> selectByExample(CustomerOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    CustomerOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByExampleSelective(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByExample(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByPrimaryKeySelective(CustomerOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_order
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByPrimaryKey(CustomerOrder record);
}