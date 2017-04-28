package iib.utility.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	public static Long getNanosSinceEpoch( String date )  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return dt.getTime() * 1000000;
	}
}
