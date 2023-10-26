package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class SDMenuOrderByValueSTGY implements SecondaryMenuStrategy {
    private SecondaryMenu secondaryMenu = null;

    public SDMenuOrderByValueSTGY(SecondaryMenu secondaryMenu) {
        this.secondaryMenu = secondaryMenu;
    }

    @Override
    public void execute() {
        secondaryMenu.showHistoryOrderBySuger();
    }
}
