package Common;

import java.util.Random;

public class Decider {

	double[] likelyhoods;
	double[] likelyhoodRanges = {0, 0, 0, 0, 0, 0, 0};
	
	public Decider(double[] likelyhoods)
	{
		this.likelyhoods = likelyhoods;
	}
	
	public int getPreferedTagger()
	{
		Random randomGenerator = new Random();
		double randomDouble = randomGenerator.nextInt(100);
		randomDouble = randomDouble/100;
		
		this.likelyhoodRanges[0] = this.likelyhoods[0];
		this.likelyhoodRanges[1] = this.likelyhoodRanges[0]+this.likelyhoods[1];
		this.likelyhoodRanges[2] = this.likelyhoodRanges[1]+this.likelyhoods[2];
		this.likelyhoodRanges[3] = this.likelyhoodRanges[2]+this.likelyhoods[3];
		this.likelyhoodRanges[4] = this.likelyhoodRanges[3]+this.likelyhoods[4];
		this.likelyhoodRanges[5] = this.likelyhoodRanges[4]+this.likelyhoods[5];
		
		if (randomDouble >= 0 && randomDouble < this.likelyhoodRanges[0]) {
			return 1;
		} else if (randomDouble >= this.likelyhoodRanges[0] && randomDouble < this.likelyhoodRanges[1]) {
			return 2;
		} else if (randomDouble >= this.likelyhoodRanges[1] && randomDouble < this.likelyhoodRanges[2]) {
			return 3;
		} else if (randomDouble >= this.likelyhoodRanges[2] && randomDouble < this.likelyhoodRanges[3]) {
			return 4;
		} else if (randomDouble >= this.likelyhoodRanges[3] && randomDouble < this.likelyhoodRanges[4]) {
			return 5;
		} else {
			return 6;
		}
	}
}
