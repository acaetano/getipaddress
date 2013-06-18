package org.acs.getipaddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.acs.getipaddress.AndroidNetInterface;;

public class ShowAddress extends Activity 

{
	
	AndroidNetInterface interfaces[];
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		Log.d("Get IP Address", "Check -4");
        super.onCreate(savedInstanceState);
        Log.d("Get IP Address", "Check -3");
        setContentView(R.layout.show_address);
        Log.d("Get IP Address", "Check -2");
		ListView lv_address = (ListView) findViewById(R.id.lv_network_info);
		Log.d("Get IP Address", "Check -1");
		String rec_addr = "";
		try {
			Log.d("Get IP Address", "Check 0");
			rec_addr = get_intf();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			Log.d("Get IP Address", "Check A");
			e.printStackTrace();
		}
		
		String[] arr_rec_addr = Str2ArrStr(rec_addr,"]]]");
		Log.d("Get IP Address", "Check 1");
		ArrayAdapter<String> list_addr = new ArrayAdapter<String>(this, R.layout.item_lv_interfaces,R.id.tv_list_interfaces_name, arr_rec_addr);
		Log.d("Get IP Address", "Check 2");
		if(lv_address == null) { Log.d("Get IP Address", "list_addr eh null!"); };
		lv_address.setAdapter(list_addr);
		Log.d("Get IP Address", "Check 3");
		lv_address.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) 
			{
				String details = ""; //if defined as null, will break when SocketException happens below
				Log.d("Get IP Address", "Check 4");
				String name = ((TextView) v.findViewById(R.id.tv_list_interfaces_name)).getText().toString();
				String hwaddress = ((TextView) v.findViewById(R.id.tv_list_interfaces_hwaddress)).getText().toString();
				Log.d("Get IP Address", "Check 5");
				try {
					details = get_intf_details(name);
					Log.d("Get IP Address", "Check 6");
				} catch (SocketException e) {
					
					e.printStackTrace();
				}
				Toast.makeText(getBaseContext(), details, Toast.LENGTH_SHORT).show();
				Log.d("Get IP Address", "Check 7");
				
			}						
		});
    }
	
	public void onStop()
	{
		super.onStop();
		finish();
	}
	
	private String[] Str2ArrStr(String str, String sep){
		
		Log.d("String completa: ", str);
		String[] ArrStr = str.split(sep);
		Log.d("String tratada: ", ArrStr[0]);
		
		return ArrStr;
	}
	
	
	private String get_intf() throws SocketException{
		
		String retorno = ""; //defining as null crashes the app
						
		//cria matriz e popula com interfaces e suas configs
		Log.d("Get IP Address", "Check B");
			initialize_global_interfaces();
		
		return retorno;
	}
		
	private String get_intf_details(String intf) throws SocketException {
		
		String intf_info="";
		NetworkInterface ni = NetworkInterface.getByName(intf);
		intf_info = ni.toString();		
		return intf_info;
	}
	
	void initialize_global_interfaces() throws SocketException {
		
		for(Enumeration<NetworkInterface> list_intf = NetworkInterface.getNetworkInterfaces(); list_intf.hasMoreElements();)
	    {
			Log.d("Get IP Address", "Check C");
			int i = 0;
			Log.d("Get IP Address", "Check D");
			NetworkInterface intf = list_intf.nextElement();
			Log.d("Get IP Address", "Check E");
			interfaces[i] = new AndroidNetInterface();
			Log.d("Get IP Address", "Check E.1");
			String asd = intf.getName();
			Log.d("Get IP Address", "Check E.2");
			interfaces[i].setName(asd);
			Log.d("Get IP Address", "Check F");
			//interfaces[i].setHWAddress(intf.getHardwareAddress());   //method available only after level 9 API 
			
			try {
				for (Enumeration<InetAddress> x = intf.getInetAddresses(); x.hasMoreElements();)
				{
					Log.d("Get IP Address", "Check G");
					InetAddress j = x.nextElement();
					Log.d("Get IP Address", "Check H");
					byte[] ip = ((InetAddress) j).getAddress();
					Log.d("Get IP Address", "Check I");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		
}

