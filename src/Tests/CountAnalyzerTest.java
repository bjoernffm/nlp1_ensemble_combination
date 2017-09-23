package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import Common.CountAnalyzer;
import Interfaces.Analyzer;

public class CountAnalyzerTest {

	@Test
	public void testGetMostSignificantTokens1() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		Analyzer analyzer = new CountAnalyzer();
		ArrayList<Entry<String, Integer>> result = analyzer.getMostSignificantTokens(list);
		System.out.println(result.toString());
		assertTrue(result.toString().equals("[b=1, a=1, c=1]"));
	}

	@Test
	public void testGetMostSignificantTokens2() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("c");
		list.add("c");
		list.add("c");
		Analyzer analyzer = new CountAnalyzer();
		ArrayList<Entry<String, Integer>> result = analyzer.getMostSignificantTokens(list);
		System.out.println(result.toString());
		assertTrue(result.toString().equals("[c=3, b=2, a=1]"));
	}

	@Test
	public void testGetMostSignificantTokens3() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("POS");
		list.add("c");
		list.add("a");
		Analyzer analyzer = new CountAnalyzer();
		ArrayList<Entry<String, Integer>> result = analyzer.getMostSignificantTokens(list);
		System.out.println(result.toString());
		assertTrue(result.toString().equals("[b=2, a=2, c=1]"));
	}

}
