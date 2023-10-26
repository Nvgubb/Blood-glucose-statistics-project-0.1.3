package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class MenuShowHistorySTGY implements MenuStrategy{
    private SecondaryMenu SDMenu = null;

    public MenuShowHistorySTGY(SecondaryMenu SDMenu) {
        this.SDMenu = SDMenu;
    }

    @Override
    public void execute() {
        SDMenu.ShowHistorySecondaryMenu();
    }
}
