package Common;

import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;

import Cachers.MemoryCacher;
import Interfaces.Cacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

public class Blazegraph {
	
	protected Cacher cacher;
	protected Map<String, String> equalizations;
	public int queriesCount = 0;
	
	public Blazegraph() {
		this.cacher = new MemoryCacher();
		ArrayList<String> emptyList = new ArrayList<String>();
		
		this.cacher.set("UD", "...", emptyList);
		this.cacher.set("STTS", "...", emptyList);
		this.cacher.set("PTB", "...", emptyList);
		this.cacher.set("LassyShort", "...", emptyList);
		this.cacher.set("STagger", "...", emptyList);

		this.equalizations = new HashMap<String, String>();
		this.equalizations.put("Noun", "NN");		
		this.equalizations.put("Conjunction", "CONJ");		
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
