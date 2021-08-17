package top.zl.proxy.jdkproxy;

import lombok.Data;

/**
 * @author zl
 * 2021/08/16
 */
@Data
public class Hero implements Equip{

    private String select;

    public Hero(String name){
        this.select=name;
    }

    @Override
    public void buy() {
        System.out.println(select + "购买了装备");
    }
}
