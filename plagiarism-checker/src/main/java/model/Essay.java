package model;

import java.sql.Timestamp;

public class Essay {
	private int id;
	private int userId;
	private String filename;
	private String filepath;
	private Timestamp submittedAt;
	private String status;
	private double plagiarismPercent;

	public Essay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Essay(int id, int userId, String filename, String filepath, Timestamp submittedAt, String status,
			double plagiarismPercent) {
		super();
		this.id = id;
		this.userId = userId;
		this.filename = filename;
		this.filepath = filepath;
		this.submittedAt = submittedAt;
		this.status = status;
		this.plagiarismPercent = plagiarismPercent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Timestamp getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(Timestamp submittedAt) {
		this.submittedAt = submittedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPlagiarismPercent() {
		return plagiarismPercent;
	}

	public void setPlagiarismPercent(double plagiarismPercent) {
		this.plagiarismPercent = plagiarismPercent;
	}

}
