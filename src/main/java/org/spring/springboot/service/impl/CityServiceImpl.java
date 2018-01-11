package org.spring.springboot.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.CityAnnotationDao;
import org.spring.springboot.dao.CityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 * Created by Created by kevin on 17/11/21.
 */
@Service
public class CityServiceImpl implements CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	@Autowired
	private RedisTemplate redisTemplate;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private CityAnnotationDao cityAnnotationDao;

    public City findCityByName(String cityName) {
    	
        return cityDao.findByName(cityName);
    }
    public City findOneCityForAnnotation(String cityName) {
    	 // 从缓存中获取城市信息
        String key = "city_" + cityName;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }
        City city = cityAnnotationDao.findByName(cityName);
        // 插入缓存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
        return city;
    }

}
