package org.acs.getipaddress;

public class AndroidNetInterface 
{
	
	String Name = null;
	String Type = null;
	String IPAddress = null;
	String MacAddress = null;
	byte INetAddress = 0x0;
	byte HWAddress = 0x0;
	boolean hasSubInterfaces = false;
	int numberSubinterfaces = 0;
	
	public AndroidNetInterface() 
	{
		
	}
	
	private void setName(String n)
	{
		try { 
			if( (!(n.length() == 0)) && (n!=null) && n.trim()!= "" )
			{
				this.Name = n;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getName()
	{
		return this.Name;
		
	}

}



