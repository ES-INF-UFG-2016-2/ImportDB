package integ1.trab5.importBD.model;

import integ1.trab5.importBD.model.campos.Egresso2e3Campos;
import integ1.trab5.importBD.model.campos.ProgramaAcademico;
import integ1.trab5.importBD.model.enums.CursosUFG;

/**
 * Registro tipo 2: Valor fixo “Reg.2”, o segundo e terceiro campos do Egresso,
 * o identificador de um Curso da UFG cursado pelo egresso, e todos os campos de
 * Realização de Programa Acadêmico do egresso nesse curso.
 *
 */
public class RegistroTipo2 {
    
    private final String valorFixo = "Reg.2";
    private Egresso2e3Campos egresso2e3Campos;
    private String idCursoCursadoUFG;
    private ProgramaAcademico progAcademico;

    public RegistroTipo2() {
        this.egresso2e3Campos = new Egresso2e3Campos();
        this.progAcademico = new ProgramaAcademico();
    }

    public Egresso2e3Campos getEgresso2e3Campos() {
        return egresso2e3Campos;
    }

    public String getValorFixo() {
        return valorFixo;
    }    
    
    public void setEgresso2e3Campos(Egresso2e3Campos egresso2e3Campos) {
        this.egresso2e3Campos = egresso2e3Campos;
    }

    public String getIdCursoCursadoUFG() {
        return idCursoCursadoUFG;
    }

    public void setIdCursoCursadoUFG(String idCursoCursadoUFG) {
        if (CursosUFG.isMember(idCursoCursadoUFG)) {
            this.idCursoCursadoUFG = idCursoCursadoUFG;
        } else {
            throw new IllegalArgumentException("O nome do curso passado por parâmetro não existe.");
        }
    }

    public ProgramaAcademico getProgAcademico() {
        return progAcademico;
    }

    public void setProgAcademico(ProgramaAcademico progAcademico) {
        this.progAcademico = progAcademico;
    }   
    
}
