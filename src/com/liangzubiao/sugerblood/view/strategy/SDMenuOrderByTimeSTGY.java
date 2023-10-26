package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class SDMenuOrderByTimeSTGY implements SecondaryMenuStrategy {
private SecondaryMenu secondaryMenu = null;

    public SDMenuOrderByTimeSTGY(SecondaryMenu secondaryMenu) {
        this.secondaryMenu = secondaryMenu;
    }

    @Override
    public void execute() {
        secondaryMenu.showHistoryOrderByTime();
    }
}
