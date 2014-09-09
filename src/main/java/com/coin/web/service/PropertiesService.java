package com.coin.web.service;

import java.util.Properties;

import com.coin.service.BasePropertiesService;

public interface PropertiesService extends BasePropertiesService {

	Properties getProperties();

	boolean overrideEmail();

	String getEmailOverrideAddress();
	String getBTCTickerUrl();


	boolean getRequireImageAuth();

	boolean getOverrideImageAuth();

	String getOverrideImageAuthValue();

	Float getTransactionPctFee();

	Float getTtransactionBaseFee();

	Boolean getRequireTokenAuth();

	Boolean getSeperateTokenAuth();

	String getEmailFrom();

	boolean getSendEmail();

	boolean getSendEmailToFile();

	String getEmailOutDir();

	boolean getSendHtml();

	boolean getOflacValidate();

	boolean getOverrideTokenAuth();

	String getOverrideTokenAuthValue();

	String getBaseUrl();

	String getEmailRegistrationUrl();

	String getEmailForogotpasswordUrl();

	long getFundingSourceDupUserMinutes();

	long getFundingSourceDupSystemMinutes();

	int getMaxFundingSourcePerUser();

	int getMaxFundingSourceActivePerUser();

	Long getMaxFundingSourceSystem();

	Long getMaxLoginsPeriodCount();
	Long getMaxFailedLoginsPeriodCount();
	Long getUserMaxLoginsPeriodCount();
	Long getUserMaxFailedLoginsPeriodCount();

	Long getUserFailLoginPeriodMinutes();
	Long getUserLoginPeriodMinutes();
	Long getFailLoginPeriodMinutes();
	Long getLoginPeriodMinutes();

	Long getBTCTickerTimeOutMinutes();

	String getFedWireRoutingNumberFile();


	Long getMaxOrderUserPeriodMinutes();
	Long getMaxOrderSystemPeriodMinutes();
	Float getMaxNumOrderUser();
	Float getMaxNumOrderSystem();
	Float getMaxOrderUser();
	Float getMaxDollarOrderSystem();

	String getBTCAddressUrl();

	String getBTCExchangeServerUrl();

	String getBTCTickersUrl();
	
}
