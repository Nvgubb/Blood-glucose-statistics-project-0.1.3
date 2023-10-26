package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class MenuInsertSTGY implements MenuStrategy{
    private SecondaryMenu SDMenu = null;

    public MenuInsertSTGY(SecondaryMenu SDMenu) {
        this.SDMenu = SDMenu;
    }

    @Override
    public void execute() {
        boolean b = SDMenu.InsertSecondaryMenu();
        if(b){
            System.out.println("插入成功");
        }
    }
}
