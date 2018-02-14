package net.slipp.domain;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Question {
	@Id
	@GeneratedValue
	private Long id;
	
	
	//private String writer;
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	
	private String title;
	private String contents;
	
	
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	

}
