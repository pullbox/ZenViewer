package net.bechtelus.beans;

import java.io.Serializable;
import java.sql.SQLException;

import net.bechtelus.standard.MaintainStandard;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

@ManagedBean
public class Standard implements Serializable {

	private static final long serialVersionUID = 7778841766245989494L;

	private long Id;
	private String name;
	private String description;
	private String type;
	private int weight;
	private int order;
	private boolean applicable;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		this.Id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isApplicable() {
		return applicable;
	}

	public void setApplicable(boolean applicable) {
		this.applicable = applicable;
	}

	public String showValues() {
		// update or write new standard record to DB
		MaintainStandard maintainstandard = new MaintainStandard();
		if (Id != 0) {
			maintainstandard.writeStandard(this);
		} else {
			maintainstandard.writeStandard(this);
		}
		return ("DspStandard"); // Means to go to page-b.xhtml (since condition
								// is not mapped in faces-config.xml)
	}

	public String someOtherActionControllerMethod() {
		return ("index"); // Means to go to index.xhtml (since condition is not
							// mapped in faces-config.xml)
	}

	public String[] getavailableStandardTypes() {
		String[] availStandard = { "Standard", "Objective" };
		return (availStandard);
	}

	@PostConstruct
	public void init() throws SQLException, ClassNotFoundException {

	}

}
