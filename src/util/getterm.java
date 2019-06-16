package util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getterm {
	public void getterm() {}
	public static String getTerms() {
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		String term="";
		if(month>=9&&month<=12)
			term=""+year+'-'+(year+1)+1;
		else
			term=""+(year-1)+'-'+year+2;
		return term;
		
	}
	public static  String getdate(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);

	}

}
