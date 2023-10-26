package com.liangzubiao.sugerblood.view.strategy;

import com.liangzubiao.sugerblood.view.SecondaryMenu;

public class SDMenuExitSTGY implements SecondaryMenuStrategy {
private SecondaryMenu secondaryMenu = null;

    public SDMenuExitSTGY(SecondaryMenu secondaryMenu) {
        this.secondaryMenu = secondaryMenu;
    }

    @Override
    public void execute() {
        System.out.println("退出成功");
    }
}
