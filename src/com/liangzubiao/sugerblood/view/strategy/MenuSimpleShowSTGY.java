package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class MenuSimpleShowSTGY implements MenuStrategy{
    private SecondaryMenu SDMenu = null;

    public MenuSimpleShowSTGY(SecondaryMenu SDMenu) {
        this.SDMenu = SDMenu;
    }

    @Override
    public void execute() {
        SDMenu.simpleShow();
    }
}
