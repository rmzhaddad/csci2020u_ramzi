
public class StudentRecord {

private String SID;
public String getSID() {
	return SID;
}
public void setSID(String sID) {
	SID = sID;
}

private Double Midterm;
public Double getMidterm() {
	return Midterm;
}
public void setMidterm(Double midterm) {
	Midterm = midterm;
}

private Double Assginments;

/**
 * @return the assginments
 */
public Double getAssginments() {
	return Assginments;
}
/**
 * @param assginments the assginments to set
 */
public void setAssginments(Double assginments) {
	Assginments = assginments;
}

private Double FianlExam;

/**
 * @return the fianlExam
 */
public Double getFianlExam() {
	return FianlExam;
}
/**
 * @param fianlExam the fianlExam to set
 */
public void setFianlExam(Double fianlExam) {
	FianlExam = fianlExam;
}

private Double FinalMark;

private String Grade;


public void setLetterValue()
{
	if(this.getFinalMark()>=80&this.getFinalMark()<=100)
		this.setGrade("A");
	if(this.getFinalMark()>=70&this.getFinalMark()<=79)
		this.setGrade("B");
	if(this.getFinalMark()>=60&this.getFinalMark()<=69)
		this.setGrade("C");
	if(this.getFinalMark()>=50&this.getFinalMark()<=59)
		this.setGrade("D");
	if(this.getFinalMark()<=50)
		this.setGrade("F");
	}


public StudentRecord(String SID,Double Midterm,Double Assignments,Double FinalExam) 
{
	this.SID=SID;
	this.Midterm=Midterm;
	this.Assginments= Assignments;
	this.FianlExam= FinalExam;
	this.setFinalMark(((0.2*this.Assginments)+(0.3*this.Midterm)+(0.5*this.FianlExam)));
	setLetterValue();
	
	
	}
/**
 * @return the finalMark
 */
public Double getFinalMark() {
	return FinalMark;
}
/**
 * @param finalMark the finalMark to set
 */
public void setFinalMark(Double finalMark) {
	FinalMark = finalMark;
}
/**
 * @return the grade
 */
public String getGrade() {
	return Grade;
}
/**
 * @param grade the grade to set
 */
public void setGrade(String grade) {
	Grade = grade;
}




	
}
