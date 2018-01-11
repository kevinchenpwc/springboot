package org.spring.springboot.service;

import org.spring.springboot.domain.City;

/**
 * 城市业务逻辑接口类
 *
 * Created by Created by kevin on 17/11/21.
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);
    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
	City findOneCityForAnnotation(String cityName);
}
