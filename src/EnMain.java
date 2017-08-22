import Common.Blazegraph;
import Parsers.CoNLL.Parser;

public class EnMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Blazegraph bs = new Blazegraph();
			bs.test();
			
			//Parser parser= new Parser("projections-no-norm.txt");
			//parser.getTokenContainer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
