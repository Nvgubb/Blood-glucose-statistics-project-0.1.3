package com.liangzubiao.sugerblood.view;

import com.liangzubiao.utiljdbc.Connector;

import javax.sql.DataSource;

public class Main {
    static private DataSource dataSource = null;
    public static void main(String[] args) {
        dataSource = Connector.getDataSource();
        new Menu(dataSource).showMenu();
    }
}
