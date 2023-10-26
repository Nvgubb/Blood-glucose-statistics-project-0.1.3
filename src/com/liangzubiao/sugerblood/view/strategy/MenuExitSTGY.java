package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class MenuExitSTGY implements MenuStrategy{
    private SecondaryMenu secondaryMenu = null;

    public MenuExitSTGY(SecondaryMenu secondaryMenu) {
        this.secondaryMenu = secondaryMenu;
    }

    @Override
    public void execute() {
        System.out.println("退出成功");
    }
}
