package com.liangzubiao.sugerblood.uitl;

import com.liangzubiao.sugerblood.view.SecondaryMenu;
import com.liangzubiao.sugerblood.view.strategy.*;
import com.liangzubiao.utiljdbc.Connector;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 菜单策略的初始化工厂，因为一旦策略多了，内存就占用大，所以就是采用延后初始化的方式
 * @Author: Nvgu
 * @CreateDate: 2023/10/25
 * @ModifiedDate: 2023/10/25
 */
public class MenuStraregyFactory {
    private static Map<String, Class<? extends MenuStrategy>> strategyMap = new HashMap<>();

    static {
        strategyMap.put("0", MenuExitSTGY.class);
        strategyMap.put("1", MenuInsertSTGY.class);
        strategyMap.put("2", MenuShowHistorySTGY.class);
        strategyMap.put("3", MenuSimpleShowSTGY.class);

    }
//
//    @Test
//    public void test() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        Class<MenuInsertSTGY> menuInsertSTGYClass = MenuInsertSTGY.class;
//        Constructor<MenuInsertSTGY> declaredConstructor = menuInsertSTGYClass.getDeclaredConstructor(SecondaryMenu.class);
//        MenuInsertSTGY menuInsertSTGY = declaredConstructor.newInstance();
//    }

    public static MenuStrategy getMenuStrategy(String name) {
        Class<? extends MenuStrategy> strategyClass = strategyMap.get(name);
        if (strategyClass == null) {
            throw new IllegalArgumentException("Unsupported method: " + name);
        }

        try {
            return strategyClass.getDeclaredConstructor(SecondaryMenu.class).newInstance(new SecondaryMenu(Connector.getDataSource()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate strategy: " + name, e);
        }
    }
}
