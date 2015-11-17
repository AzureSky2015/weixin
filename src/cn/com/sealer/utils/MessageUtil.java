package cn.com.sealer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import cn.com.sealer.po.Image;
import cn.com.sealer.po.ImageMessage;
import cn.com.sealer.po.Music;
import cn.com.sealer.po.MusicMessage;
import cn.com.sealer.po.NewsItem;
import cn.com.sealer.po.NewsMessage;
import cn.com.sealer.po.TextMessage;

public class MessageUtil {
	
	//常量字符串定义
	public static final String MESSAGE_TEXT = "text";//文本消息
	public static final String MESSAGE_NEWS = "news";//图文消息
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "click";
	public static final String MESSAGE_VIEW = "view";
	
	
	/**
	 * xml转化为Map
	 * @param request
	 * @return Map<String, String>
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			InputStream is = request.getInputStream();
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for(Element e : list){
				map.put(e.getName(), e.getText());
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * catText消息拼接字符串
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return 回复消息的xml格式
	 */
	public static String catText(String fromUserName, String toUserName, String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(fromUserName);
		text.setToUserName(toUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime("" + new Date().getTime());
		text.setContent(content);
		return MessageUtil.textMessageToXML(text);
	}
	
	
	
	/**
	 * 主菜单
	 * TextMessage文本消息转为 xml
	 * @param textMessage
	 * @return xml拼接的字符串
	 */
	public static String textMessageToXML(TextMessage textMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	
	/**
	 * 帮助信息
	 * @return
	 */
	public static String getMenuText(){
		StringBuilder sb = new StringBuilder();
		sb.append("欢迎关注. \n请按照一下提示操作：\n\n");
		sb.append("1.博客网址.\n");
		sb.append("2.个人主页.\n");
		sb.append("3.图文消息推送.\n");
		sb.append("4.美图推送.\n");
		sb.append("5.歌曲推荐.\n");
		sb.append("6.翻译工具帮助.\n");
		sb.append("7.天气工具帮助.\n");
		sb.append("?./帮助./help. 弹出本菜单.");
		return sb.toString();
	}
	
	/**
	 * 对6的回复
	 * @return
	 */
	public static String getTransHelpText(){
		StringBuilder sb = new StringBuilder();
		sb.append("输入 '翻译足球', 输出结果:\n中文拼音是：【zú qiú】 \n释义:\nsoccer;\nfootball;\nassociation football;\ncenter forward;\ncenter halfback;.\n");
		sb.append("\n\n");
		sb.append("输入 '翻译中国足球', 输出结果:\n Chinese football\n");
		sb.append("\n");
		sb.append("输入 '翻译coffee' 输入结果:\n 美式音标是：[ˈkɔ:fi]\n英式音标是：[ˈkɒfi]\n释义:\n[n.]	咖啡豆;咖啡粉;（一杯）咖啡;\n非正式的社交集会;");
		sb.append("\n");
		return sb.toString();
	}


	/**
	 * 对 1 的回复
	 * @return
	 */
	public static String getFirstText() {
		StringBuilder sb = new StringBuilder();
		sb.append("http://blog.csdn.net/azure_sky_2014");
		return sb.toString();
	}


	/**
	 * 对2的回复
	 * @return
	 */
	public static String getSecondText() {
		StringBuilder sb = new StringBuilder();
		sb.append("http://www.sealer.com.cn");
		return sb.toString();
	}


	/**
	 * 对？的回复  以及的非法输入的回复
	 * 帮助信息
	 * @return
	 */
	public static String getPuzzledText() {
		StringBuilder sb = new StringBuilder();
		sb.append("1.博客网址.\n");
		sb.append("2.个人主页.\n");
		sb.append("3.图文消息推送.\n");
		sb.append("4.美图推送.\n");
		sb.append("5.歌曲推荐.\n");
		sb.append("6.翻译工具帮助.\n");
		sb.append("7.天气工具帮助.\n");
		sb.append("?./帮助./help. 弹出本菜单.");
		return sb.toString();
	}
	
	
	/**
	 * 对7de 回复
	 * @return
	 */
	public static String getWeatherHelpText(){
		StringBuilder sb = new StringBuilder();
		sb.append("输入 '长春天气', 输出结果: \n");
		sb.append("长春:\n周二 11月17日 (实时：-6℃)\n温度: -2 ~ -8℃\n天气: 晴\n风力: 北风微风\nPM2.5: 64\n穿衣指数: 寒冷\n洗车指数: 较适宜\n旅游指数: 较适宜\n感冒指数: 较易发\n运动指数: 较不宜\n紫外线强度指数: 弱\n");
		return sb.toString();
	}
	
	
	
	/**
	 * newsMessage图文消息转化为XML
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXML(NewsMessage newsMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new NewsItem().getClass());
		return xStream.toXML(newsMessage);
	}
	
	
	/**
	 * 对4的回复
	 * imageMessage图片消息转化为XML
	 * @param ImageMessage
	 * @return
	 */
	public static String imageMessageToXML(ImageMessage ImageMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", ImageMessage.getClass());
		return xStream.toXML(ImageMessage);
	}
	
	/**
	 * 对5的回复
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXML(MusicMessage musicMessage){
		XStream xStream = new XStream();
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
	}
	
	
	/**
	 * 对3的回复
	 * 图文消息初始化
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static String initNewsMessage(String fromUserName, String toUserName){
		List<NewsItem> newsList = new ArrayList<NewsItem>();
		
		NewsItem newsItem = new NewsItem();
		newsItem.setTitle("猎豹者");
		newsItem.setDescription("让优秀成为一种习惯");
		newsItem.setPicUrl("http://www.sealer.com.cn/weixin/images/sl_habit.jpg");
		newsItem.setUrl("http://www.sealer.com.cn");
		newsList.add(newsItem);
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setToUserName(toUserName);
		newsMessage.setMsgType(MessageUtil.MESSAGE_NEWS);
		newsMessage.setCreateTime("" + new Date().getTime());
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		return MessageUtil.newsMessageToXML(newsMessage);
	}
	
	
	/**
	 * 初始化imagePassage消息
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static String initImageMessage(String fromUserName, String toUserName){
		String message = null;
		Image image = new Image();
		image.setMediaId("dwts5dkun-oqBddfwbwczLa2aM4r5BG2b8j_DImk0_hK_R7OBHYAjGECTmT8vtq_");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(fromUserName);
		imageMessage.setToUserName(toUserName);
		imageMessage.setCreateTime("" + new Date().getTime());
		imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE);
		imageMessage.setImage(image);
		message = imageMessageToXML(imageMessage);
		return message;
		
	}
	
	
	/**
	 * 初始化音乐消息
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public static String initMusicMessage(String fromUserName, String toUserName){
		String message = null;
		Music music = new Music();
		MusicMessage musicMessage = new MusicMessage();
		music.setThumbMediaId("OxHeeLy4IM3lOpJEMPGI5F700RZvf03T0QVIgOpFamVRVp5j5iiz7_4f1pOC8SQr");
		music.setDescription("这是一首容祖儿演唱的非常令人感动的歌曲，请用心感受.");
		music.setTitle("《小小》");
		music.setMusicUrl("http://sc.111ttt.com/up/mp3/159410/23B9A5915505615C36CC5738C98A4CD3.mp3");
		music.setHQMusicUrl("http://sc.111ttt.com/up/mp3/159410/23B9A5915505615C36CC5738C98A4CD3.mp3");
		musicMessage.setCreateTime("" + new Date().getTime());
		musicMessage.setMsgType(MessageUtil.MESSAGE_MUSIC);
		musicMessage.setFromUserName(fromUserName);
		musicMessage.setToUserName(toUserName);
		musicMessage.setMusic(music);
		message = musicMessageToXML(musicMessage);
		return message;
	}
	
	
}
