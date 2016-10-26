package com.supcon.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtil {
	
	private static Properties properties = null;
	
	public static Properties getProperties(){
		if(properties == null){
			properties = new Properties();
			InputStream in = null;
			try {
				in = CommonUtil.class.getResourceAsStream("/context.properties");
				properties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return properties;
	}
}
