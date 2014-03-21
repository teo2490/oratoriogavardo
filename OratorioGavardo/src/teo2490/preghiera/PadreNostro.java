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

public class PadreNostro extends Activity {

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
        setContentView(R.layout.activity_padre_nostro);
        
        wb = (WebView) findViewById(R.id.myWebView);
        wb.setBackgroundColor(Color.parseColor("#0099FF"));
        bottone1 = (Button) findViewById(R.id.bottone1);
        
        
        String r = new String("<br><div align=\"center\"><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_padre.png\"  width=\"150\" height=\"40\"></div><div align=\"center\"><font face=\"Tahoma\"><b><i><br /></i></b><p><b><i>Padre nostro<br /> che sei nei cieli,<br /> sia santificato il tuo nome,<br /> venga  il tuo regno, sia fatta la tua volont&agrave,<br /> come in cielo e cos&igrave in terra.<br />Dacci oggi il nostro pane quotidiano<br /> e rimetti a noi i nostri debiti<br /> come noi li rimettiamo ai nostri  debitori.<br />E non ci indurre in tentazione,<br /> ma liberaci dal male.<br /><br /> Amen</i></b></p><b><i><br /><br /></i></b></font></div>");
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
		getMenuInflater().inflate(R.menu.padre_nostro, menu);
		return true;
	}

}
