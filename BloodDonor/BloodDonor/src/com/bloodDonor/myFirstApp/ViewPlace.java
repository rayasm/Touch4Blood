package com.bloodDonor.myFirstApp;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPlace extends Activity {
	List<Address> result;
	Intent  resultIntent;
	ListView lstVw;
	final static int MAX_RESULT = 10;
	String searchString=null;
	Geocoder myGeocoder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_place);
        String title;
        lstVw =(ListView)findViewById(R.id.dialoglist);
        title= getIntent().getExtras().getString("Title");
     
        myGeocoder = new Geocoder(this);

		try {
			result = myGeocoder.getFromLocationName(title, MAX_RESULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	    
   	   if ((result == null)||(result.isEmpty())){
   	    Toast.makeText(ViewPlace.this,
   	    "No matches were found or there is no backend service!",
   	      Toast.LENGTH_LONG).show();
   	    finish();
   	   }else{
   	 
   	    MyArrayAdapter adapter = new MyArrayAdapter(this,
   	     android.R.layout.simple_list_item_1, result);
   	     lstVw.setAdapter(adapter);
   	   
   	   }
     
            
            lstVw.setOnItemClickListener(listviewResultOnItemClickListener);
            
          
    }
        
  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_place, menu);
        return true;
    }
    public class MyArrayAdapter extends ArrayAdapter<Address> {
    	  Context mycontext;
    	  public MyArrayAdapter(Context context, int textViewResourceId,
    	    List<Address> objects) {
    	   super(context, textViewResourceId, objects);
    	   // TODO Auto-generated constructor stub
    	   mycontext = context;
    	  }
    	  
    	 
    	 
    	  @Override
    	  public View getView(int position, View convertView, ViewGroup parent) {
    	   // TODO Auto-generated method stub
    	    
    	   int maxAddressLineIndex = getItem(position).getMaxAddressLineIndex();
    	   String addressLine = "";
    	    
    	   for (int j = 0; j <= maxAddressLineIndex; j++){
    	    addressLine += getItem(position).getAddressLine(j) + ",";
    	   }
    	    
    	   TextView rowAddress = new TextView(mycontext);
    	   rowAddress.setText(addressLine);
    	    
    	   return rowAddress;
    	 
    	  }
    	 
    	 }
    
    OnItemClickListener listviewResultOnItemClickListener
	  = new OnItemClickListener(){
	 
	  public void onItemClick(AdapterView<?> parent, View view, int position,
	    long id) {
	   // TODO Auto-generated met
	   double lat = ((Address)parent.getItemAtPosition(position)).getLatitude();
	   double lon = ((Address)parent.getItemAtPosition(position)).getLongitude();
	   String loc =   lat + ":" + lon;
	   //String str=((Address)parent.getItemAtPosition(position)).getLocality().toString();
	   //Toast.makeText(DialogactivityActivity.this,
	    // loc,
	    // Toast.LENGTH_LONG).show(); 
		  Intent resultData = new Intent();
		 
		  resultData.putExtra("valuelon", loc);
		  
		  
		  setResult(Activity.RESULT_OK, resultData);
		  finish();
	   
	   
	  }
	   
	 };
}
