package Cachers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaces.Cacher;

public class MemoryCacher implements Cacher {
	Map<String, List<String>> map;
	
	public MemoryCacher() {
		this.map = new HashMap<String, List<String>>();
	}
	
	public void set(String namespace, String POS, List<String> list)
	{
		this.map.put(namespace+'_'+POS, list);
	}
	
	public List<String> get(String namespace, String POS)
	{
		return this.map.get(namespace+'_'+POS);
	}
	
	public boolean contains(String namespace, String POS)
	{
		return this.map.containsKey(namespace+'_'+POS);
	}
	
	public int size()
	{
		return this.map.size();
	}
}
