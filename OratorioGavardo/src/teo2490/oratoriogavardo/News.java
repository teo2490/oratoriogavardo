package teo2490.oratoriogavardo;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.Button;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class News extends Activity {
	private Button bottone1;
	private Bitmap image1;
	private Bitmap image2;
	private ImageView iv1;
	private ImageView iv2;
	public WebView wb;
	
	private ProgressDialog pDialog;
	
	
	@SuppressLint("NewApi")
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
        
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
        
       
		new DownloadImage().execute();
	}

	 class DownloadImage extends AsyncTask<Void,Void,Void>
     {

         @Override
         protected void onPreExecute() {
             // TODO Auto-generated method stub
             super.onPreExecute();
             pDialog = ProgressDialog.show(News.this, "Caricamento..",
 					"Attendere prego", true);
         }

         /**
          * Downloading the image from the server
          */
         @Override
         protected Void doInBackground(Void... params) {
        	 wb = (WebView) findViewById(R.id.webView1);
        	 wb.setBackgroundColor(Color.parseColor("#0099FF"));
             // TODO Auto-generated method stub
             try
             {
             image1 = downloadBitmap("http://www.oratoriogavardo.it/public/navphp/oratoriogavardo/images/home/vsx.jpg");
             image2 = downloadBitmap("http://www.oratoriogavardo.it/public/navphp/oratoriogavardo/images/home/vdx.jpg");
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
             String head = "<br><img src=\"http://www.oratoriogavardo.it/public/navphp/AndroidApp/ic_news.png\"  width=\"150\" height=\"60\">";
             wb.loadData("<div align='center'>"+ head + "</div>", "text/html", "utf-8");
             return null;
         }

         /**
 		 * After completing background task (if this is the last tag active) dismiss the progress dialog
 		 * 
 		 */
         @Override
         protected void onPostExecute(Void result) {
             // TODO Auto-generated method stub
             super.onPostExecute(result);
             // dismiss the dialog once got all details
             	pDialog.dismiss();
          			
          			iv1 = (ImageView) findViewById(R.id.imageView1);
          			iv2 = (ImageView) findViewById(R.id.imageView2);
          			iv1.setImageBitmap(image1);
          			iv2.setImageBitmap(image2);
         }   
         
         
     }
     
     /**
      * This method get the stream from HTTP and converti it in Bitmap format
      * @param url
      * @return The bitmap image of a gift
      */
      private Bitmap downloadBitmap(String url) {
    	  Bitmap image = null;
          // initilize the default HTTP client object
          final DefaultHttpClient client = new DefaultHttpClient();

          //forming a HttoGet request 
          final HttpGet getRequest = new HttpGet(url);
          try {

              HttpResponse response = client.execute(getRequest);

              //check 200 OK for success
              final int statusCode = response.getStatusLine().getStatusCode();

              if (statusCode != HttpStatus.SC_OK) {
                  Log.w("ImageDownloader", "Error " + statusCode + 
                          " while retrieving bitmap from " + url);
                  return null;

              }

              final HttpEntity entity = response.getEntity();
              if (entity != null) {
                  InputStream inputStream = null;
                  try {
                      // getting contents from the stream 
                      inputStream = entity.getContent();

                      // decoding stream data back into image Bitmap that android understands
                      image = BitmapFactory.decodeStream(inputStream);


                  } finally {
                      if (inputStream != null) {
                          inputStream.close();
                      }
                      entity.consumeContent();
                  }
              }
          } catch (Exception e) {
              // You Could provide a more explicit error message for IOException
              getRequest.abort();
              Log.e("ImageDownloader", "Something went wrong while" +
                      " retrieving bitmap from " + url + e.toString());
          } 

          return image;
      }
}
