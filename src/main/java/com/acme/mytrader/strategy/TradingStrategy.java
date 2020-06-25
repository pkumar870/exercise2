package com.acme.mytrader.strategy;

import java.util.Timer;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

	public void start(String breachSecurity, int breachVolume, double breachPrice, PriceSource priceSource,
			ExecutionService executionService) {
		Timer timer;
		PriceListenerImpl priceListenerImpl;

		priceListenerImpl = new PriceListenerImpl(breachSecurity, breachVolume, breachPrice, priceSource,
				executionService);
		timer = new Timer();
		timer.scheduleAtFixedRate(priceListenerImpl, 0, Long.MAX_VALUE);
	}
}
