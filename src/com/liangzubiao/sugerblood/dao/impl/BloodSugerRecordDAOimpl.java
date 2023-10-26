package com.liangzubiao.sugerblood.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.liangzubiao.sugerblood.dao.BloodSugerRecordDAO;
import com.liangzubiao.sugerblood.model.BloodSugerRecord;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: 血糖记录DAO的实现类，基于dbutil来实现
 * @Author: Nvgu
 * @CreateDate: 2023/10/25
 * @ModifiedDate: 2023/10/25
 */



public class BloodSugerRecordDAOimpl implements BloodSugerRecordDAO {
    private DataSource druidDataSource = null;//德鲁伊连接池数据源
    private QueryRunner queryRunner = null;//这里多线程访问时需要注意

    public BloodSugerRecordDAOimpl(DataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
        queryRunner = new QueryRunner(druidDataSource);
    }

    //基于事务：
    @Override
    public List<BloodSugerRecord> getRecords(Connection connection) throws SQLException {
        String sql = "SELECT blood_suger_value `value` , blood_suger_unit unit , insert_time insertTime FROM blood_suger";
        return queryRunner.query(connection, sql, new BeanListHandler<>(BloodSugerRecord.class));
    }


    /**
     * @Description:通过传入的不同参数来决定排序的方法,注意：这里需要在调用方法时，检验参数是否合理，避免sql注入
     * @Param: [connection: 连接, isAsc :尔数组--决定是ASC还是DESC , avg -- 填充占位符的元素]
     * @return: java.util.List<com.liangzubiao.sugerblood.model.BloodSugerRecord>
     * @Author: Nvgu
     */
    @Override
    public List<BloodSugerRecord> getRecordsByOrder(Connection connection, boolean[] isAsc, String[] strings) throws SQLException {
        String sql = "SELECT blood_suger_value `value` , blood_suger_unit unit , insert_time insertTime FROM blood_suger ORDER BY";
        for (int i = 0; i < isAsc.length; i++) {
            sql += " " + strings[i] + " " + (isAsc[i] == true ? "ASC" : "DESC");
        }
        return queryRunner.query(connection, sql, new BeanListHandler<>(BloodSugerRecord.class));
    }

    @Override
    public int saveRecords(Connection connection, Object... avg) throws SQLException {
        String sql = "insert into blood_suger(blood_suger_value , insert_time) values (? , ?)";
        return queryRunner.update(connection, sql);
    }

    @Override
    public Float simpleShowMax(Connection connection) throws SQLException {
        String sql = "SELECT MAX(m_value) value FROM(SELECT blood_suger_value m_value FROM blood_suger ORDER BY insert_time DESC LIMIT 0,5)temp";
        return queryRunner.query(connection, sql, new ScalarHandler<>("value"));
    }

    @Override
    public Float simpleShowMin(Connection connection) throws SQLException {
        String sql = "SELECT MIN(m_value) value FROM(SELECT blood_suger_value m_value FROM blood_suger ORDER BY insert_time DESC LIMIT 0,5)temp";
        return queryRunner.query(connection, sql, new ScalarHandler<>("value"));
    }


    //不基于事务
    @Override
    public List<BloodSugerRecord> getRecords() throws SQLException {
        String sql = "SELECT blood_suger_value `value` , blood_suger_unit unit , insert_time insertTime FROM blood_suger";
        return queryRunner.query(sql, new BeanListHandler<>(BloodSugerRecord.class));
    }

    @Override
    public List<BloodSugerRecord> getRecordsByOrder(boolean[] isAsc, String[] strings) throws SQLException {
        String sql = "SELECT blood_suger_value `value` , blood_suger_unit unit , insert_time insertTime FROM blood_suger ORDER BY";
        for (int i = 0; i < isAsc.length ; i++) {
            if(i == isAsc.length - 1 ){
                sql += " " + strings[i] + " " + (isAsc[i] == true ? "ASC" : "DESC");
            }else {
                sql += " " + strings[i] + " " + (isAsc[i] == true ? "ASC," : "DESC,");
            }
        }
        return queryRunner.query(sql, new BeanListHandler<>(BloodSugerRecord.class));
    }

    @Override
    public int saveRecords(Object... avg) throws SQLException {
        String sql = "insert into blood_suger(blood_suger_value , insert_time) values (? , ?)";
        return queryRunner.update(sql , avg);
    }

    @Override
    public Float simpleShowMax() throws SQLException {
        String sql = "SELECT MAX(m_value) value FROM(SELECT blood_suger_value m_value FROM blood_suger ORDER BY insert_time DESC LIMIT 0,5)temp";
        return queryRunner.query(sql, new ScalarHandler<>("value"));
    }

    @Override
    public Float simpleShowMin() throws SQLException {
        String sql = "SELECT MIN(m_value) value FROM(SELECT blood_suger_value m_value FROM blood_suger ORDER BY insert_time DESC LIMIT 0,5)temp";
        return queryRunner.query(sql, new ScalarHandler<>("value"));
    }

}
