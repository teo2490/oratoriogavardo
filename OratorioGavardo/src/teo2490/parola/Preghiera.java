package teo2490.parola;

import teo2490.oratoriogavardo.Parola;
import teo2490.oratoriogavardo.R;
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

public class Preghiera extends Activity {

	private WebView testa;
	private Button bottone1;
	private Button bottone2;
	private Button bottone3;
	private Button bottone4;
	private Button bottone5;
	private Button bottone6;
	private Button bottone8;
	private Intent padre;
	private Intent ave;
	private Intent gloria;
	private Intent adoro;
	private Intent angelo;
	private Intent maria;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	      case android.R.id.home:
	         NavUtils.navigateUpTo(this,
	               new Intent(this, Parola.class));
	         return true;
	   }
	   return super.onOptionsItemSelected(item);
	}
	
    @SuppressLint({ "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preghiera);
    
        
        testa = (WebView) findViewById(R.id.myWebView);
        testa.setBackgroundColor(Color.parseColor("#0099FF"));
        
        String init = new String("<br /><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_preghiere.png\"  width=\"180\" height=\"60\"></div>");
        testa.loadData(init, "text/html", "UTF-8");
        
        bottone1 = (Button) findViewById(R.id.bottone2);
        bottone2 = (Button) findViewById(R.id.bottone3);
        bottone3 = (Button) findViewById(R.id.bottone4);
        bottone4 = (Button) findViewById(R.id.bottone5);
        bottone5 = (Button) findViewById(R.id.bottone6);
        bottone6 = (Button) findViewById(R.id.bottone7);
        bottone8 = (Button) findViewById(R.id.bottone8);
        bottone8.setOnClickListener(new View.OnClickListener() {
        	  public void onClick(View view) { 
        		  finish();
        	  }
        	});
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
       	   getActionBar().setDisplayHomeAsUpEnabled(true);
       	   bottone8.setVisibility(View.GONE);
       	}
        
        padre = new Intent(this, teo2490.preghiera.PadreNostro.class);
        ave = new Intent(this, teo2490.preghiera.AveMaria.class);
        gloria = new Intent(this, teo2490.preghiera.Gloria.class);
        adoro = new Intent(this, teo2490.preghiera.TiAdoro.class);
        angelo = new Intent(this, teo2490.preghiera.Angelo.class);
        maria = new Intent(this, teo2490.preghiera.Maria.class);
               
      //Listener per i pulsanti
        View.OnClickListener gestore = new View.OnClickListener() {
        	  public void onClick(View view) { 
        	    
        	    switch(view.getId()){
        	            	
        	      case R.id.bottone2:
        	    	  startActivity(padre);
        	    	  break;
        	            	    	
        	      case R.id.bottone3:
        	    	  startActivity(ave);
        	    	  break;
        	        
        	      case R.id.bottone4:
        	    	  startActivity(gloria);
        	    	  break;
        	        
        	      case R.id.bottone5:
        	    	  startActivity(angelo);
        	    	  break;
        	        
        	      case R.id.bottone6:
        	    	  startActivity(maria);
        	    	  break;
        	        
        	      case R.id.bottone7:
        	    	  startActivity(adoro);
        	    	  break;
        	    }	
        	  }
        	};
        	       
        	bottone1.setOnClickListener(gestore);
        	bottone2.setOnClickListener(gestore);
        	bottone3.setOnClickListener(gestore);
        	bottone4.setOnClickListener(gestore);
        	bottone5.setOnClickListener(gestore);
        	bottone6.setOnClickListener(gestore);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preghiera, menu);
		return true;
	}

}
