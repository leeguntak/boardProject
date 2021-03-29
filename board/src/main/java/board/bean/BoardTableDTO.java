package board.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardTableDTO {
	
	private int seq;
	private String mem_Id;
	private String mem_Name;
	private String mem_email;
	private String subject;
	private String content;
	private String file1;
	private String file2;
	private int ref;
	private int lev;
	private int step;
	private int pseq;
	private int reply;
	private int hit;
	private int ccomment;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date logtime;
}
