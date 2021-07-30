package top.zl.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zl
 * 2021/07/15
 */
@Data
public class Payment implements Serializable {

    private static final long serialVersionUID = 42L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String serial;
}
