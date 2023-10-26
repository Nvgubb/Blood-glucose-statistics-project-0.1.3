package com.liangzubiao.sugerblood.view;

import com.liangzubiao.sugerblood.uitl.MenuStraregyFactory;
import com.liangzubiao.sugerblood.view.strategy.*;

import javax.sql.DataSource;
import java.util.Scanner;

/** 
* @Description: 主菜单
* @Author: Nvgu
* @CreateDate: 2023/10/26
* @ModifiedDate: 2023/10/26
*/
public class Menu {
    private DataSource dataSource = null;
    private SecondaryMenu SDMenu = null;

    public Menu(DataSource dataSource ) {
        this.dataSource = dataSource;
        SDMenu = new SecondaryMenu(dataSource);
    }

    public void showMenu() {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============欢迎使用血糖统计系统============");
        do {
            System.out.println("    ====== 1.输入       2.历史记录查看 ======");
            System.out.println("    ====== 3.简单查看           0.退出 ======");
            System.out.println("请输入你的选择");
            String next = scanner.next();
            MenuStrategy menuStrategy = MenuStraregyFactory.getMenuStrategy(next);
            if(menuStrategy != null){
                menuStrategy.execute();
            }else {
                System.out.println("你的输入有误，请重新输入！");
            }
            if("0".equals(next)){
                loop = false;
            }

        } while (loop);
        System.out.println("**退出成功**");
    }
}
