package com.wicket_projects.shoppinglist.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime {
	
	public static String getCurrentTime(){
		String rtv = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		rtv = sdf.format(Calendar.getInstance().getTime());
		
		return rtv;
	}
}
