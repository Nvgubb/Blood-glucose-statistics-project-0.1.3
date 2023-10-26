package com.liangzubiao.sugerblood.dao;

import com.liangzubiao.sugerblood.model.BloodSugerRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: 血糖记录DAO
 * @Author: Nvgu
 * @CreateDate: 2023/10/25
 * @ModifiedDate: 2023/10/25
 */
public interface BloodSugerRecordDAO {

    //基于事务：
    List<BloodSugerRecord> getRecords(Connection connection) throws SQLException;//获得全部记录

    List<BloodSugerRecord> getRecordsByOrder(Connection connection, boolean[] isAsc , String[] strings) throws SQLException;//获得排序记录

    int saveRecords(Connection connection, Object... avg) throws SQLException;//插入记录

    Float simpleShowMax(Connection connection )throws SQLException;//简单查询近5条记录的最大值

    Float simpleShowMin(Connection connection)throws SQLException;//简单查询近5条记录的最小值




    //不基于事务：
    List<BloodSugerRecord> getRecords() throws SQLException;//获得全部记录

    List<BloodSugerRecord> getRecordsByOrder( boolean[] isAsc , String[] strings) throws SQLException;//获得排序记录

    int saveRecords( Object... avg) throws SQLException;//插入记录

    Float simpleShowMax()throws SQLException;//简单查询近5条记录的最大值

    Float simpleShowMin()throws SQLException;//简单查询近5条记录的最小值

}
