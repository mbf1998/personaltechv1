/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Pergunta;
import static com.mycompany.personaltechweb.entities.Pergunta.PERGUNTA;
import com.mycompany.personaltechweb.services.PerguntaServico;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author T-Gamer
 */
@RequestScoped
@Named("PerguntaBean")
public class PerguntaBean extends PerguntaServico implements Serializable {
  
    private PerguntaServico perguntaServico ;
     private List<Pergunta> listaPergunta;
    
    @Override
    public void criar() {
    perguntaServico.init();
    }
    public List<Pergunta> getPerguntas(){
     
     return super.getEntidades(PERGUNTA);
 }

    public PerguntaServico getPerguntaServico() {
        return perguntaServico;
    }

    public void setPerguntaServico(PerguntaServico perguntaServico) {
        this.perguntaServico = perguntaServico;
    }

    public List<Pergunta> getListaPergunta() {
        return listaPergunta;
    }

    public void setListaPergunta(List<Pergunta> listaPergunta) {
        this.listaPergunta = listaPergunta;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }
    
}
