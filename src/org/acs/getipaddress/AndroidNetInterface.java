package org.acs.getipaddress;

public class AndroidNetInterface 
{
	
	String Name = null;
	String Type = null;
	String IPAddress = null;
	String MacAddress = null;
	byte[] INetAddress = null;
	byte[] HWAddress = null;
	boolean hasSubInterfaces = false;
	int numberSubinterfaces = 0;
	
	public AndroidNetInterface() 
	{
		
	}
	
	void setName(String n)
	{
		try { 
			if( (!(n.length() == 0)) && (n!=null) && n.trim()!= "" )
			{
				Name = n;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	String getName()
	{
		return Name;
		
	}
	
	void setHWAddress(byte[] hw)
	{
		HWAddress = hw;
		MacAddress = new String(hw);
	}
	
	byte[] getHWAddress()
	{
		return HWAddress;
	}
	
	void setINetAddress(byte[] ip)
	{
		INetAddress = ip;
		IPAddress = ip_byte2str(ip);
	}
	
	private static String ip_byte2str(byte[] rawBytes) {
	     int i = 4;
	     String ipAddress = "";
	     for (byte raw : rawBytes)
	     {
	         ipAddress += (raw & 0xFF);
	         if (--i > 0)
	         {
	             ipAddress += ".";
	         }
	     }

	     return ipAddress;
	}

}



