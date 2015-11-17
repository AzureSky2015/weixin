package cn.com.sealer.weather;

public class WeatherResult {
	private String desc;
	private int status;
	private cn.com.sealer.weather.Data data;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Data getData() {
		return data;
	}
	public void setData(cn.com.sealer.weather.Data data) {
		this.data = data;
	}
	
	
}
