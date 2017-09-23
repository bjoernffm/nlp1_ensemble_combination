package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Interfaces.Analyzer;

public class CountAnalyzer implements Analyzer {
	protected ArrayList<String> blacklist;
	
	public CountAnalyzer()
	{
		this.blacklist = new ArrayList<String>();
		this.blacklist.add("NamedIndividual");
		this.blacklist.add("Tag");
		this.blacklist.add("LinguisticAnnotation");
		this.blacklist.add("LingusticAnnotation");
		this.blacklist.add("POS");
		this.blacklist.add("PartOfSpeech");
	}
	
	public ArrayList<Map.Entry<String, Integer>> getMostSignificantTokens(List<String> tokenList)
	{
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		String POSTag;
		
		for(int i = 0; i < tokenList.size(); i++) {
			POSTag = tokenList.get(i);
			
			if (this.blacklist.contains(POSTag)) {
				continue;
			}
			
			if (table.containsKey(POSTag)) {
				Integer value = table.get(POSTag);
				table.replace(POSTag, (value+1));
			} else {
				table.put(POSTag, 1);
			}
		}
		
		//Transfer as List and sort it
		ArrayList<Map.Entry<String, Integer>> l = new ArrayList<Entry<String, Integer>>(table.entrySet());
		Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){
			public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
	            return o2.getValue().compareTo(o1.getValue());
	        }
		});

		return l;
	}
}
