package net.bechtelus.extended.model;

import java.util.HashMap;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;

import net.bechtelus.controlbeans.TicketSearchController;
import net.bechtelus.standard.APIAccessObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Field;

@ManagedBean
@ApplicationScoped

public class FieldDefinition {
	private static Zendesk zd;
	private static List<Field> fields;
	private static HashMap<Long, Field> hashfields;

	private static FieldDefinition fielddef;
	private static Logger logger;


	public String getFieldName(long fieldid) {
		Field field = hashfields.get(fieldid);
		return field.getDescription();
	}

	public static FieldDefinition getinstance() {

		if (fielddef == null) {
			fielddef = new FieldDefinition();
			logger.info("instanciated");
		}
		return fielddef;
	}

	public static List<Field> getFields() {
		return fields;
	}

	@PostConstruct
	public void init() {

		
		this.logger = LoggerFactory.getLogger(FieldDefinition.class);
		logger.info("init");
		zd = APIAccessObject.getAPIAccessObject();
		this.fields = zd.getTicketFields();
		hashfields = new HashMap<Long, Field>();
		for (int i = 0; i < fields.size(); i++) {
			Field f = fields.get(i);
			hashfields.put(f.getId(), f);
		}

		logger.info("hashsize: " + hashfields.size());
		logger.info("populated");
	}

	@PreDestroy
	public void close() {
		zd.close();
		logger.info("destroyed");
	}
	
	
	
}
