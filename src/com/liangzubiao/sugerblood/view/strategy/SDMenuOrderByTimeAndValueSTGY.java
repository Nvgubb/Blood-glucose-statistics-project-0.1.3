package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class SDMenuOrderByTimeAndValueSTGY implements SecondaryMenuStrategy{
private SecondaryMenu secondaryMenu = null;

    public SDMenuOrderByTimeAndValueSTGY(SecondaryMenu secondaryMenu) {
        this.secondaryMenu = secondaryMenu;
    }

    @Override
    public void execute() {
        secondaryMenu.showHistotyOrderByBoth();
    }
}
