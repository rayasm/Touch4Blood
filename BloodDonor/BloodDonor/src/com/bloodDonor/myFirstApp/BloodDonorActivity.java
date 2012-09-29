package com.bloodDonor.myFirstApp;



import android.location.Geocoder;
import android.os.Bundle;

import android.view.Menu;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import android.annotation.SuppressLint;
import android.app.ListActivity;

import java.util.List;
import java.util.Random; 

import com.bloodDonor.ConnectServer.User;
import com.bloodDonor.ConnectServer.UserController;

import android.app.Activity;
import android.content.Intent;


public class BloodDonorActivity extends Activity {
	ImageButton imageButton;
    /** Called when the activity is first created. */
	private CDataBase datasource;
	ListView lstVw;
	EditText searchText;
	String searchString=null;
	Geocoder myGeocoder;
	String email;
	String UserName;
	String mylat;
	String mylon;
	
	int  SearchrequestCode;

	final static int MAX_RESULT = 10;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        datasource = new CDataBase(this);
		datasource.open();
		//get the email address
		//email= getIntent().getExtras().getString("UserEmail");
		//UserName=getIntent().getExtras().getString("UserName"); 
		searchText=(EditText)findViewById(R.id.txtPlace_name);
		
		
		/*
		List<CProperty> values = datasource.getAllComments();
		ArrayAdapter<CProperty> adapter = new ArrayAdapter<CProperty>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter); */
	
    }

   
    

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		//ArrayAdapter<CProperty> adapter = (ArrayAdapter<CProperty>) getListAdapter();
		CProperty comment = null;
		
		switch (view.getId()) {
		case R.id.btnEnter:
			
			addUser();
		
		
			//adapter.add(comment);
			break;			
			
		case R.id.btnnext:
			Intent myIntent = new Intent(BloodDonorActivity.this, ViewDonors.class);
			int requestCode=1;
			BloodDonorActivity.this.startActivityForResult(myIntent, requestCode);
			break;
			
		case R.id.imgSearchPlace:
			
			Intent mySearchINtent = new Intent(BloodDonorActivity.this, ViewPlace.class);
			SearchrequestCode=1;
				
				mySearchINtent.putExtra("Title",searchText.getText().toString() );
				BloodDonorActivity.this.startActivityForResult(mySearchINtent, SearchrequestCode);
			
		

		}
		
	} 

    @Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}
	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode,
	            Intent data) {
		
	        if (requestCode == SearchrequestCode) {
	            if (resultCode == RESULT_OK) {
	              
	           String GetLatLon=data.getStringExtra("valuelon");
	           String[] strArray = GetLatLon.split(":");
	           mylat=strArray[0].toString();
	           mylon=strArray[1].toString();
	           // use 'myValue' return value here
	              Toast.makeText(BloodDonorActivity.this,
	            		  GetLatLon,
	    			      Toast.LENGTH_LONG).show();
	            }
	        }
	  }
	 
	 final void addUser()
	    {
		final Thread checkUpdate = new Thread() {
		    public void run() {
			EditText name = (EditText) findViewById(R.id.txtPerson);
			Spinner BloodType= (Spinner) findViewById(R.id.blood_desc);
			EditText place = (EditText) findViewById(R.id.txtPlace_name);			
			EditText phone = (EditText) findViewById(R.id.phone_no);
			User u = new User();
			u.setname(name.getText().toString());
			u.setplace(place.getText().toString());
			u.setPhone(phone.getText().toString());
			u.setBloodtype(BloodType.getSelectedItem().toString());
			u.setuserid("email");
			u.setapplication("facebook");
			u.setlat(mylat);
			u.setlon(mylat);
			
			final UserController uc = new UserController();
			try {
			    uc.create(u);
			} catch (Exception e) {
			    return;
			}
			//final Intent intent = new Intent(AddUserActivity.this,
				//GaeHomeScreen.class);
			//startActivity(intent);
		    }
		};
		checkUpdate.start();
	    }
	 
}