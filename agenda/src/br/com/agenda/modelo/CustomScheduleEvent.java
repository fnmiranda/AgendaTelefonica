package br.com.agenda.modelo;

import java.util.Date;
import java.util.Map;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleRenderingMode;

public class CustomScheduleEvent implements ScheduleEvent {

    private String id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String styleClass;
    private Object data;
    private String url;
    private String description;
    private boolean allDay;
    private boolean editable;

    public CustomScheduleEvent() {
    }

    public CustomScheduleEvent(String title, Date start, Date end, boolean allDay, Object data) {
        this.title = title;
        this.startDate = start;
        this.endDate = end;
        this.allDay = allDay;
        this.data = data;
    }

    public CustomScheduleEvent(String title, String description, Date start, Date end, String styleClass, boolean allDay, Object data) {
        this.title = title;
        this.startDate = start;
        this.endDate = end;
        this.styleClass = styleClass;
        this.allDay = allDay;
        this.data = data;
        this.description = description;
//        System.out.println("Titulo: "+this.title);
//        System.out.println("data inicial: "+this.startDate.toString());
//        System.out.println("data final: "+this.endDate.toString());
//        System.out.println("estilo: "+this.styleClass.toString());
//        System.out.println("Dia Todo: "+this.allDay);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

	@Override
	public Map<String, Object> getDynamicProperties() {
		return null;
	}

	@Override
	public ScheduleRenderingMode getRenderingMode() {
		return null;
	}
}