package cn.bizfocus.scm.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author songxuan
 * @date 2020/04/03
 */
@Data
public abstract class BaseEntity {


    /**
     * id
     */
    private Long id;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;


    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long modifiedBy;


    /**
     * 删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
