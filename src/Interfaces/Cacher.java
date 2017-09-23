package Interfaces;

import java.util.List;

public interface Cacher {
	public void set(String namespace, String POS, List<String> list);
	public List<String> get(String namespace, String POS);
	public boolean contains(String namespace, String POS);
	public int size();
}
