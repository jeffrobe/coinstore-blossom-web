package com.coin.web.service;

import java.math.BigDecimal;

import com.coin.model.Fundingsource;
import com.coin.model.Message;

 
public interface FundingsourceService {
	Message newSource(Fundingsource fundingsource);
	Message createTrialDeposit(Long fundingsourceId);
	Message confirmTrialDeposit(Long trialdepositId, BigDecimal deposit1, BigDecimal deposit2);
	Message updateSource(Fundingsource fundingsource);
	Message validatefundingSource(Fundingsource fundingsource);
	Message testDuplicateSource(Long userId, String routing, String account);
	Message testMaxSource(Long userId);
}
