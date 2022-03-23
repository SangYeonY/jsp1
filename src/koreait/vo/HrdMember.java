package koreait.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HrdMember {

	private int custNo;
	private String custName;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
}

