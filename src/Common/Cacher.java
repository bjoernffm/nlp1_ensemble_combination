package Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cacher {
	Map<String, List<String>> map;
	
	public Cacher() {
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
}
