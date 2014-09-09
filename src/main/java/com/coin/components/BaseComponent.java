package com.coin.components;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.coin.web.service.PropertiesService;

 
public class BaseComponent {
	protected static final Logger log = Logger.getLogger(BaseComponent.class);
	 
	@Autowired
	protected PropertiesService propertiesService;

}
