package com.liangzubiao.sugerblood.uitl;

import com.liangzubiao.sugerblood.view.SecondaryMenu;
import com.liangzubiao.sugerblood.view.strategy.*;
import com.liangzubiao.utiljdbc.Connector;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 二级菜单策略的初始化工厂，因为一旦策略多了，内存就占用大，所以就是采用延后初始化的方式
 * @Author: Nvgu
 * @CreateDate: 2023/10/25
 * @ModifiedDate: 2023/10/25
 */

public class SDMenuStraregyFactory {
    private static Map<String, Class<? extends SecondaryMenuStrategy>> SDstrategyMap = new HashMap<>();

    static {
        SDstrategyMap.put("0" , SDMenuExitSTGY.class);
        SDstrategyMap.put("1" , SDMenuOrderByTimeSTGY.class);
        SDstrategyMap.put("2" , SDMenuOrderByValueSTGY.class);
        SDstrategyMap.put("3" , SDMenuOrderByTimeAndValueSTGY.class);
    }

    public static SecondaryMenuStrategy getMenuStrategy(String name){
        Class<? extends SecondaryMenuStrategy> SDstrategyClass = SDstrategyMap.get(name);
        if(SDstrategyClass == null){
            throw new IllegalArgumentException("Unsupported method: " + name);
        }

        try {
            return SDstrategyClass.getDeclaredConstructor(SecondaryMenu.class).newInstance(new SecondaryMenu(Connector.getDataSource()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate strategy: " + name, e);
        }
    }
}
