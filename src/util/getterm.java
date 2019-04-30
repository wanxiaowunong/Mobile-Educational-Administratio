package util;
import java.util.Calendar;

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

}
