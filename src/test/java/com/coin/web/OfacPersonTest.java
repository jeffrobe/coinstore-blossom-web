package com.coin.web;
 
import org.apache.log4j.Logger;
import org.junit.Test;

import com.coin.model.Message;
import com.coin.model.OfacPerson;

 
public class OfacPersonTest {

	private static final Logger log = Logger.getLogger(OfacPersonTest.class);
 

	@Test 
	public void getPerson() throws Exception {
		String firstName="Wilfred";
		String lastName="EGGLETON";
		 
		Message msg = null;
		 
		
		if (msg.getExtra()!= null) {
			OfacPerson p = (OfacPerson) msg.getExtra();
			log.info("found person: "+p);
		}
		
		
	}
	
}