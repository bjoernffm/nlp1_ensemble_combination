package Interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Analyzer {
	public ArrayList<Map.Entry<String, Integer>> getMostSignificantTokens(List<String> tokenList);
}
