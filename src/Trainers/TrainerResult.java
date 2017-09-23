package Trainers;

import Common.Object;

public class TrainerResult extends Object {
	protected double[] taggerConfidence = {0, 0, 0, 0, 0, 0, 0};
	protected double cacherUsage = 0;
	protected double duration = 0;
	
	public TrainerResult(double[] taggerConfidence, double cacherUsage, double duration)
	{
		this.taggerConfidence = taggerConfidence;
		this.cacherUsage = cacherUsage;
		this.duration = duration;
	}

	public double[] getTaggerConfidence()
	{
		return this.taggerConfidence;
	}

	public double getCacherUsage()
	{
		return this.cacherUsage;
	}

	public double getDuration()
	{
		return this.duration;
	}
	
	public String toString()
	{
		String retString;
		retString = this.tagsets[0]+": "+Math.round(this.taggerConfidence[0]*100)+"%, ";
		retString += this.tagsets[1]+": "+Math.round(this.taggerConfidence[1]*100)+"%, ";
		retString += this.tagsets[2]+": "+Math.round(this.taggerConfidence[2]*100)+"%, ";
		retString += this.tagsets[3]+": "+Math.round(this.taggerConfidence[3]*100)+"%, ";
		retString += this.tagsets[4]+": "+Math.round(this.taggerConfidence[4]*100)+"%, ";
		retString += this.tagsets[5]+": "+Math.round(this.taggerConfidence[5]*100)+"%\n";
		retString += "Cacher usage: "+(Math.round(this.cacherUsage*10000)/100.)+"%\n";
		retString += "Took "+this.duration+" seconds";
		
		return retString;
	}
}
