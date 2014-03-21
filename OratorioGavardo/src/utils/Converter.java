package utils;

public class Converter {

	public String specialChar2escapeChar(String r){
			r = r.replaceAll("à", "&agrave;");
		    r = r.replaceAll("á", "&aacute;");
		    r = r.replaceAll("À", "&Agrave;");
		    r = r.replaceAll("Á", "&Aacute;");
		    r = r.replaceAll("è", "&egrave;");
		    r = r.replaceAll("é", "&eacute;");
		    r = r.replaceAll("È", "&Egrave;");
		    r = r.replaceAll("É", "&Eacute;");
		    r = r.replaceAll("ì", "&igrave;");
		    r = r.replaceAll("í", "&iacute;");
		    r = r.replaceAll("Ì", "&Igrave;");
		    r = r.replaceAll("Í", "&Iacute;");
		    r = r.replaceAll("ò", "&ograve;");
		    r = r.replaceAll("ó", "&oacute;");
		    r = r.replaceAll("Ò", "&Ograve;");
		    r = r.replaceAll("Ó", "&Oacute;");
		    r = r.replaceAll("ù", "&ugrave;");
		    r = r.replaceAll("ú", "&uacute;");
		    r = r.replaceAll("Ù", "&Ugrave;");
		    r = r.replaceAll("Ú", "&Uacute;");
		    r = r.replaceAll("\"", "&quot;");
		    r = r.replaceAll("‘", "&lsquo;");
		    r = r.replaceAll("’", "&rsquo;");
		    r = r.replaceAll("'", "&rsquo;");
		    r = r.replaceAll("“", "&ldquo;");
		 	r = r.replaceAll("”", "&rdquo;");
		 	r = r.replaceAll("»", "&raquo;");
		 	r = r.replaceAll("«", "&laquo;");
		 	//r = r.replaceAll(" ", "&nbsp;");
		 	
		 	
		 	return r;
	}
	
	public String escapeChar2specialChar(String r){
		r = r.replaceAll("&agrave;", "à");
	    r = r.replaceAll("&aacute;", "á");
	    r = r.replaceAll("&Agrave;", "À");
	    r = r.replaceAll("&Aacute;", "Á");
	    r = r.replaceAll("&egrave;", "è");
	    r = r.replaceAll("&eacute;", "é");
	    r = r.replaceAll("&Egrave;", "È");
	    r = r.replaceAll("&Eacute;", "É");
	    r = r.replaceAll("&igrave;", "ì");
	    r = r.replaceAll("&iacute;", "í");
	    r = r.replaceAll("&Igrave;", "Ì");
	    r = r.replaceAll("&Iacute;", "Í");
	    r = r.replaceAll("&ograve;", "ò");
	    r = r.replaceAll("&oacute;", "ó");
	    r = r.replaceAll("&Ograve;", "Ò");
	    r = r.replaceAll("&Oacute;", "Ó");
	    r = r.replaceAll("&ugrave;", "ù");
	    r = r.replaceAll("&uacute;", "ú");
	    r = r.replaceAll("&Ugrave;", "Ù");
	    r = r.replaceAll("&Uacute;", "Ú");
	    r = r.replaceAll("&quot;", "\"");
	    r = r.replaceAll("&lsquo;", "‘");
	    r = r.replaceAll("&rsquo;", "’");
	    r = r.replaceAll("&rsquo;", "'");
	    r = r.replaceAll("&ldquo;", "“");
	 	r = r.replaceAll("&rdquo;", "”");
	 	r = r.replaceAll("&raquo;", "»");
	 	r = r.replaceAll("&laquo;", "«");
	 	r = r.replaceAll("&nbsp;", " ");
	 	r = r.replaceAll("&#039;", "'");
	 	
	 	return r;
	}
	
	public String html2String(String r){
		r = r.replaceAll("<[^>]*>", "");
		
		return r;
	}
}
