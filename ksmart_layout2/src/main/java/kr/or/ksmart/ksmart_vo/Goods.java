package kr.or.ksmart.ksmart_vo;

public class Goods {
	private String goodsCode;
	private String goodsUserId;
	private String goodsName;
	private String goodsCate;
	private String goodsPrice;
	private String goodsColor;
	private String goodsSize;
	private String goodsDate;
	private String goodsDesc;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getgoodsUserId() {
		return goodsUserId;
	}
	public void setGoodsUserId(String goodsUserId) {
		this.goodsUserId = goodsUserId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsCate() {
		return goodsCate;
	}
	public void setGoodsCate(String goodsCate) {
		this.goodsCate = goodsCate;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsColor() {
		return goodsColor;
	}
	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}
	public String getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}
	public String getGoodsDate() {
		return goodsDate;
	}
	public void setGoodsDate(String goodsDate) {
		this.goodsDate = goodsDate;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}	
	@Override
	public String toString() {
		return "Goods [goodsCode="+ goodsCode +", goodsUserId="+goodsUserId+", goodsName="+goodsName
				+", goodsCate="+goodsCate+", goodsPrice="+goodsPrice+", goodsColor="+goodsColor
				+", goodsSize="+goodsSize+", goodsDate="+goodsDate+", goodsDesc="+goodsDesc+ "]";
	}
}

