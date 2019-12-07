package com.scaler7.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_article")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 标题图片
     */
    @TableField("titlePic")
    private String titlePic;

    private String slug;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 最后一次修改时间
     */
    private String modified;

    /**
     * 内容文字
     */
    private String content;

    /**
     * 作者id
     */
    @TableField("authorId")
    private Integer authorId;

    /**
     * 类别
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 标签
     */
    private String tags;

    /**
     * 属性
     */
    private String categories;

    /**
     * 点击数
     */
    private Integer hits;

    /**
     * 评论数
     */
    @TableField("commentsNum")
    private Integer commentsNum;

    /**
     * 是否允许被评论
     */
    @TableField("allowComment")
    private Integer allowComment;


}
