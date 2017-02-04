package common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 서버에서 직접 GET, POST통신을 할때 사용하는 클래스
 * <pre>
 * GetDataURLConnection url = new GetDataURLConnection(true, "");
 * StringBuilder sb = conn.requestUrl(new URL("http://xxx.xxx.204.75/pkg/Service.try"), "name", value);
 * </pre>
 * sb에 리턴된 값이 있다.
 */
public class GetDataURLConnection {
	
	private StringBuilder sb = null;
	private StringBuilder sbEl = null;
	private HttpURLConnection con = null;
	private BufferedReader in = null;
	private String line = "";
	private String host = "";
	private boolean isPOST = false;
	
	public GetDataURLConnection() {
		this(false, "");
	}
	public GetDataURLConnection(boolean isPOST, String host) {
		this.isPOST = isPOST;
		this.host = host;
	}
	
	private void init() {
		sb = null;
		sbEl = new StringBuilder();
		con = null;
		in = null;
		line = "";
	}
	
	
	public StringBuilder requestUrl(URL url) {
		return requestUrl(url,"","");
	}
	
	public StringBuilder requestUrl(URL url, String names, String param) {
		String[] tmp1 = null;
		String[] tmp2 = null;
		if (names != null && !names.equals("") ) {
			tmp1 = new String[1];
			tmp1[0] = names;
		}
		if (param != null && !param.equals("") ) {
			tmp2 = new String[1];
			tmp2[0] = param;
		}
		return requestUrl(url,tmp1,tmp2);
	}

	public StringBuilder requestUrl(URL url, String []names, String[] param) {
		init();
		String data = "";
		try {
			if (names != null) {
				if (names.length != param.length) throw new IOException("param length is not equals , names: " + names.length +", params :" + param.length);
				for (int i=0; i<names.length; i++) {
					if (i>0) data += "&";
					data += URLEncoder.encode(names[i], "UTF-8") + "=" + URLEncoder.encode(param[i], "UTF-8");
				}
			}
			
			sb = new StringBuilder();
			if(!isPOST) {
				sbEl = getUrlContents(url);
			} else {
				sbEl = postUrlContents(url, data);
			}
			
			sb.append(sbEl);
			sbEl.setLength(0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null ) in.close();
			} catch (IOException e) {}
			con = null;
		}
		
		return sb;
	}

	private StringBuilder postUrlContents(URL url, String param) throws IOException {
		sbEl.setLength(0);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		con.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Pragma", "no-cache");
		con.setRequestProperty("User-Agent", "Hanasam Server direct accept");
		if (!host.equals("")) {
			con.setRequestProperty("Host", host);
		}
		
		if (!param.equals("")) {
			con.setDoOutput(true);
			con.setDoInput(true);

			con.setRequestMethod("POST");
			DataOutputStream writer = new DataOutputStream(con.getOutputStream());
			writer.writeBytes(param);
			writer.flush();
		}
//System.out.println("GetDataURLConnection.postUrlContents - response code : " + con.getResponseCode() );
		
		if (con.getContentType().toUpperCase().indexOf("UTF-8") > -1) {
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		} else {
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
		}
		line = "";
		while ((line = in.readLine()) != null) {
			sbEl.append(line);
		}

		return sbEl;
	}
	
	private StringBuilder getUrlContents(URL url) throws IOException {
		sbEl.setLength(0);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		con.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Pragma", "no-cache");
		con.setRequestProperty("User-Agent", "Hanasam Server direct accept");
		if (!host.equals("")) {
			con.setRequestProperty("Host", host);
		}
System.out.println("GetDataURLConnection.getUrlContents - response code : " + con.getResponseCode() );

		if (con.getContentType().toUpperCase().indexOf("UTF-8") > -1) {
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		} else {
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
		}
		line = "";
		while ((line = in.readLine()) != null) {
			sbEl.append(line);
		}

		return sbEl;
	}
	@SuppressWarnings("unused")
	private String cutString(String body, String startStr, String endStr, int startAddNo, int endAddNo) {
		String rslt = "";
		int startStrIdx = 0;
		int endStrIdx = 0;
		if (!HNTTrans.trim(startStr).equals("")) {
			startStrIdx = body.indexOf(startStr);
			if (startStrIdx < 0) return rslt;
			
		}
		
		if (!HNTTrans.trim(endStr).equals("")) {
			endStrIdx = body.indexOf(endStr);
			if (endStrIdx < 0) return rslt;
		}
		rslt = body.substring(startStrIdx+startAddNo, endStrIdx+endAddNo);
		
		return rslt;
	}
	
	public List<String> getValueFromSimpleXML(String tagName, String org) {
		String tag = "<"+tagName+">[^\\<]*</"+tagName+">";
		Pattern p = Pattern.compile(tag, Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE|Pattern.MULTILINE);
		Matcher m = p.matcher(org);
		List<String> rslt = new ArrayList<String>();
		while(m.find()){
			String tmp = m.group();
			tmp = tmp.replaceAll("<[/]?"+tagName+">", "");
			rslt.add(tmp);
		}
		return rslt;
	}
}
