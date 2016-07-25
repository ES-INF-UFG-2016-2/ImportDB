package integ1.trab5.importBD.model.campos;

/**
 * Classe que representa o segundo e terceiro campos do Egresso.
 */
public class Egresso2e3Campos {
    
    private String id_egresso;
    private String tipo_doc_identidade;
    private String num_doc_identidade;

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
    
     public void setId_egresso(){
       this.id_egresso =  String.join("-", this.tipo_doc_identidade, this.num_doc_identidade);
    }

    public String getId_egresso() {
        return id_egresso;
    }       
}
