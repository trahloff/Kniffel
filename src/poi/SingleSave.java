package poi;

import java.util.TreeSet;

public class SingleSave {
	private TreeSet<Integer> scores = new TreeSet<Integer>();
	private String name;
	
	public SingleSave(String name) {
		super();
		this.name = name;
	}

	public TreeSet<Integer> getScores() {
		return scores;
	}

	public void setScores(TreeSet<Integer> scores) {
		this.scores = scores;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
	

}
