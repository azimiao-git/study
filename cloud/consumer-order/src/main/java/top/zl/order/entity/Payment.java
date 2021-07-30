package top.zl.order.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zl
 * 2021/07/15
 */
@Data
public class Payment implements Serializable {

    private static final long serialVersionUID = 42L;

    private Integer id;

    private String serial;
}
