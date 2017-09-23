import Common.Decider;
import Common.FileReader;
import Trainers.TaggerTrainer;
import Trainers.TrainerResult;

public class EnMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TaggerTrainer tt = new TaggerTrainer();
			FileReader reader = new FileReader("projections-no-norm.txt");
			
			String content = reader.getContent();
			tt.setContent(content);
			TrainerResult result = tt.getResult();

			System.out.println(result.toString());
			
			Decider decider = new Decider(result.getTaggerConfidence());
			
			/*for(int i = 0; i < 5; i++) {
				pos = tokens[i].split("\t", -1);
				System.out.print(pos[0]+" ");
				int decision = decider.getPreferedTagger();
				System.out.print(pos[decision]+" - ");
				System.out.println(tagsets[(decision-1)]);
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
