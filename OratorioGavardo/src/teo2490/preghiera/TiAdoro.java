package teo2490.preghiera;

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

public class TiAdoro extends Activity {

	public WebView wb;
	private Button bottone1;
	
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
        setContentView(R.layout.activity_ti_adoro);
        
        wb = (WebView) findViewById(R.id.myWebView);
        wb.setBackgroundColor(Color.parseColor("#0099FF"));
        bottone1 = (Button) findViewById(R.id.bottone1);
        
        
        String r1 = new String("<br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_adoro.png\"  width=\"150\" height=\"40\"></div><br><font face=\"Tahoma\"><u>AL MATTINO</u></font><br><div align=\"center\"><font face=\"Tahoma\"><b><i><br />Ti adoro, Dio mio, <br />e ti amo con tutto il cuore. <br />Ti ringrazio di avermi creato, fatto cristiano<br /> e conservato in questa notte.<br />Ti offro le azioni della giornata,<br /> f&agrave che siano tutte secondo la tua volont&agrave<br /> e per la maggior Tua gloria.<br />Preservami dal peccato e da ogni male.<br />La Tua grazia sia sempre con me<br /> e con tutti i miei cari.<br /><br /> Amen<br /><br /></i></b></font></div>");
        String r2 = new String("<br><br><font face=\"Tahoma\"><u>ALLA SERA</u></font><br><div align=\"center\"><font face=\"Tahoma\"><b><i><br />Ti adoro, Dio mio, <br />e ti amo con tutto il cuore.<br /> Ti ringrazio di avermi creato, fatto cristiano<br /> e conservato in questo giorno.<br />Perdonami il male oggi commesso e,<br /> se qualche bene ho compiuto, accettalo.<br />Custodiscimi nel riposo e liberami dai pericoli.<br />La Tua grazia sia sempre con me<br /> e con tutti i miei cari.<br /><br /> Amen<br /><br /></i></b></font></div>");
        wb.loadData(r1+r2, "text/html", "utf-8");
        
      //Listener per i pulsanti
        View.OnClickListener gestore = new View.OnClickListener() {
        	  public void onClick(View view) { 
        	    
        	    switch(view.getId()){
        	            	
        	      case R.id.bottone1:
        	       finish();        	    	  
        	        break;
        	    }
        	  }
        };
        
        bottone1.setOnClickListener(gestore);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      	   getActionBar().setDisplayHomeAsUpEnabled(true);
      	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ti_adoro, menu);
		return true;
	}

}
