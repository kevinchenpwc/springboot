package org.spring.springboot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * 城市实体类
 *
 * Created by Created by kevin on 17/11/21.
 */
@Getter
@Setter
@Entity
public class City implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2081742442561524068L;

	/**
     * 城市编号
     */
	@Id
    private Long id;

    /**
     * 省份编号
     */
	@Column
    private Long provinceId;

    /**
     * 城市名称
     */
	@Column
    private String cityName;

    /**
     * 描述
     */
	@Column
    private String description;

}
