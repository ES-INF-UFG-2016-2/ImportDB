package integ1.trab5.importBD.model;

import integ1.trab5.importBD.model.campos.Egresso4PCampos;
import integ1.trab5.importBD.model.campos.HistoricoUFG;

/**
 * Registro tipo 1: Valor fixo “Reg.1”, os quatro primeiros campos de Egresso, 
 * o nome do Curso da UFG cursado pelo egresso, e todos os campos de Histórico 
 * na UFG.
 *
 * @author gustavosotnas
 */
public class RegistroTipo1 {

    private final String valorFixo = "Reg.1";
    private Egresso4PCampos egresso4PCampos;
    private String nomeCursoUFG;
    private HistoricoUFG historicoUFG;
            
    //====== GETTERS & SETTERS ======
    
    public RegistroTipo1() {
        this.egresso4PCampos = new Egresso4PCampos();
        this.historicoUFG = new HistoricoUFG();
    }
    
    public String getValorFixo() {
        return valorFixo;
    }

    public Egresso4PCampos getEgresso4PCampos() {
        return egresso4PCampos;
    }
    
    public void setEgresso4PCampos(Egresso4PCampos egresso4PCampos) {
        this.egresso4PCampos = egresso4PCampos;
    }

    public String getNomeCursoUFG() {
        return nomeCursoUFG;
    }

    public void setNomeCursoUFG(String nomeCursoUFG) {
        this.nomeCursoUFG = nomeCursoUFG;
    }

    public HistoricoUFG getHistoricoUFG() {
        return historicoUFG;
    }

    public void setHistoricoUFG(HistoricoUFG historicoUFG) {
        this.historicoUFG = historicoUFG;
    }
}
