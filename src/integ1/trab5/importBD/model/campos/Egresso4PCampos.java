package integ1.trab5.importBD.model.campos;

import java.util.Date;

/**
 * Classe que representa os 4 primeiros campos de Egresso.
 *
 * @author gustavosotnas
 */
public class Egresso4PCampos {
    private String nome;
    private String tipoID;
    private String id;
    private Date dataNasc;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoID() {
        return tipoID;
    }

    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
}