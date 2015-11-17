package cn.com.sealer.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.sealer.utils.CheckUtil;
import cn.com.sealer.utils.MessageUtil;
import cn.com.sealer.utils.WeixinUtil;

@WebServlet("/WeixinServlet")
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WeixinServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF8");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter pw = response.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			pw.print(echostr);
		}else{
			pw.print("ji ben ok, ji xu nu li");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getFirstText());
				}else if("2".equals(content)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getSecondText());
				}else if("?".equals(content) || "？".equals(content)|| "帮助".equals(content) || "help".equalsIgnoreCase(content)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getPuzzledText());
				}else if("3".equals(content)){
					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
				}else if("4".equals(content)){
					message = MessageUtil.initImageMessage(toUserName, fromUserName);
				}else if("5".equals(content)){
					message = MessageUtil.initMusicMessage(toUserName, fromUserName);
				}else if("6".equals(content)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getTransHelpText());
				}else if("7".equals(content)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getWeatherHelpText());
				}else if(content.startsWith("翻译")){
					if(content != null){
						content = content.replaceAll("^翻译", "").trim();
						//调用translate1
						//message = MessageUtil.catText(toUserName, fromUserName, WeixinUtil.translate1(content));
						if(!("".equals(content))){
							message = MessageUtil.catText(toUserName, fromUserName, WeixinUtil.translate(content));
						}
					}
				}else if(content.endsWith("天气")){
					if(content != null){
						content = content.replaceAll("天气$", "").trim();
						//调用translate1
						//message = MessageUtil.catText(toUserName, fromUserName, WeixinUtil.translate1(content));
						if(!("".equals(content))){
							message = MessageUtil.catText(toUserName, fromUserName, WeixinUtil.getBaiduWeather(content));
						}
					}
				}else{
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getPuzzledText());
				}
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getMenuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					message = MessageUtil.catText(toUserName, fromUserName, MessageUtil.getMenuText());
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				message = MessageUtil.catText(toUserName, fromUserName, map.get("Label"));
			}
			//System.out.println(message);
			
			pw.print(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
