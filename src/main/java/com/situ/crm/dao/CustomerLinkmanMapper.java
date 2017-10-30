package com.situ.crm.dao;

import com.situ.crm.pojo.CustomerLinkman;
import com.situ.crm.pojo.CustomerLinkmanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerLinkmanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int countByExample(CustomerLinkmanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int deleteByExample(CustomerLinkmanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int insert(CustomerLinkman record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int insertSelective(CustomerLinkman record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    List<CustomerLinkman> selectByExample(CustomerLinkmanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    CustomerLinkman selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByExampleSelective(@Param("record") CustomerLinkman record, @Param("example") CustomerLinkmanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByExample(@Param("record") CustomerLinkman record, @Param("example") CustomerLinkmanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByPrimaryKeySelective(CustomerLinkman record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer_linkman
     *
     * @mbggenerated Mon Oct 30 14:03:59 CST 2017
     */
    int updateByPrimaryKey(CustomerLinkman record);
}