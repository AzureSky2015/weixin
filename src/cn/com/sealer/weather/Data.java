package cn.com.sealer.weather;

public class Data {
	private String wendu;
	private String ganmao;
	private Forecast[] forecast;
	private Yesterday yesterday;
	private String aqi;
	private String city;
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public Forecast[] getForecast() {
		return forecast;
	}
	public void setForecast(Forecast[] forecast) {
		this.forecast = forecast;
	}
	public Yesterday getYesterday() {
		return yesterday;
	}
	public void setYesterday(Yesterday yesterday) {
		this.yesterday = yesterday;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
