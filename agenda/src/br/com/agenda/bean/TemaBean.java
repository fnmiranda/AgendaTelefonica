package br.com.agenda.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class TemaBean implements Serializable{
	private String tema = "bootstrap";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String[] getTemas() {
		return new String[]{"afterdark", "afternoon", "afterwork", "black-tie",
				"blitzer", "bluesky", "bootstrap", "casablanca", "cruze", "cupertino", 
				"dark-hive", "delta", "dot-luv", "eggplant", "excite-bike", "flick",
				"glass-x","home", "hot-sneaks", "humanity", "le-frog", "midnight", 
				"mint-choc", "overcast", "pepper-grinder", "redmond", "rocket", "sam",
				"smoothness", "south-street", "start", "sunny", "swanky-purse", 
				"trontastic", "ui-darkness", "ui-lightness", "vader"};
	}
}
