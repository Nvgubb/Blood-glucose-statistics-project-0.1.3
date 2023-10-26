package com.liangzubiao.sugerblood.view;

import com.liangzubiao.sugerblood.dao.impl.BloodSugerRecordDAOimpl;
import com.liangzubiao.sugerblood.model.BloodSugerRecord;
import com.liangzubiao.sugerblood.uitl.SDMenuStraregyFactory;
import com.liangzubiao.sugerblood.uitl.ValidationUtil;
import com.liangzubiao.sugerblood.view.strategy.SecondaryMenuStrategy;
import com.liangzubiao.sugerblood.visualization.BloodSugerRecordShow;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * @Description: 二级菜单
 * @Author: Nvgu
 * @CreateDate: 2023/10/25
 * @ModifiedDate: 2023/10/25
 */

public class SecondaryMenu {
    private DataSource dataSource = null;
    private BloodSugerRecordDAOimpl bsrDAOimpl = null;
    private BloodSugerRecordShow bloodSugerRecordShow = null;

    public SecondaryMenu(DataSource dataSource) {
        this.dataSource = dataSource;
        this.bsrDAOimpl = new BloodSugerRecordDAOimpl(dataSource);
        bloodSugerRecordShow = new BloodSugerRecordShow();
    }

    public boolean InsertSecondaryMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean loop = false;

            do {
                System.out.println("======请输入你的血糖值（mmol/L）======");
                System.out.println("***注意：范围在3.9-7.8之间 (若要返回界面，输入0)***");

                if (scanner.hasNextFloat()) {//检测是否为数字
                    float data = scanner.nextFloat();

                    if (data == 0) {
                        return false;
                    }

                    if (ValidationUtil.checkInsertValue(data)) {//检验小数点后的位数小于等于2位 和其范围
                        //执行sql语句
                        //插入记录data，这里可以不使用事务
                        bsrDAOimpl.saveRecords(data , new Timestamp(new Date().getTime()));
                        loop = true;
                    }
                } else {
                    System.out.println("你输入的不全是数字");
                    scanner.next(); // 跳过非数字的 token
                    scanner.nextLine(); // 清空输入缓冲区
                }
            } while (!loop);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public void ShowHistorySecondaryMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        System.out.println("===========历史记录二级菜单=========");
        do {
            System.out.println("1.按录入时间排序           2.按血糖值高低排序");
            System.out.println("3.同时按录入时间和血糖高低值排序      0.退出");
            System.out.println("请输入你的选择:");
            String next = scanner.next();
            SecondaryMenuStrategy menuStrategy = SDMenuStraregyFactory.getMenuStrategy(next);
            if (menuStrategy != null) {
                menuStrategy.execute();
            } else {
                System.out.println("你的输入有误，请重新输入！");
            }
            if ("0".equals(next)) {
                loop = false;
            }

        } while (loop);
        return;
    }


    public void showHistoryOrderByTime() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        try {
            do {
                System.out.println("1.ASC  2.DESC  0.退出");
                System.out.println("请输入你的选择:");
                String next = scanner.next();
                if ("1".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{true}, new String[]{"insert_time"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("2".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{false}, new String[]{"insert_time"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("0".equals(next)) {
                    loop = false;
                } else {
                    System.out.println("你的输入有误，请重新输入!");
                }
            } while (loop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }
    }

    public void showHistoryOrderBySuger() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        try {
            do {
                System.out.println("1.ASC  2.DESC  0.退出");
                System.out.println("请输入你的选择:");
                String next = scanner.next();
                if ("1".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{true}, new String[]{"blood_suger_value"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("2".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{false}, new String[]{"blood_suger_value"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("0".equals(next)) {
                    loop = false;
                } else {
                    System.out.println("你的输入有误，请重新输入!");
                }
            } while (loop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }
    }

    public void showHistotyOrderByBoth() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        try {
            do {
                System.out.println("1.AA  2.DD  3.AD  4.DA  0.退出");
                System.out.println("请输入你的选择:");
                String next = scanner.next();
                if ("1".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{true, true}, new String[]{"blood_suger_value", "insert_time"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("2".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{false, false}, new String[]{"blood_suger_value", "insert_time"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("3".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{true, false}, new String[]{"blood_suger_value", "insert_time"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("4".equals(next)) {
                    List<BloodSugerRecord> recordsByOrder = bsrDAOimpl.getRecordsByOrder(new boolean[]{false, true}, new String[]{"blood_suger_value", "insert_time"});
                    bloodSugerRecordShow.printList(recordsByOrder);
                } else if ("0".equals(next)) {
                    loop = false;
                } else {
                    System.out.println("你的输入有误，请重新输入!");
                }
            } while (loop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void simpleShow() {
        Scanner scanner = new Scanner(System.in);
        Float taget = null;
        boolean loop = true;
        try {
            do {
                System.out.println("1.Max  2.Min  0.退出");
                System.out.println("请输入你的选择:");
                String next = scanner.next();
               if("1".equals(next)){
                   taget = bsrDAOimpl.simpleShowMax();
                   bloodSugerRecordShow.printValue("Max" , taget);
               }else if("2".equals(next)){
                   taget = bsrDAOimpl.simpleShowMin();
                   bloodSugerRecordShow.printValue("Min" , taget);
               }else if("0".equals(next)){
                    loop = false;
               }else {
                   System.out.println("你的输入有误，请重新输入!");
               }
            } while (loop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
