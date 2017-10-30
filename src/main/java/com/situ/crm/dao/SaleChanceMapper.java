package com.situ.crm.dao;

import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.SaleChanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleChanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int countByExample(SaleChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int deleteByExample(SaleChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int insert(SaleChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int insertSelective(SaleChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    List<SaleChance> selectByExample(SaleChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    SaleChance selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int updateByExampleSelective(@Param("record") SaleChance record, @Param("example") SaleChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int updateByExample(@Param("record") SaleChance record, @Param("example") SaleChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int updateByPrimaryKeySelective(SaleChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sale_chance
     *
     * @mbggenerated Thu Oct 26 15:55:21 CST 2017
     */
    int updateByPrimaryKey(SaleChance record);
}