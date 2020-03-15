package com.hrxb.ssm.vo;

import com.hrxb.ssm.entity.Items;

public class ItemsVO extends Items{
	
	private Integer pricelow;//最低价格
	private Integer pricehight;//最高价格
	
	
	public Integer getPricelow() {
		return pricelow;
	}
	public void setPricelow(Integer pricelow) {
		this.pricelow = pricelow;
	}
	public Integer getPricehight() {
		return pricehight;
	}
	public void setPricehight(Integer pricehight) {
		this.pricehight = pricehight;
	}
	
	
	

}
