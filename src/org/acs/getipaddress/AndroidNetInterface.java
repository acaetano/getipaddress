package org.acs.getipaddress;

public class AndroidNetInterface {
	
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
		if( (!n.isEmpty()) && (n!=null) && (!n.isEmpty(n.trim())) )
		{
			this.Name = n;
		} else {
			//
		}
	}
	
	private String getName()
	{
		return this.Name;
		
	}

}
