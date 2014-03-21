package teo2490.parola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

import teo2490.oratoriogavardo.Parola;
import teo2490.oratoriogavardo.R;
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
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Commento extends Activity {
	
	public WebView wb;
	private Button bottone1;
	public String r;

	/**
	 * This method sets the back-button's target. In this case it is the app's home page.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case android.R.id.home:
	         NavUtils.navigateUpTo(this,
	               new Intent(this, Parola.class));
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
	    	  sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Il Commento del Giorno - Oratorio di Gavardo");
	    	  startActivity(Intent.createChooser(sharingIntent, "Condividi con.."));
	    	  return true;
	   }
	   return super.onOptionsItemSelected(item);
	}

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commento);
        
        wb = (WebView) findViewById(R.id.myWebView);
        wb.setBackgroundColor(Color.parseColor("#0099FF"));
        
        //zoom abilitato o no
        wb.getSettings().setBuiltInZoomControls(true); 

        //default zoom. Può essere CLOSE (vicino), MEDIUM (medio, impostato di default),FAR(lontano)
        wb.getSettings().setDefaultZoom(ZoomDensity.FAR); 
        
        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone1.setOnClickListener(new View.OnClickListener() {
        	  public void onClick(View view) { 
        		  finish();
        	  }
        	});
        
        //If the back-button is supported, it is shown; otherwise the app shows a normal button "Indietro".
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      	   getActionBar().setDisplayHomeAsUpEnabled(true);
      	   bottone1.setVisibility(View.GONE);
      	}

      //Making connection and html code downloading
	    Void params = null;
	    AsyncTask<Void, Void, String> at = new DownloadFilesTask();
	    at.execute(params);
    }

    //---------------------
    private class DownloadFilesTask extends AsyncTask<Void, Void, String> {
  		private String html;
  		private GregorianCalendar gc;
  		private ProgressDialog progressDialog;
  		
  		protected void onPreExecute(){
  			//Show progress dialog
  			progressDialog = ProgressDialog.show(Commento.this, "", "Loading..");
  		}
  		
  		@Override
  	    protected String doInBackground(Void... params) {
  	    	  URL u = null;
  	    	  
  	    	  gc = new GregorianCalendar();
  	    	  	try{
  	    		try {
	    			if(gc.get(Calendar.MONTH)+1<10){
  	    				if(gc.get(Calendar.DAY_OF_MONTH)<10)	u = new URL("http://liturgia.silvestrini.org/commento/"+gc.get(Calendar.YEAR)+"-"+"0"+(gc.get(Calendar.MONTH)+1)+"-"+"0"+gc.get(Calendar.DAY_OF_MONTH)+".html");
  	    				else	u = new URL("http://liturgia.silvestrini.org/commento/"+gc.get(Calendar.YEAR)+"-"+"0"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DAY_OF_MONTH)+".html");
  	    			}
  	    			else{
  	    				if(gc.get(Calendar.DAY_OF_MONTH)<10)	u = new URL("http://liturgia.silvestrini.org/commento/"+gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+"0"+gc.get(Calendar.DAY_OF_MONTH)+".html");
  	    				else	u = new URL("http://liturgia.silvestrini.org/commento/"+gc.get(Calendar.YEAR)+"-"+(gc.get(Calendar.MONTH)+1)+"-"+gc.get(Calendar.DAY_OF_MONTH)+".html");
  	    			}
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
  	    			
  	    	  }catch(NullPointerException e){
	    			Toast.makeText(getBaseContext(), "Opzione al momento non disponibile! Ci scusiamo per il disagio.", Toast.LENGTH_LONG).show();
	    			finish();
  	    	  }
  	    	  return r;
  	    }
  		
  		  @Override
  	      protected void onPostExecute(String r) {
  			 String init = new String("<br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_commento.png\"  width=\"150\" height=\"40\"></div>");
  	         // aggiorno i campi
  	         wb.loadData(init+"<font style=\"font:Tahoma\"><P ALIGN=\"JUSTIFY\">"+r+"</P></font>", "text/html", "iso-8859-1");
  	         //<img src=\"/Credo/res/drawable-hdpi/ic_vangelo.png\"/><br>
  	       progressDialog.dismiss();
  	      }
  		  
  	   
  		  public String extract(String res){
  			  String s1 = new String("<br class=\"mybr\" />");
  			  String s2 = new String("DALLA REGOLA DEL NOSTRO SANTO PADRE BENEDETTO");
  			  String s3 = new String("I SANTI DI OGGI");
  			  String ris;
  			  if(res.indexOf(s3)>-1)	ris = new String(res.substring(res.indexOf(s1), res.indexOf(s3)));
  			  else ris = new String(res.substring(res.indexOf(s1), res.indexOf(s2)));
  			  
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
  		                      new InputStreamReader(is, "iso-8859-1"));
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.commento, menu);
		return true;
	}

}
