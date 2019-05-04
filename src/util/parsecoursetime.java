package util;

public class parsecoursetime {
	public static String getIdString(int a,String s) {
		int j=s.length();
		int i=0;
		String s1="";
		if(a==2) {
			switch(s.charAt(a-1)) {
			case '一':s1= 1+""; break;
			case '二':s1= 2+""; break;
			case '三':s1= 3+""; break;
			case '四':s1= 4+""; break;
			case '五':s1= 5+""; break;
			case '六':s1= 6+"";break;
			case '日':s1= 7+"";break;
			};
			return s1;
		}else 
			return s.charAt(a-1)+"";
		}
}
