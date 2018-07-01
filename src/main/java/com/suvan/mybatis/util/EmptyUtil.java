package com.suvan.mybatis.util;

import java.util.Collection;
import java.util.Map;

/**
 * @描述: 用于判断对象是否为空
 * @作者: zhangpan
 * @创建日期: 2016年9月23日 上午11:24:02
 */
public class EmptyUtil {
	
	/**
	 * 判断数组是否为null获取empty
	 * @param list
	 * @return
	 */
	public static<T> Boolean isEmpty(Collection<T> list){
		if(list == null || list.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	public static<K, V> Boolean isEmpty(Map<K, V> map){
		if(map == null || map.keySet().size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组是否是空
	 * @param list
	 * @return
	 */
	public static<T> Boolean isEmpty(T[] list){
		if(list == null || list.length == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断参数里面是否存在null或者“”
	 * @param objs
	 * @return
	 */
	public static Boolean isHaveEmpty(Object ... objs){
		if(objs == null || objs.length == 0){
			return true;
		}
		for(Object obj : objs){
			if(obj == null){
				return true;
			}
			if(obj instanceof String){
				if(obj.equals("")){
					return true;
				}
			}
		}
		return false;
	}
}
