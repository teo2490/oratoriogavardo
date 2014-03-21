package teo2490.oratoriogavardo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity {

	private WebView testa;
	private Button bottone1;
	private Button bottone2;
	private Button bottone3;
	private Intent news;
	private Intent parola;
	private Intent pensiero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//Controllo connessione ON
		Networking nw = new Networking();
        if(!nw.isNetworkAvailable(getApplicationContext())){
            Toast.makeText(this, "L'applicazione necessita di una connessione dati!", Toast.LENGTH_LONG).show();
            finish();
        }
        
        testa = (WebView) findViewById(R.id.myWebView);
        testa.setBackgroundColor(Color.parseColor("#0099FF"));
        
        String init = new String("<br><br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_oratorio.png\"  width=\"250\" height=\"80\"><br /><br /><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_gavardo.png\"  width=\"200\" height=\"80\"></div><br>");
        testa.loadData(init, "text/html", "UTF-8");
        
        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone2 = (Button) findViewById(R.id.bottone2);
        bottone3 = (Button) findViewById(R.id.bottone3);
        
        news = new Intent(this, News.class);
        parola = new Intent(this, Parola.class);
        pensiero = new Intent(this, Pensiero.class);
        
      //Listener per i pulsanti
        View.OnClickListener gestore = new View.OnClickListener() {
        	  public void onClick(View view) { 
        	    
        	    switch(view.getId()){
        	            	
        	      case R.id.bottone1:
        	    	  	startActivity(news);
        	    	  	break;
        	            	    	
        	      case R.id.bottone2:
        	    		startActivity(parola);
        	    		break;
        	    		
        	      case R.id.bottone3:
      	    		startActivity(pensiero);
      	    		break;
        	    }	
        	  }
        	};
        	       
        	bottone1.setOnClickListener(gestore);
        	bottone2.setOnClickListener(gestore);
        	bottone3.setOnClickListener(gestore);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case R.id.web_settings:
	    	  //Azione
	    	  Uri uriUrl = Uri.parse("http://www.oratoriogavardo.it");
	    	  Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
	    	  startActivity(launchBrowser);
	    	  return true;
	   }
	   return super.onOptionsItemSelected(item);
	}

    public class Networking {
   	 /*
   	 *@return boolean return true if the application can access the internet
   	 */
   	 public boolean isNetworkAvailable(Context context) {
   	     ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
   	     if (connectivity != null) {
   	        NetworkInfo[] info = connectivity.getAllNetworkInfo();
   	        if (info != null) {
   	           for (int i = 0; i < info.length; i++) {
   	              if (info[i].getState() == NetworkInfo.State.CONNECTED) {
   	                 return true;
   	              }
   	           }
   	        }
   	     }
   	     return false;
   	  }
   	}
}
