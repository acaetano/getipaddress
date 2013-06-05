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

public class ShowAddress extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_address);
		ListView lv_address = (ListView) findViewById(R.id.lv_network_info);
		String rec_addr = null;
		try {
			rec_addr = get_intf();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("Get IP Address", "passei do try: "+rec_addr);
		String[] arr_rec_addr = Str2ArrStr(rec_addr,"]]]");
		ArrayAdapter<String> list_addr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_rec_addr);
		lv_address.setAdapter(list_addr);
		lv_address.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) 
			{
				String details = ""; //if defined as null, will break when SocketException happens below
				String item = ((TextView) v).getText().toString();
				try {
					details = get_intf_details(item);
				} catch (SocketException e) {
					
					e.printStackTrace();
				}
				Toast.makeText(getBaseContext(), details, Toast.LENGTH_SHORT).show();
				
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
		
		String retorno = ""; //defining as null crashes the Dalvik VM
				
		for(Enumeration<NetworkInterface> list_intf = NetworkInterface.getNetworkInterfaces(); list_intf.hasMoreElements();)
		    {
				NetworkInterface intf = list_intf.nextElement();
				
				retorno = retorno.concat(intf.getName());
				retorno = retorno.concat("\n");
				for (Enumeration<InetAddress> i = intf.getInetAddresses(); i.hasMoreElements();)
				{
					byte[] ip = ((InetAddress) i).getAddress();
					//continuar daqui
				}	
				retorno = retorno.concat("]]]");
				Log.d("Get IP Address", "retorno dentro for: "+retorno);
			}
		Log.d("Get IP Address", "retorno retornado: "+retorno);
		return retorno;
	}
		
	private String get_intf_details(String intf) throws SocketException {
		
		String intf_info="";
		NetworkInterface ni = NetworkInterface.getByName(intf);
		intf_info = ni.toString();		
		return intf_info;
	}
}
