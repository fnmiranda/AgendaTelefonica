package br.com.agenda.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.com.agenda.dao.UsuarioBD;
import br.com.agenda.modelo.Usuario;
import br.com.agenda.tx.Transacional;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class UsuarioEditorBean implements Serializable {

	@Inject
	private FacesContext context;
	@Inject
	private UsuarioBD usuarioDB;
	@Inject
	private Usuario usuario;
	
	private boolean edicao;
	private boolean edicaoInput;
	private String senhaAntiga ="";
	private String senhaNova ="";
	
	@PostConstruct
	public void init() {
		this.usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		this.setEdicao(true);
		this.setEdicaoInput(false);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	
	public void checarBooleano() {
		if(edicaoInput == false) {
			this.setEdicao(true);
		}else {
			this.setEdicao(false);
		}
		
	}

	public void editarDadosDoUsuario() {
		this.setEdicaoInput(true);
		checarBooleano();
	}

	public boolean isEdicaoInput() {
		return edicaoInput;
	}

	public void setEdicaoInput(boolean edicaoInput) {
		this.edicaoInput = edicaoInput;
	}
	
    @Transacional
    public void salvar() {
//    	System.out.println("Entrou no metodo Salvar: "+ senhaAntiga);
    	this.setEdicaoInput(false);
    	checarBooleano();
    	FacesMessage message;
        if (usuario.getId() != null && usuario.getSenha().equals(senhaAntiga)) {
//        	System.out.println(senhaNova);
        	usuario.setSenha(senhaNova);
            usuarioDB.atualiza(usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "usuario Salvo", "usuario Salvo");
            addMessage(message);
        } else {
        	System.out.println("não pode ser salvo: "+senhaNova);
        	String mensagem = "Usuário não pode ser editado";
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        	addMessage(message);
        }
        
    	
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}


}
