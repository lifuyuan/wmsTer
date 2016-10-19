package com.wmsterminal.model;

import java.util.List;

public class StokeTakingResponse {
	
	private List<StockTakings> stockTakings ;

	public void setStockTakings(List<StockTakings> stockTakings){
	this.stockTakings = stockTakings;
	}
	public List<StockTakings> getStockTakings(){
	return this.stockTakings;
	}
	
	
	public class StockTakings {
		private String stockTakingNum;

		private List<Referred_shelfs> referred_shelfs ;

		public void setStockTakingNum(String stockTakingNum){
		this.stockTakingNum = stockTakingNum;
		}
		public String getStockTakingNum(){
		return this.stockTakingNum;
		}
		public void setReferred_shelfs(List<Referred_shelfs> referred_shelfs){
		this.referred_shelfs = referred_shelfs;
		}
		public List<Referred_shelfs> getReferred_shelfs(){
		return this.referred_shelfs;
		}
		
		public class Referred_shelfs {
			private String shelfNum;

			private String status;

			public void setShelfNum(String shelfNum){
			this.shelfNum = shelfNum;
			}
			public String getShelfNum(){
			return this.shelfNum;
			}
			public void setStatus(String status){
			this.status = status;
			}
			public String getStatus(){
			return this.status;
			}

			}

	}
}
