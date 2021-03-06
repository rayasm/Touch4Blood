package com.ConnectingNetWebSrv.connectingnetwebsrv;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectingWebSrv extends Activity {
	TextView tv;
	private static final String SOAP_ACTION="http://tempuri.org/CelsiusToFahrenheit";
	private static final String METHOD_NAME= "CelsiusToFahrenheit";
	private static final String NAMESPACE ="http://tempuri.org/";
	private static final String URL="http://www.w3schools.com/webservices/tempconvert.asmx";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting_web_srv);
         tv=(TextView)findViewById(R.id.textView1);
         DownloadWebPageTask task = new DownloadWebPageTask();
 		task.execute(new String[] { });

      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_connecting_web_srv, menu);
        return true;
    }
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {  
       
      
        protected void onPostExecute(String result) {
        	tv.setText(result);
		}
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String returnValue = "mahi";
			SoapObject Request = new SoapObject(NAMESPACE,METHOD_NAME);
	        Request.addProperty("Celsius","35");
	        SoapSerializationEnvelope soapEnvelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        soapEnvelope.dotNet=true;
	        soapEnvelope.setOutputSoapObject(Request);
	        
	        HttpTransportSE aht = new  HttpTransportSE(URL); 
	        try
	        
	        {
	        	
	        	
	        	aht.call(SOAP_ACTION, soapEnvelope);
	        	
	        	SoapPrimitive resultString= (SoapPrimitive)soapEnvelope.getResponse();
	        	
	        	
	        	returnValue=resultString.toString();
	        	
	        }
	        catch(Exception e)
	        {
	        e.printStackTrace();
	        }
			return returnValue;
	    }
	 
    }
  
}
