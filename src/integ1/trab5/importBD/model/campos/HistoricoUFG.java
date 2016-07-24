package integ1.trab5.importBD.model.campos;

/**
 * Classe que representa todos os campos de Histórico do Egresso na UFG.
 *
 * @author gustavosotnas
 */
public class HistoricoUFG {
    private int mesAnoIngresso;
    private int mesAnoConclusao;
    private int numMatriculaNoCurso;
    private String tituloTrabalhoFinal;

    public int getMesAnoIngresso() {
        return mesAnoIngresso;
    }

    public void setMesAnoIngresso(int mesAnoIngresso) {
        this.mesAnoIngresso = mesAnoIngresso;
    }

    public int getMesAnoConclusao() {
        return mesAnoConclusao;
    }

    public void setMesAnoConclusao(int mesAnoConclusao) {
        this.mesAnoConclusao = mesAnoConclusao;
    }

    public int getNumMatriculaNoCurso() {
        return numMatriculaNoCurso;
    }

    public void setNumMatriculaNoCurso(int numMatriculaNoCurso) {
        this.numMatriculaNoCurso = numMatriculaNoCurso;
    }

    public String getTituloTrabalhoFinal() {
        return tituloTrabalhoFinal;
    }

    public void setTituloTrabalhoFinal(String tituloTrabalhoFinal) {
        this.tituloTrabalhoFinal = tituloTrabalhoFinal;
    }
}