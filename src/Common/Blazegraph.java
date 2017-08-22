package Common;

import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;

import com.bigdata.rdf.sail.webapp.SD;
import com.bigdata.rdf.sail.webapp.client.ConnectOptions;
import com.bigdata.rdf.sail.webapp.client.JettyResponseListener;
import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;

import org.apache.log4j.Logger;
import org.openrdf.model.Statement;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.rio.RDFFormat;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfig;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.manager.LocalRepositoryManager;
import org.openrdf.repository.sail.config.SailRepositoryConfig;
import org.openrdf.sail.config.SailImplConfig;
import org.openrdf.sail.nativerdf.config.NativeStoreConfig;

public class Blazegraph {
	public void test() throws Exception
	{
		RemoteRepositoryManager repoManager = new RemoteRepositoryManager("http://18.194.4.246:9999/blazegraph", false /* useLBS */);
		RemoteRepository repo = repoManager.getRepositoryForNamespace("kb");
		TupleQueryResult result = repo.prepareTupleQuery("prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> prefix stts: <http://purl.org/olia/stts.owl#> SELECT ?a ?b WHERE { ?a ?b stts:POS } LIMIT 2").evaluate();
		
		try {
			while (result.hasNext()) {
				BindingSet bs = result.next();
				System.out.println(bs);
			}
		} finally {
			result.close();
		}      
		
		//RepositoryConnection connection = repo.getConnection();
		try {
		// use connection
		} finally {
			repoManager.close();
		}
	}
}
