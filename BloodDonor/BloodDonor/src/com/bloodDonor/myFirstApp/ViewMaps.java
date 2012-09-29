package com.bloodDonor.myFirstApp;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;;


public class ViewMaps extends Activity {
	
	public WebView webview;     
	protected ProgressDialog web_pd;
	final Handler rb_web_pd_Handler = new Handler();   
	
	@Override
	public void onCreate(Bundle icicle) 
	{
		
		
		super.onCreate(icicle);                   
	    setContentView(R.layout.activity_maps);        

	   webview = (WebView)findViewById(R.id.webViewDis);  
	    webview.getSettings().setJavaScriptEnabled(true);
	    webview.getSettings().setPluginsEnabled(true);
	    webview.getSettings().setDomStorageEnabled(true);
	    webview.getSettings().setBuiltInZoomControls(true);
	    webview.getSettings().setSupportZoom(true);
	    webview.getSettings().setAllowFileAccess(true);

	    web_pd = ProgressDialog.show(ViewMaps.this, "", "Please wait...", true);
	    Thread t = new Thread() 
	    {
	        public void run() 
	        {               
	            rb_web_pd_Handler.post(checked_LoginResp);
	        }                                               
	    };
	    t.start();

	    webview.setWebViewClient(new WebViewClient() 
	    {
	        @Override
	        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
	        {
	            // Handle the error
	        }

	        public boolean shouldOverrideUrlLoading(WebView view, String url) {

	                view.loadUrl(url);

	            return true;
	        }
	    });
	    float source_lat = 17.493133f;
	    float source_long = 78.398438f;
	    float dest_lat = 17.444992f;
	    float dest_long = 78.379898f;
	    String page_url ="http://maps.google.com/maps?daddr="+dest_lat+","+dest_long+"&saddr="+source_lat+","+source_long+"&output=embed";             
	    System.out.println("URL:"+page_url);       
	    webview.loadUrl(page_url);     
	}


	 final Runnable checked_LoginResp = new Runnable()
	 {
	     public void run()
	     {
	         try
	         {
	             webview.setWebChromeClient(new WebChromeClient() 
	             {
	                 public void onProgressChanged(WebView view, int progress)
	                 {
	                     if(progress == 100)
	                     {
	                         web_pd.dismiss();  
	                     }
	                 }
	             });
	         } 
	         catch(Exception e)
	         {
	             e.printStackTrace();
	         }
	     }      
	 };    
	

}