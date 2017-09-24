package Common;

import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;

import Cachers.MemoryCacher;
import Interfaces.Cacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

public class BlazegraphClient {
	
	protected Cacher cacher;
	protected Map<String, String> equalizations;
	public int queriesCount = 0;
	
	public BlazegraphClient() {
		this.cacher = new MemoryCacher();
		ArrayList<String> emptyList = new ArrayList<String>();
		
		this.cacher.set("UD", "...", emptyList);
		this.cacher.set("STTS", "...", emptyList);
		this.cacher.set("PTB", "...", emptyList);
		this.cacher.set("LassyShort", "...", emptyList);
		this.cacher.set("STagger", "...", emptyList);

		this.equalizations = new HashMap<String, String>();
		//this.equalizations.put("Noun", "NN");		
		//this.equalizations.put("Conjunction", "CONJ");	
		
		this.equalizations.put("NOUN", "N");
		this.equalizations.put("NN", "N");
		this.equalizations.put("NNS", "N");
		this.equalizations.put("NNP", "N");
		this.equalizations.put("NNPS", "N");
		this.equalizations.put("NE", "N");
		
		this.equalizations.put("Conjunction", "CONJ");
		this.equalizations.put("KN", "CONJ");
		this.equalizations.put("VG", "CONJ");
		this.equalizations.put("CC", "CONJ");
		this.equalizations.put("KOUI", "CONJ");
		this.equalizations.put("KOUS", "CONJ");
		this.equalizations.put("KON", "CONJ");
		this.equalizations.put("KOKOM", "CONJ");
		
		this.equalizations.put("WW", "VB");
		this.equalizations.put("VERB", "VB");
		this.equalizations.put("VBD", "VB");
		this.equalizations.put("VBG", "VB");
		this.equalizations.put("VBN", "VB");
		this.equalizations.put("VBP", "VB");
		this.equalizations.put("VBZ", "VB");
		this.equalizations.put("VVFIN", "VB");
		this.equalizations.put("VVIMP", "VB");
		this.equalizations.put("VVINF", "VB");
		this.equalizations.put("VVIZU", "VB");
		this.equalizations.put("VVPP", "VB");
		this.equalizations.put("VAFIN", "VB");
		this.equalizations.put("VAIMP", "VB");
		this.equalizations.put("VAINF", "VB");
		this.equalizations.put("VVPP", "VB");
		this.equalizations.put("VMFIN", "VB");
		this.equalizations.put("VMINF", "VB");
		this.equalizations.put("VMPP", "VB");
		
		this.equalizations.put("JJ", "ADJ");
		this.equalizations.put("JJR", "ADJ");
		this.equalizations.put("JJS", "ADJ");
		this.equalizations.put("ADJA", "ADJ");
		this.equalizations.put("ADJD", "ADJ");
		
		this.equalizations.put("AB", "ADV");
		this.equalizations.put("BW", "ADV");
		this.equalizations.put("RB", "ADV");
		this.equalizations.put("RBR", "ADV");
		this.equalizations.put("RBS", "ADV");
		
		this.equalizations.put("DET", "DT");
		
		this.equalizations.put("VZ", "PP");
		this.equalizations.put("IN", "PP");		
	}
	
	public List<String> query(String query) throws Exception
	{
		RemoteRepositoryManager repoManager = new RemoteRepositoryManager("http://18.194.4.246:9999/blazegraph", false /* useLBS */);
		RemoteRepository repo = repoManager.getRepositoryForNamespace("kb");
		TupleQueryResult result = repo.prepareTupleQuery(query).evaluate();
		repoManager.close();
		this.queriesCount++;

		List<String> resultList = new ArrayList<String>();
		String[] parts;
		
		try {
			while (result.hasNext()) {
				BindingSet bs = result.next();

				parts = bs.getValue("a").toString().split("#");
				/*if (parts.length > 1 && this.equalizations.containsKey(parts[1])) {
					resultList.add(this.equalizations.get(parts[1]));
				}*/
				if (parts.length > 1 && !resultList.contains(parts[1])) {
					resultList.add(parts[1]);
				}
				
				parts = bs.getValue("c").toString().split("#");
				/*if (parts.length > 1 && this.equalizations.containsKey(parts[1])) {
					resultList.add(this.equalizations.get(parts[1]));
				}*/
				if (parts.length > 1 && !resultList.contains(parts[1])) {
					resultList.add(parts[1]);
				}
			}
		} finally {
			result.close();
		}     
		
		return resultList;
	}
	
	public List<String> queryAfrikaansUD(String POS) throws Exception
	{
		if (this.cacher.contains("UD", POS)) {
			return this.cacher.get("UD", POS);
		} else {			
			List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/universal_dependencies.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
			
			if (!resultList.contains(POS)) {
				resultList.add(POS);
			}
			
			this.cacher.set("UD", POS, resultList);
			
			return resultList;
		}
	}
	
	public List<String> queryGermanSTTS(String POS) throws Exception
	{
		if (this.cacher.contains("STTS", POS)) {
			return this.cacher.get("STTS", POS);
		} else {
			List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/stts.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
			
			if (!resultList.contains(POS)) {
				resultList.add(POS);
			}
			
			this.cacher.set("STTS", POS, resultList);
			
			return resultList;
		}
	}
	
	public List<String> queryDanishEnglishPTB(String POS) throws Exception
	{
		if (this.cacher.contains("PTB", POS)) {
			return this.cacher.get("PTB", POS);
		} else {
			List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/penn.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
			
			if (!resultList.contains(POS)) {
				resultList.add(POS);
			}
	
			this.cacher.set("PTB", POS, resultList);
			
			return resultList;
		}
	}
	
	public List<String> queryDutchLassyShort(String POS) throws Exception
	{
		if (this.cacher.contains("LassyShort", POS)) {
			return this.cacher.get("LassyShort", POS);
		} else {
			List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/lassy-short.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
			
			if (!resultList.contains(POS)) {
				resultList.add(POS);
			}
			
			this.cacher.set("LassyShort", POS, resultList);
			
			return resultList;
		}
	}
	
	public List<String> querySwedishSTagger(String POS) throws Exception
	{
		if (this.cacher.contains("STagger", POS)) {
			return this.cacher.get("STagger", POS);
		} else {
			List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/suc.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
			
			if (!resultList.contains(POS)) {
				resultList.add(POS);
			}
	
			this.cacher.set("STagger", POS, resultList);
			
			return resultList;
		}
	}
	
	public Cacher getCacher()
	{
		return this.cacher;
	}
}
