package Common;

import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

public class Blazegraph {
	
	public List<String> query(String query) throws Exception
	{
		RemoteRepositoryManager repoManager = new RemoteRepositoryManager("http://18.194.4.246:9999/blazegraph", false /* useLBS */);
		RemoteRepository repo = repoManager.getRepositoryForNamespace("kb");
		TupleQueryResult result = repo.prepareTupleQuery(query).evaluate();
		repoManager.close();

		List<String> resultList = new ArrayList<String>();
		String[] parts;
		
		try {
			while (result.hasNext()) {
				BindingSet bs = result.next();

				parts = bs.getValue("a").toString().split("#");
				if (parts.length > 1 && !resultList.contains(parts[1])) {
					resultList.add(parts[1]);
				}
				
				parts = bs.getValue("c").toString().split("#");
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
		List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/universal_dependencies.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
		
		if (!resultList.contains(POS)) {
			resultList.add(POS);
		}
		
		return resultList;
	}
	
	public List<String> queryGermanSTTS(String POS) throws Exception
	{
		List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/stts.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
		
		if (!resultList.contains(POS)) {
			resultList.add(POS);
		}
		
		return resultList;
	}
	
	public List<String> queryDanishEnglishPTB(String POS) throws Exception
	{
		List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/penn.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
		
		if (!resultList.contains(POS)) {
			resultList.add(POS);
		}
		
		return resultList;
	}
	
	public List<String> queryDutchLassyShort(String POS) throws Exception
	{
		POS = POS.toLowerCase();
		List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/lassy-short.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
		
		if (!resultList.contains(POS)) {
			resultList.add(POS);
		}
		
		return resultList;
	}
	
	public List<String> querySwedishSTagger(String POS) throws Exception
	{
		POS = POS.toLowerCase();
		List<String> resultList = this.query("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/suc.owl#> SELECT ?a ?c WHERE { stts:"+POS+" rdf:type ?a. ?a rdfs:subClassOf* ?c }");
		
		if (!resultList.contains(POS)) {
			resultList.add(POS);
		}
		
		return resultList;
	}
}
