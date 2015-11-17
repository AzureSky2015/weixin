package cn.com.sealer.utils;

import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.com.sealer.baiduweather.BaiduWeatherResult;
import cn.com.sealer.baiduweather.Index;
import cn.com.sealer.baiduweather.Results;
import cn.com.sealer.baiduweather.WeatherData;
import cn.com.sealer.menu.Button;
import cn.com.sealer.menu.ClickButton;
import cn.com.sealer.menu.Menu;
import cn.com.sealer.menu.ViewButton;
import cn.com.sealer.po.AccessToken;
import cn.com.sealer.trans.Data;
import cn.com.sealer.trans.Parts;
import cn.com.sealer.trans.Symbols;
import cn.com.sealer.trans.TransResult;
import cn.com.sealer.weather.Forecast;
import cn.com.sealer.weather.WeatherResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeixinUtil {
	public static final String APPID = "wx10ad2fe5e5d04067"; 
	public static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d"; 
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	public static final String BUTTON_TYPE_CLICK = "click";
	public static final String BUTTON_TYPE_VIEW = "view";
	public static final String BUTTON_TYPE_SCANCODE_PUSH = "scancode_push";
	public static final String BUTTON_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String BUTTON_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
	public static final String BUTTON_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String BUTTON_TYPE_PIC_WEIXIN = "pic_weixin";
	public static final String BUTTON_TYPE_LOCATION_SELECT = "location_select";
	public static final String BUTTON_TYPE_MEDIA_ID = "media_id";
	public static final String BUTTON_TYPE_VIEW_LIMITED = "view_limited";
	
	//{{这是一组的  翻译
	public static final String TRANS_APP_ID = "20151115000005506";
	public static final String TRANS_KEY = "WmUZOHfiP1vit84crLsu";
	public static final String TRANS_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate?q=QUERY&appid=TRANS_APP_ID&salt=TRANS_SALT&from=FROM&to=TO&sign=SIGN";
	public static final String TRANS_SALT = "1435660288";
	//}}
	public static final String TRANS_URL_FULL = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=YourApiKey&q=SOURCE&from=auto&to=auto";
	public static final String TRANS_URL_DICT = "http://openapi.baidu.com/public/2.0/translate/dict/simple?client_id=YourApiKey&q=SOURCE&from=auto&to=auto";
	public static final String MY_API_KEY = "B13Gg19E5gad0xUqXXV4nWSu";
	
	
	public static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?city=CITY";
	public static final String BAIDU_WEATHER_URL = "http://api.map.baidu.com/telematics/v3/weather?location=CITY&output=json&ak=MYKEY";
	public static final String MY_BAIDU_WEATHER_KEY = "DrNbLx4ZFRg8KbLoXhkCZeL9";
	
	
	//手机号码归属地查询
//	public static final String PHONE_ATTRIBUTION_API_KEY = "217ca7befcf3223ae5303286d12ce997";
//	public static final String PHONE_ATTRIBUTION_URL = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone?tel=PHONE_NUMBER&ak=MYKEY";
	public static final String PHONE_ATTRIBUTION_URL = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=PHONE_NUMBER";
	
	
	//百度音乐
	public static final String BAIDU_MUSIC = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=SONG_NAME$$";
	/**
	 * doGetStr
	 * 作用：get方式获取json数据
	 * @param url
	 * @return
	 */
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity, "UTF-8");
//				System.out.println(result);
				jsonObject = JSONObject.fromObject(result);
				
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	
	
	/**
	 * post请求
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static JSONObject doPostStr(String url, String outStr){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			
			HttpResponse response = httpClient.execute(httpPost);
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				jsonObject = JSONObject.fromObject(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return jsonObject;
	}
	
	
	
	/**
	 * 获取token
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
			
		}
		return token;
	}
	
	
	/**
	 * 文件上传
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		//System.out.println(jsonObj);
		String typeName = "media_id";
		if(!"image".equals(type)){
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}
	
	
	/**
	 * 初始化一个menu
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("点赞");
		button11.setType(BUTTON_TYPE_CLICK);
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("sealer网站");
		button21.setType(BUTTON_TYPE_VIEW);
		button21.setUrl("http://www.sealer.com.cn");
		
		ClickButton button31 = new ClickButton();
		button31.setName("扫一扫");
		button31.setType(BUTTON_TYPE_SCANCODE_PUSH);
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("我的位置");
		button32.setType(BUTTON_TYPE_LOCATION_SELECT);
		button32.setKey("32");
		
		Button button3 = new Button();
		button3.setName("附加功能");
		button3.setSub_button(new Button[]{button31, button32});
		
		menu.setButton(new Button[]{button11, button21, button3});
		
		return menu;
	}
	
	
	/**
	 * 提交请求，创建menu
	 * @param token
	 * @param menu
	 * @return
	 */
	public static int createMenu(String token, String menu){
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		
		return result;
	}
	
	
	/**
	 * 查询menu结构
	 * @param token
	 * @return
	 */
	public static String queryMenu(String token){
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject.toString();
	}

	/**
	 * 删除菜单
	 * @param token
	 * @return
	 */
	public static int deleteMenu(String token){
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		int result = jsonObject.getInt("errcode");
		return result;
	}
	
	
	/**
	 * translate实现版本1----自己摸索版本Vip版本
	 * @param plainText
	 * @return
	 */
	public static String translateVip(String plainText){
		StringBuilder sb = new StringBuilder();
		sb.append(TRANS_APP_ID).append(plainText).append(TRANS_SALT).append(TRANS_KEY);
		String sign = EncryptUtil.getMD5(sb.toString());
		String url = TRANS_URL.replace("QUERY", plainText).replace("TRANS_APP_ID", TRANS_APP_ID).replace("TRANS_SALT", TRANS_SALT).replace("FROM", "auto").replace("TO", "auto").replace("SIGN", sign);
		JSONObject jsonObject = doGetStr(url);
		JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("trans_result"));
		return jsonArray.getJSONObject(0).getString("dst");
	}
	
	/**
	 * 翻译词组句子  非VIP
	 * @param plainText
	 * @return
	 */
	public static String translateFull(String plainText){
		
		String url = null;
		String dest = null;
		try {
			url = TRANS_URL_FULL.replace("SOURCE", URLEncoder.encode(plainText,"UTF-8")).replace("YourApiKey", MY_API_KEY);
			JSONObject jsonObject = doGetStr(url);
			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("trans_result"));
			dest = jsonArray.getJSONObject(0).getString("dst");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	
	
	
	/**
	 * translate实现版本2----有音标注释全版本  仅翻译单词
	 * @param plainText
	 * @return
	 */
	public static String translate(String plainText){
		String url = null;
		StringBuffer dst = null;
		try {
			
			url = TRANS_URL_DICT.replace("SOURCE", plainText).replace("YourApiKey", MY_API_KEY);//注意String不可变
//			System.out.println(url);
			JSONObject jsonObject = doGetStr(url);
			String errno = jsonObject.getString("errno");
			Object obj = jsonObject.get("data");
//			String from = jsonObject.getString("from");
//			String to = jsonObject.getString("to");
//			System.out.println(errno + from + ":" + to);
//			System.out.println(plainText);
			dst = new StringBuffer();
			if("0".equals(errno)&&!"[]".equals(obj.toString())){
				TransResult transResult = (TransResult)JSONObject.toBean(jsonObject, TransResult.class);
				Data data = transResult.getData();
				
//				System.out.println(data.getWord_name());
				
				Symbols symbols = data.getSymbols()[0];
//				System.out.println(symbols);
				String phzh = (symbols.getPh_zh() == null || "".equals(symbols.getPh_zh())) ? "" : ("中文拼音是：【" + symbols.getPh_zh() + "】\n");
				String pham = (symbols.getPh_am() == null || "".equals(symbols.getPh_am())) ? "" : ("美式音标是：[" + symbols.getPh_am() + "]\n");
				String phen = (symbols.getPh_en() == null || "".equals(symbols.getPh_en())) ? "" : ("英式音标是：[" + symbols.getPh_en() + "]\n");
				dst.append(phzh + pham + phen + "释义:\n");
				
//				System.out.println(dst.toString());
				Parts[] parts = symbols.getParts();
				String pat = null;
				for(Parts part : parts){
					pat = ((part.getPart()!=null)&&!"".equals(part.getPart())) ? "[" + part.getPart() + "]" : "";
					dst.append(pat + "\t");
					String[] meanings = part.getMeans();
					for(String meaning : meanings){
						dst.append(meaning + ";");
					}
					dst.append("\n");
				}
				
				
				
				
			}else{
				dst.append(translateFull(plainText));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dst.toString();
	}
	
	
	
	
	/**
	 * 获取天气  有bug 调试中
	 * @param city
	 * @return
	 */
	
	//{{未知的API天气查询
	public static String getWeather(String city){
		String url = null;
		try {
			url = WEATHER_URL.replace("CITY", URLEncoder.encode(city, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		System.out.println(url);
		JSONObject jsonObject = doGetStr(url);
		System.out.println(jsonObject);
		int status = jsonObject.getInt("status");
		System.out.println(status);
		StringBuffer sb = new StringBuffer();
		if(status == 1000){
			WeatherResult weatherResult = (WeatherResult)JSONObject.toBean(jsonObject,WeatherResult.class);
			cn.com.sealer.weather.Data data = weatherResult.getData();
			sb.append(city + "天气:\n");
			sb.append("温度:" + data.getWendu() + "度\n");
			sb.append("感冒指数:" + data.getGanmao() + "\n");
			sb.append("空气质量指数:" + data.getAqi() + "\n");
			Forecast[] forecasts = data.getForecast();
			for(Forecast forecast : forecasts){
				sb.append("日期:" + forecast.getDate() + "\n");
				sb.append("风向:" + forecast.getFengxiang() + "\n");
				sb.append("风力:" + forecast.getFengli() + "\n");
				sb.append("高温:" + forecast.getHigh() + "\n");
				sb.append("低温:" + forecast.getLow() + "\n");
				sb.append("天气:" + forecast.getType() + "\n");
				sb.append("\n\n");
				
			}
		}
		
		return sb.toString();
	}
	//}}
	
	
	public static String getBaiduWeather(String city){
		String url = null;
		StringBuffer sb = new StringBuffer();
		sb.append(city + ":\n");
		url = BAIDU_WEATHER_URL.replace("MYKEY", MY_BAIDU_WEATHER_KEY).replace("CITY", city);
//		System.out.println(url);
		JSONObject jsonObject = doGetStr(url);
		String status = jsonObject.getString("status");
//		System.out.println(status);
//		System.out.println(jsonObject);
		if("success".equals(status)){
			BaiduWeatherResult baiduWeatherResult = (BaiduWeatherResult)JSONObject.toBean(jsonObject, BaiduWeatherResult.class);
			Results result = baiduWeatherResult.getResults()[0];
			WeatherData weatherData = result.getWeather_data()[0];//只获取当天数据  0代表当天
			sb.append(weatherData.getDate() + "\n");
			sb.append("温度: " + weatherData.getTemperature() + "\n");
			sb.append("天气: " + weatherData.getWeather() + "\n");
			sb.append("风力: " + weatherData.getWind() + "\n");
			String pm25 = result.getPm25();
			sb.append("PM2.5: " + pm25 + "\n");
			Index[] index = result.getIndex();
			for(Index i : index){
				sb.append(i.getTipt() + ": " + i.getZs() + "\n");
			}
			
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 获得归属地
	 */
//	public static String getAttrbution(String phoneNumber){
//		String url = null;
//		url = PHONE_ATTRIBUTION_URL.replace("PHONE_NUMBER", phoneNumber);
//		StringBuffer sb = new StringBuffer();
//		JSONObject jsonObject = doGetStr(url);
//		System.out.println(jsonObject);
//		return sb.toString();
//		
//	}
}
