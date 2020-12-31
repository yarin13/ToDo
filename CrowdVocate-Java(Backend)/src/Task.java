
public class Task {
	
	public Task() {
		
	}
	public Task(String name,String description,String status,String startDate,String endDate) {
		setName(name);
		setDescription(description);
		setStatus(status);
		setStartDate(startDate);
		setEndDate(endDate);
	}

	public int id;
	public String name;
	public String description;
	public String status;
	public String startDate;
	public String endDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
