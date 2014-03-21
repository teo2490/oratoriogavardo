package teo2490.oratoriogavardo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import utils.Converter;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.Button;

public class Pensiero extends Activity {

	public WebView wb;
	private Button bottone1;
	public String r;


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case android.R.id.home:
	         NavUtils.navigateUpTo(this,
	               new Intent(this, Home.class));
	         return true;
	      case R.id.save_settings:
	    	  //Azione
	    	  return true;
	      case R.id.share_settings:
	    	  //Azione
	    	  Converter c = new Converter();
	    	  String t = c.html2String(r);
	    	  t = c.escapeChar2specialChar(t);
	    	  Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
	    	  sharingIntent.setType("text/plain");
	    	  sharingIntent.putExtra(Intent.EXTRA_TEXT, t);
	    	  sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Il Pensiero del Giorno - Oratorio di Gavardo");
	    	  startActivity(Intent.createChooser(sharingIntent, "Condividi con.."));
	    	  return true;
	   }
	   return super.onOptionsItemSelected(item);
	}
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensiero);

        wb = (WebView) findViewById(R.id.myWebView);
        wb.setBackgroundColor(Color.parseColor("#0099FF"));
        //zoom abilitato o no
        wb.getSettings().setBuiltInZoomControls(true); 

        //default zoom. Può essere CLOSE (vicino), MEDIUM (medio, impostato di default),FAR(lontano)
        wb.getSettings().setDefaultZoom(ZoomDensity.FAR); 
        //
        WebSettings settings = wb.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        //
        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone1.setOnClickListener(new View.OnClickListener() {
        	  public void onClick(View view) { 
        		  finish();
        	  }
        	});
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
     	   getActionBar().setDisplayHomeAsUpEnabled(true);
     	   bottone1.setVisibility(View.GONE);
     	}

      //Connessione e recupero html
	    Void params = null;
	    AsyncTask<Void, Void, String> at = new DownloadFilesTask();
	    at.execute(params);
    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, String> {
  		private String html;
  		private ProgressDialog progressDialog;
  		
  		protected void onPreExecute(){
  			progressDialog = ProgressDialog.show(Pensiero.this, "", "Loading..");
  		}
  		
  		@Override
  	    protected String doInBackground(Void... params) {
  	    	  URL u = null;
  	    	    	    	  
  	    		try {
  	    			u = new URL("http://www.reginamundi.info/mobile/pensiero.asp");
  	    			//u = new URL("http://www.libreriadelsanto.it/frasegiorno.html");
  	    		} catch (MalformedURLException e) {
  	    			// TODO Auto-generated catch block
  	    			e.printStackTrace();
  	    		}
  	    			  InputStream is = null;
  	    			try {
  	    				is = u.openStream();
  	    			} catch (IOException e) {
  	    				// TODO Auto-generated catch block
  	    				e.printStackTrace();
  	    			}
  	    			try {
  	    				html = convertStreamToString(is);
  	    			} catch (IOException e) {
  	    				// TODO Auto-generated catch block
  	    				e.printStackTrace();
  	    			}
  	    			
  	    			r = new String(extract(html));
  	    			
  	    		//Sistemo caratteri speciali
  	    			Converter c = new Converter();
  	    			r = c.specialChar2escapeChar(r);
  	    			
  	    			return r;
  	    }
  		
  		  @Override
  	      protected void onPostExecute(String r) {
  			 String init = new String("<br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_pensiero.png\"  width=\"150\" height=\"40\"></div><br>");
  	         // aggiorno i campi
  	         wb.loadData(init+"<br /><br /><br /><i><font style=\"font:Tahoma\"><DIV ALIGN=\"JUSTIFY\">"+r+"</DIV></font></i>", "text/html", "UTF-8");
  	         //<img src=\"/Credo/res/drawable-hdpi/ic_vangelo.png\"/><br>
  	       progressDialog.dismiss();
  	      }
  		  
  	   
  		  public String extract(String res){
  			  String s1 = new String("<font color=\"#333333\" size=\"1\">");
  			  String s2 = new String("<font color=\"red\">");
//  			  String s1 = new String("<img src=\"/f20e/images/general/sx.png\" style=\"border:0px\"/>");
//			  String s2 = new String(" <div class=\"citazione_right\">");
  			  String ris;
  			  ris = new String(res.substring(res.indexOf(s1), res.indexOf(s2)));
  			  
  			  return ris;
  		  }
  		  
  		  private String convertStreamToString(InputStream is)
  		          throws IOException {
  		      //
  		      // To convert the InputStream to String we use the
  		      // Reader.read(char[] buffer) method. We iterate until the
  		      // Reader return -1 which means there's no more data to
  		      // read. We use the StringWriter class to produce the string.
  		      //
  		      if (is != null) {
  		          Writer writer = new StringWriter();

  		          char[] buffer = new char[1024];
  		          try {
  		              Reader reader = new BufferedReader(
  		                      new InputStreamReader(is, "UTF-8"));
  		              int n;
  		              while ((n = reader.read(buffer)) != -1) {
  		                  writer.write(buffer, 0, n);
  		              }
  		          } finally {
  		              is.close();
  		          }
  		          return writer.toString();
  		      } else {        
  		          return "";
  		      }
  		  }
  	}
    //---------------------
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pensiero, menu);
        return true;
    }
}