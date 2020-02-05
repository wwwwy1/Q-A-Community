package testpoll;

public class Jobs {
	private Integer jobId;//自动增长id
	private String jobName; //岗位
	private String companyName;//公司名

	@Override
	public String toString() {
		return "Jobs{" +
				"jobId=" + jobId +
				", jobName='" + jobName + '\'' +
				", companyName='" + companyName + '\'' +
				", workAddr='" + workAddr + '\'' +
				", salary='" + salary + '\'' +
				", pushDate='" + pushDate + '\'' +
				'}';
	}

	private String workAddr;//公司地址
	private String salary;//薪水
	private String pushDate;//发布日期

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWorkAddr() {
		return workAddr;
	}

	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

}