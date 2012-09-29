package com.example.tresting;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.widget.TextView;
import android.widget.Toast;

public class Tresting extends Activity {
	private static final String SOAP_ACTION="http://tempuri.org/CelsiusToFahrenheit";
	private static final String METHOD_NAME= "CelsiusToFahrenheit";
	private static final String NAMESPACE ="http://tempuri.org/";
	private static final String URL="http://www.w3schools.com/webservices/tempconvert1.asmx";
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tresting);
        tv=(TextView)findViewById(R.id.temp);
        call();
    }
    public void call()
    {
       
      SoapObject Request = new SoapObject(NAMESPACE,METHOD_NAME);
        Request.addProperty("Celsius","32");
        SoapSerializationEnvelope soapEnvelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.dotNet=true;
        soapEnvelope.setOutputSoapObject(Request);
        
        HttpTransportSE aht = new  HttpTransportSE(URL); 
        try
        
        {
        	tv.setText("status : " );
        	
        	aht.call(SOAP_ACTION, soapEnvelope);
        	Toast.makeText(getApplicationContext(), "msg start", Toast.LENGTH_SHORT).show();
        	SoapPrimitive resultString= (SoapPrimitive)soapEnvelope.getResponse();
        	tv.setText("status : " + resultString.toString());
        	Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
        	
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tresting, menu);
        return true;
    }
}