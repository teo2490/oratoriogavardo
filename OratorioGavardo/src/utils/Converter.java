package utils;

public class Converter {

	public String specialChar2escapeChar(String r){
			r = r.replaceAll("�", "&agrave;");
		    r = r.replaceAll("�", "&aacute;");
		    r = r.replaceAll("�", "&Agrave;");
		    r = r.replaceAll("�", "&Aacute;");
		    r = r.replaceAll("�", "&egrave;");
		    r = r.replaceAll("�", "&eacute;");
		    r = r.replaceAll("�", "&Egrave;");
		    r = r.replaceAll("�", "&Eacute;");
		    r = r.replaceAll("�", "&igrave;");
		    r = r.replaceAll("�", "&iacute;");
		    r = r.replaceAll("�", "&Igrave;");
		    r = r.replaceAll("�", "&Iacute;");
		    r = r.replaceAll("�", "&ograve;");
		    r = r.replaceAll("�", "&oacute;");
		    r = r.replaceAll("�", "&Ograve;");
		    r = r.replaceAll("�", "&Oacute;");
		    r = r.replaceAll("�", "&ugrave;");
		    r = r.replaceAll("�", "&uacute;");
		    r = r.replaceAll("�", "&Ugrave;");
		    r = r.replaceAll("�", "&Uacute;");
		    r = r.replaceAll("\"", "&quot;");
		    r = r.replaceAll("�", "&lsquo;");
		    r = r.replaceAll("�", "&rsquo;");
		    r = r.replaceAll("'", "&rsquo;");
		    r = r.replaceAll("�", "&ldquo;");
		 	r = r.replaceAll("�", "&rdquo;");
		 	r = r.replaceAll("�", "&raquo;");
		 	r = r.replaceAll("�", "&laquo;");
		 	//r = r.replaceAll(" ", "&nbsp;");
		 	
		 	
		 	return r;
	}
	
	public String escapeChar2specialChar(String r){
		r = r.replaceAll("&agrave;", "�");
	    r = r.replaceAll("&aacute;", "�");
	    r = r.replaceAll("&Agrave;", "�");
	    r = r.replaceAll("&Aacute;", "�");
	    r = r.replaceAll("&egrave;", "�");
	    r = r.replaceAll("&eacute;", "�");
	    r = r.replaceAll("&Egrave;", "�");
	    r = r.replaceAll("&Eacute;", "�");
	    r = r.replaceAll("&igrave;", "�");
	    r = r.replaceAll("&iacute;", "�");
	    r = r.replaceAll("&Igrave;", "�");
	    r = r.replaceAll("&Iacute;", "�");
	    r = r.replaceAll("&ograve;", "�");
	    r = r.replaceAll("&oacute;", "�");
	    r = r.replaceAll("&Ograve;", "�");
	    r = r.replaceAll("&Oacute;", "�");
	    r = r.replaceAll("&ugrave;", "�");
	    r = r.replaceAll("&uacute;", "�");
	    r = r.replaceAll("&Ugrave;", "�");
	    r = r.replaceAll("&Uacute;", "�");
	    r = r.replaceAll("&quot;", "\"");
	    r = r.replaceAll("&lsquo;", "�");
	    r = r.replaceAll("&rsquo;", "�");
	    r = r.replaceAll("&rsquo;", "'");
	    r = r.replaceAll("&ldquo;", "�");
	 	r = r.replaceAll("&rdquo;", "�");
	 	r = r.replaceAll("&raquo;", "�");
	 	r = r.replaceAll("&laquo;", "�");
	 	r = r.replaceAll("&nbsp;", " ");
	 	r = r.replaceAll("&#039;", "'");
	 	
	 	return r;
	}
	
	public String html2String(String r){
		r = r.replaceAll("<[^>]*>", "");
		
		return r;
	}
}
