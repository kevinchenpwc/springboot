package org.spring.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.CityRepository;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Created by kevin on 17/11/21.
 */
@RestController
public class CityRestController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CityService cityService;
    @Autowired
    private CityRepository cityRepositoryService;

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
    	return cityService.findCityByName(cityName);
    }
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public City findCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findCityByName(cityName);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public City updateCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findCityByName(cityName);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.DELETE)
    public City deleteCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findCityByName(cityName);
    }


    @RequestMapping(value = "/api/city/annotation", method = RequestMethod.GET)
    public City findOneCityForAnnotation(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findOneCityForAnnotation(cityName);
    }
    
    @RequestMapping(value = "/api/city/Hibernate", method = RequestMethod.GET)
    public City findOneCityForHibernate(@RequestParam(value = "id", required = true) Long id) {
        return this.cityRepositoryService.findOne(id);
    }

}
