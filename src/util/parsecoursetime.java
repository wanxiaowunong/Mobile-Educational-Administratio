package util;

public class parsecoursetime {
	public static String getIdString(int a,String s) {
		int j=s.length();
		int i=0;
		String s1="";
		if(a==2) {
			switch(s.charAt(a-1)) {
			case 'һ':s1= 1+""; break;
			case '��':s1= 2+""; break;
			case '��':s1= 3+""; break;
			case '��':s1= 4+""; break;
			case '��':s1= 5+""; break;
			case '��':s1= 6+"";break;
			case '��':s1= 7+"";break;
			};
			return s1;
		}else 
			return s.charAt(a-1)+"";
		}
}
