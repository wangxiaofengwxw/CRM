package com.situ.crm.dao;

import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated Thu Oct 26 11:54:32 CST 2017
     */
    int updateByPrimaryKey(Customer record);
}