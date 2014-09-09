package com.coin.web.service.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import com.coin.web.common.Config;
import com.coin.service.impl.BasePropertiesServiceImpl;
import com.coin.web.service.PropertiesService;
 
@Service ("propertiesService")
public class PropertiesServiceImpl extends BasePropertiesServiceImpl implements PropertiesService {

	public PropertiesServiceImpl() {
		setPropfiles(Config.propertiesFile); 
	}
	
	
	@Override
	public String getBTCExchangeServerUrl()  { return getProp("BTCExchangeServerUrl"); }
		
	@Override
	public String getBTCAddressUrl()  { return getProp("BTCAddressUrl"); }
 
	@Override
	public String getBTCTickersUrl()  { return getProp("BTCTickersUrl"); }

	@Override
	public String getBTCTickerUrl()  { return getProp("BTCTickerUrl"); }

	@Override
	public boolean overrideEmail() {return false;	}

	@Override
	public String getEmailOverrideAddress() {		return null;	}


	@Override
	public Long getBTCTickerTimeOutMinutes()  { return getPropLong("BTCTickerTimeOutMinutes"); }
	
	@Override
	public String getDataDir() { return properties.getProperty("dataDir");  	}

	@Override
	public String getOpacSdnFile() { return properties.getProperty("opacSdnFile");  	}

	@Override
	public String getFedWireRoutingNumberFile() { return properties.getProperty("fedWireRoutingNumberFile"); }

	@Override
	public boolean getRequireImageAuth() {
		 
		return false;
	}

	@Override
	public boolean getOverrideImageAuth() {
		 
		return false;
	}

	@Override
	public String getOverrideImageAuthValue() {
		 
		return null;
	}

	@Override
	public Float getTransactionPctFee() {
		 
		return null;
	}

	@Override
	public Float getTtransactionBaseFee() {
		 
		return null;
	}

	@Override
	public Boolean getRequireTokenAuth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getSeperateTokenAuth() {
		 
		return null;
	}

	@Override
	public String getEmailFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getSendEmail() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getSendEmailToFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getEmailOutDir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getSendHtml() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getOflacValidate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getOverrideTokenAuth()  { return getPropBoolean("max_fundingsource_sup_system_minutes"); }


	@Override
	public String getOverrideTokenAuthValue()  { return getProp("max_fundingsource_sup_system_minutes"); }


	@Override
	public String getBaseUrl()  { return getProp("max_fundingsource_sup_system_minutes"); }


	@Override
	public String getEmailRegistrationUrl()  { return getProp("max_fundingsource_sup_system_minutes"); }


	@Override
	public String getEmailForogotpasswordUrl() { return getProp("max_fundingsource_dup_system_minutes"); }


	@Override
	public long getFundingSourceDupUserMinutes(){ return getPropLong("max_fundingsource_dup_user_minutes"); }


	@Override
	public long getFundingSourceDupSystemMinutes() { return getPropLong("max_fundingsource_dup_system_minutes"); }

	@Override
	public int getMaxFundingSourcePerUser() { return getPropInt("max_fundingsource_system"); }

	@Override
	public int getMaxFundingSourceActivePerUser() { return getPropInt("max_fundingsource_active_user"); }

	@Override
	public Long getMaxFundingSourceSystem() { return getPropLong("max_fundingsource_system"); }

	@Override
	public Long getMaxLoginsPeriodCount() { return getPropLong("max_login_period_count"); }

	@Override
	public Long getMaxFailedLoginsPeriodCount() { return getPropLong("max_fail_login_period_count"); }

	@Override
	public Long getUserMaxLoginsPeriodCount() { return getPropLong("max_user_login_period_count"); }

	@Override
	public Long getUserMaxFailedLoginsPeriodCount() { return getPropLong("max_user_fail_login_period_count"); }

	@Override
	public Long getUserFailLoginPeriodMinutes() {return getPropLong("max_user_fail_login_period_minutes"); }

	@Override
	public Long getUserLoginPeriodMinutes() { return getPropLong("max_user_login_period_minutes"); }

	@Override
	public Long getFailLoginPeriodMinutes() {return getPropLong("max_fail_login_period_minutes"); }

	@Override
	public Long getLoginPeriodMinutes() { return getPropLong("max_login_period_minutes"); }



	@Override
	public Long getMaxOrderUserPeriodMinutes() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Long getMaxOrderSystemPeriodMinutes() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Float getMaxNumOrderUser() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Float getMaxNumOrderSystem() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Float getMaxOrderUser() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Float getMaxDollarOrderSystem() {
		// TODO Auto-generated method stub
		return null;
	}

 

}
