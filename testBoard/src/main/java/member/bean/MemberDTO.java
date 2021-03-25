package member.bean;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	private String mem_id;
	private String mem_pwd;
	private String mem_name;
	private int mem_tel1;
	private int mem_tel2;
	private int mem_tel3;
	private String mem_email1;
	private String mem_email2;
	private String mem_postcode;
	private String mem_address;
	private String mem_detailAddress;
	private String mem_extraAddress;
	private Date logtime;

}
