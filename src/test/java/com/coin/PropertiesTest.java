package com.coin;
 
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coin.web.common.Config;
import com.coin.web.service.PropertiesService;

public class PropertiesTest extends AbstractTest {

	@Autowired
	private PropertiesService propertiesService;

	@Test
	public void getProps()   {
		 
		System.out.println("start");
		System.out.println("propertiesService: "+propertiesService);
		log.info("files: "+Config.propertiesFile.length);

		Long t = propertiesService.getBTCTickerTimeOutMinutes();
		System.out.println("t: "+t);
		
		
		System.out.println("end");
	}
  
	
	 
}