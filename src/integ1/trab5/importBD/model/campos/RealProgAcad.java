package integ1.trab5.importBD.model.campos;

import integ1.trab5.importBD.model.enums.TiposProgramaAcademico;
import java.util.Date;

/**
 * Classe que representa todos os campos de Realização de Programa Acadêmico.
 *
 * @author gustavosotnas
 */
public class RealProgAcad {

    private String tipoProgAcad;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;

    public String getTipoProgAcad() {
        return tipoProgAcad;
    }

    public void setTipoProgAcad(String tipo) {
        
        if (TiposProgramaAcademico.isMember(tipo)) {
            this.tipoProgAcad = tipo;
        }
        else {
            throw new IllegalArgumentException
                ("O tipo de realização de programa acadêmico passado por parâmetro não existe.");
        }
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  
}
