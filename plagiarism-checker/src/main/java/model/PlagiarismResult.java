package model;

public class PlagiarismResult {
	private int id;
	private int essayId;
	private Integer matchedEssayId;
	private double similarityPercent;
	private String description;

	public PlagiarismResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlagiarismResult(int id, int essayId, Integer matchedEssayId, double similarityPercent, String description) {
		super();
		this.id = id;
		this.essayId = essayId;
		this.matchedEssayId = matchedEssayId;
		this.similarityPercent = similarityPercent;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEssayId() {
		return essayId;
	}

	public void setEssayId(int essayId) {
		this.essayId = essayId;
	}

	public Integer getMatchedEssayId() {
		return matchedEssayId;
	}

	public void setMatchedEssayId(Integer matchedEssayId) {
		this.matchedEssayId = matchedEssayId;
	}

	public double getSimilarityPercent() {
		return similarityPercent;
	}

	public void setSimilarityPercent(double similarityPercent) {
		this.similarityPercent = similarityPercent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
