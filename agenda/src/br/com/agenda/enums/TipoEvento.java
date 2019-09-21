package br.com.agenda.enums;

public enum TipoEvento {
	
	PRADRAO("Padrão", ""),
	URGENTE("Urgente","urgente"),
	CANCELADO("Cancelado","cancelado");
	
	private final String descricao;
	private final String css;
	
	private TipoEvento(String descricao, String css) {
		this.descricao = descricao;
		this.css = css;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCss() {
		return css;
	}

}
