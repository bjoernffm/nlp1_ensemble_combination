package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Common.BlazegraphClient;

public class BlazegraphClientTest {

	@Test
	public void testQuery() throws Exception {
		BlazegraphClient bs = new BlazegraphClient();
		List<String> resultList = bs.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/stts.owl#> SELECT ?a ?c WHERE { stts:NN rdf:type ?a. ?a rdfs:subClassOf* ?c }");
		assertTrue(resultList.toString().equals("[CommonNoun, Noun, POS, Tag, LinguisticAnnotation]"));
	}

	@Test
	public void testQueryGermanSTTS() throws Exception {
		BlazegraphClient bs = new BlazegraphClient();
		List<String> result = bs.queryGermanSTTS("NN");
		assertTrue(result.toString().equals("[CommonNoun, Noun, POS, Tag, LinguisticAnnotation, NN]"));
	}

	@Test
	public void testGetCacher() throws Exception {
		BlazegraphClient bs = new BlazegraphClient();
		bs.queryGermanSTTS("NN");
		assertTrue(bs.getCacher().get("STTS", "NN").toString().equals("[CommonNoun, Noun, POS, Tag, LinguisticAnnotation, NN]"));
		bs.queryGermanSTTS("NN");
		assertTrue(bs.getCacher().get("STTS", "NN").toString().equals("[CommonNoun, Noun, POS, Tag, LinguisticAnnotation, NN]"));
	}

}
