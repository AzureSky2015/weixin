package cn.com.sealer.test;

import cn.com.sealer.po.AccessToken;
import cn.com.sealer.utils.WeixinUtil;
import net.sf.json.JSONObject;

public class WeixinTest {
	public static void main(String[] args) {
		//获取token
//		AccessToken token = WeixinUtil.getAccessToken();
//		System.out.println(token.getToken());
//		System.out.println(token.getExpiresIn());
		
//		String token = "EL1VS530w5J6QOomQAvUfZqrs7FCQnjhPr3QXsDgdZpzzBWyvGvSg-SUIvvjHXxjnOwDkJsIZ3bEdDlDkquR4ervmD9P-UuPRn728xoqlmcCOIgABAQCW";

		
		
//		//上传图片或缩略图得到mediaId	
//		String filePath = "F:/moon.jpg";
//		try {
//			String mediaId = WeixinUtil.upload(filePath, token.getToken(), "thumb");
//			System.out.println(mediaId);
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//创建菜单
//		String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
//		int result = WeixinUtil.createMenu(token, menu);
//		if(result == 0){
//			System.out.println("创建菜单成功");
//		}else{
//			System.out.println("errcode:" + result);
//		}
//		
		
//		
//		//查询菜单结构
//		String menuInfo = WeixinUtil.queryMenu(token);
//		
//		//删除菜单
//		int result = WeixinUtil.deleteMenu(token);
//		if(result == 0){
//			System.out.println("菜单删除成功！");
//		}else{
//			System.out.println("errcode:" + result);
//		}
		
		/*
		 *测试翻译  translate1
		 */
//		String result = WeixinUtil.translate1("持久性");
//		System.out.println(result);
		
//		String result = WeixinUtil.translate("drink coffee");
//		System.out.println(result);
		
		
//		//测试 天气
//		String result = WeixinUtil.getWeather("北京");
//		System.out.println(result);
		
		
		//测试百度天气API
//		String result = WeixinUtil.getBaiduWeather("长春");
//		System.out.println(result);
	
		
		//测试归属地
//		String number = WeixinUtil.getAttrbution("18844192312");
//		System.out.println(number);
	}
	
	
}
