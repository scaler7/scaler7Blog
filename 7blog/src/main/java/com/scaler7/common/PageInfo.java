package com.scaler7.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
* @ClassName: scaler7
* @Description: 自定义PageInfo(虽然好像没用到),是针对于layui封装的，但是后来考虑决定后台管理系统不用layui写，所以这个类就被关小黑屋了
* @author Zzz  
 * @param <T>
 * @param <T>
* @date 2019年11月28日  
*
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {
	
	private Integer  page;//页码
	
	private Integer limit;//每页数据条数
	
	private long count;// 总条数
	
	private List<T> data;// 具体的数据

}
