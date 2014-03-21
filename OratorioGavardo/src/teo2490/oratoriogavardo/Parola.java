package teo2490.oratoriogavardo;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

@SuppressLint("NewApi")
public class Parola extends Activity {


	private WebView testa;
	private Button bottone1;
	private Button bottone2;
	private Button bottone3;
	private Button bottone4;
	private Button bottone5;
	private Intent vangelo;
	private Intent preghiera;
	private Intent santo;
	private Intent commento;
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case android.R.id.home:
	         NavUtils.navigateUpTo(this,
	               new Intent(this, Home.class));
	         return true;
	      case R.id.web_settings:
	    	  //Azione
	    	  Uri uriUrl = Uri.parse("http://www.oratoriogavardo.it");
	    	  Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
	    	  startActivity(launchBrowser);
	    	  return true;
	   }
	   return super.onOptionsItemSelected(item);
	}
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parola);
        
        testa = (WebView) findViewById(R.id.myWebView);
        testa.setBackgroundColor(Color.parseColor("#0099FF"));
        
        String init = new String("<br><br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_parola.png\"  width=\"200\" height=\"80\"><br /><br /><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_giorno.png\"  width=\"200\" height=\"80\"></div><br>");
        testa.loadData(init, "text/html", "UTF-8");
        
        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone2 = (Button) findViewById(R.id.bottone2);
        bottone3 = (Button) findViewById(R.id.bottone3);
        bottone4 = (Button) findViewById(R.id.bottone4);
        bottone5 = (Button) findViewById(R.id.bottone5);
        
        vangelo = new Intent(this, teo2490.parola.Vangelo.class);
        preghiera = new Intent(this, teo2490.parola.Preghiera.class);
        santo = new Intent(this, teo2490.parola.Santo.class);
        commento = new Intent(this, teo2490.parola.Commento.class);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      	   getActionBar().setDisplayHomeAsUpEnabled(true);
      	   bottone5.setVisibility(View.GONE);
      	}
               
      //Listener per i pulsanti
        View.OnClickListener gestore = new View.OnClickListener() {
        	  public void onClick(View view) { 
        	    
        	    switch(view.getId()){
        	            	
        	      case R.id.bottone1:
        	    	  startActivity(vangelo);
        	    	  break;
        	            	    	
        	      case R.id.bottone2:
        	    	  startActivity(preghiera);
        	    	  break;
        	        
        	      case R.id.bottone3:
        	    	  startActivity(santo);
        	    	  break;
        	        
        	      case R.id.bottone4:
        	    	  startActivity(commento);
        	    	  break;
        	    	  
        	      case R.id.bottone5:
        	    	  finish();
        	    	  break;
        	    }	
        	  }
        	};
        	       
        	bottone1.setOnClickListener(gestore);
        	bottone2.setOnClickListener(gestore);
        	bottone3.setOnClickListener(gestore);
        	bottone4.setOnClickListener(gestore);
        	bottone5.setOnClickListener(gestore);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parola, menu);
		return true;
	}

}
