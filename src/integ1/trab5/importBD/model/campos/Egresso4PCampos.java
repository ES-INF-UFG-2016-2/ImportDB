package integ1.trab5.importBD.model.campos;

import java.util.Date;

/**
 * Classe que representa os 4 primeiros campos de Egresso.
 *
 */
public class Egresso4PCampos {

    private String id_egresso;
    private String nome_egresso;
    private String tipo_doc_identidade;
    private String num_doc_identidade;
    private Date data_nasc;

    public String getNome_egresso() {
        return nome_egresso;
    }

    public void setNome_egresso(String nome_egresso) {
        this.nome_egresso = nome_egresso;
    }

    public String getTipo_doc_identidade() {
        return tipo_doc_identidade;
    }

    public void setTipo_doc_identidade(String tipo_doc_identidade) {
        this.tipo_doc_identidade = tipo_doc_identidade;
    }

    public String getNum_doc_identidade() {
        return num_doc_identidade;
    }

    public void setNum_doc_identidade(String num_doc_identidade) {
        this.num_doc_identidade = num_doc_identidade;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }
    
    public String getId_egresso(){
        return String.join("-", this.tipo_doc_identidade, this.num_doc_identidade);
    }
}
