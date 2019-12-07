package com.scaler7.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author scaler7
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("u_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户自增长id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 密码
     */
    private String qq;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 图片地址
     */
    private String faceImg;

    /**
     * 上次登录时间
     */
    private String lastLoginTime;

    /**
     * 上次登陆ip地址
     */
    private String lastLoginIp;

    /**
     * 状态 1可用 0禁用
     */
    private Integer state;


}
