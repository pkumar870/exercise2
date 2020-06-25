package com.acme.mytrader.price;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl extends TimerTask implements PriceListener {

	private int breachVolume;
	private String breachSecurity;
	private double breachPrice;
	
	private AtomicBoolean shouldBuy = new AtomicBoolean();

	private PriceSource priceSource;
	private ExecutionService executionService;

	public PriceListenerImpl(String breachSecurity,  int breachVolume, double breachPrice, PriceSource priceSource,
			ExecutionService executionService) {
		super();
		this.breachSecurity = breachSecurity;
		this.breachVolume = breachVolume;
		this.breachPrice = breachPrice;
		this.priceSource = priceSource;
		this.executionService = executionService;
	}

	@Override
	public void priceUpdate(String security, double price) {

		if (price < breachPrice) {
			shouldBuy.set(true);
		}
		priceSource.addPriceListener(this);
		// TODO add reset of the implementation
	}

	@Override
	public void run() {
		if (shouldBuy.get()) {
			executionService.buy(breachSecurity, breachPrice, breachVolume);
		}
	}

}
