package com.bloodDonor.myFirstApp;

import java.util.List;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;

import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDonors extends Activity {
	private CDataBase datasource;
	 TableLayout t1;
	
	Spinner spnLocale;
	 String Type;
	 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_view);
	
	datasource = new CDataBase(this);
	datasource.open();
	
	


	spnLocale = (Spinner)findViewById(R.id.CboSelectGroup);
	
	TableLayout tl = (TableLayout) findViewById(R.id.main_table);
	TableRow tr_head = new TableRow(this);
	tr_head.setId(10);
	tr_head.setBackgroundColor(Color.GRAY);
	tr_head.setLayoutParams(new LayoutParams(
	LayoutParams.FILL_PARENT,
	LayoutParams.WRAP_CONTENT));
	
		TextView label_name = new TextView(this);
		label_name .setId(20);
		label_name .setText("Name");
		label_name .setTextColor(Color.WHITE);
		label_name .setPadding(2, 0, 5, 0);
	    tr_head.addView(label_name);  // add the column to the table row here
  
	    TextView BloodGroup = new TextView(this);
	    BloodGroup .setId(21);
	    BloodGroup .setText("Blood Group");
	    BloodGroup .setTextColor(Color.WHITE);
	    BloodGroup .setPadding(2, 0, 5, 0);
	    tr_head.addView(BloodGroup);  
     
	    TextView place = new TextView(this);
	    place .setId(22);
	    place .setText("Place");
	    place .setTextColor(Color.WHITE);
	    place .setPadding(2, 0, 5, 0);   
	    tr_head.addView(place );
      
    
	   TextView ContactNo = new TextView(this);
	   ContactNo .setId(23);
	   ContactNo .setText("Contact");
	   ContactNo .setTextColor(Color.WHITE);
	   ContactNo .setPadding(2, 0, 5, 0);
	   tr_head.addView(ContactNo );  
	   
   
    tl.addView(tr_head, new TableLayout.LayoutParams(
            LayoutParams.FILL_PARENT,
            LayoutParams.WRAP_CONTENT));
	
    

	spnLocale.setOnItemSelectedListener(
		    new AdapterView.OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					int pos =spnLocale.getSelectedItemPosition();
					if(pos!=0)  
		            {  
						 Type=spnLocale.getSelectedItem().toString();
TableLayout t2 = (TableLayout) findViewById(R.id.main_table);
						getValues( Type,t2)	;
		            }  
		            else{  
		                 
		                 return;  
		            } 
					
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
		        //add some code here
		    }
		);
	
	
	//List<CProperty> values = datasource.getAllComments();
	//ArrayAdapter<CProperty> adapter = new ArrayAdapter<CProperty>(this,
		//	android.R.layout.simple_list_item_1, values);
//	setListAdapter(adapter);
	 }
	 
	void  getValues( String Type,TableLayout tl)	 
	 {
		
		int tlcount = tl.getChildCount();
		for (int i = tlcount; i >1; i--) {
		    View child = tl.getChildAt(i);
		    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
		}
	
			Cursor cursor = datasource.getAllComments(Type);
			if (cursor.getCount() !=0)
			{
	
				Toast.makeText(this, "Hi",  Toast.LENGTH_SHORT).show();
				    Integer count=0;
					 cursor.moveToFirst();
					 if(cursor.moveToFirst())
				     {
				     	
							 do
					     	{
								 
								
								  TableRow tr = new TableRow(this);
								  tr.setId(100+count);
								  
								  tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
								  
								 tr.setClickable(true);
								
								tr.setOnClickListener(new View.OnClickListener(){
									 public void onClick(View view){
										Toast.makeText(ViewDonors.this, "row selected",  Toast.LENGTH_SHORT).show();
									 }
								});
								  TextView LabelName = new TextView(this);
								  LabelName.setId(200+count); 
								  LabelName.setText(cursor.getString(1));
								  LabelName.setPadding(2, 0, 5, 0);
								  //LabelName.setTextColor(Color.WHITE);
								  tr.addView(LabelName);
								  
								TextView tvBloodGroup = new TextView(this);
								tvBloodGroup.setId(200+count); 
								tvBloodGroup.setText(cursor.getString(2));
								tvBloodGroup.setPadding(2, 0, 5, 0);
								//tvBloodGroup.setTextColor(Color.WHITE);
								  tr.addView(tvBloodGroup);
								  
								 TextView tvContact = new TextView(this);
								tvContact.setId(200+count); 
								tvContact.setText(cursor.getString(3));
								tvContact.setPadding(2, 0, 5, 0);
								//tvContact.setTextColor(Color.WHITE);
								  tr.addView(tvContact);
								  
								 TextView tvLocation = new TextView(this);
								tvLocation.setId(200+count); 
								tvLocation.setText(cursor.getString(4));
								tvLocation.setPadding(2, 0, 5, 0);
								//tvLocation.setTextColor(Color.WHITE);
								  tr.addView(tvLocation);
								 	
								  tl.addView(tr, new TableLayout.LayoutParams(
								             LayoutParams.FILL_PARENT,
								             LayoutParams.WRAP_CONTENT));
									count++;

					     	}while(cursor.moveToNext());
				     }
					
		
		 }
	 }
	 
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnView:
			
			Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
			Intent myIntent = new Intent(this, ViewMaps.class);
			int requestCode=1;
			ViewDonors.this.startActivityForResult(myIntent, requestCode); 
			break;
		
		}
	}

}
