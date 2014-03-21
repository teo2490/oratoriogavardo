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

@SuppressLint("NewApi")
public class AveMaria extends Activity {

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
	
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ave_maria);
        
        wb = (WebView) findViewById(R.id.myWebView);
        wb.setBackgroundColor(Color.parseColor("#0099FF"));
        bottone1 = (Button) findViewById(R.id.bottone1);
        
        
        String r = new String("<br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_ave.png\"  width=\"150\" height=\"40\"></div><font face=\"Tahoma\"><b><i><div align=\"center\"><br />Ave, o Maria <br>piena di grazia, il Signore &egrave con te,<br> tu sei benedetta fra le donne,<br> e benedetto &egrave il frutto del tuo seno Ges&ugrave. <br>Santa Maria madre di Dio,<br> prega per noi peccatori,<br> adesso e nell'ora della nostra morte.<br><br> Amen<br /><br /></div></i></b></font>");
    	wb.loadData(r, "text/html", "UTF-8");
        
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
		getMenuInflater().inflate(R.menu.ave_maria, menu);
		return true;
	}

}
