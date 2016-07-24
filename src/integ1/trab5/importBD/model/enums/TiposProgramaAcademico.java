package integ1.trab5.importBD.model.enums;

/**
 * Enum de tipos de programa acadêmico existentes na UFG.
 *
 * @author gustavosotnas
 */
public enum TiposProgramaAcademico {
    INICIACAO_CIENTIFICA("Iniciação Científica"),
    MONITORIA("Monitoria"),
    EXTENSAO("Extensão"),
    INTERCAMBIO("Intercâmbio");
    
    private final String text;
    
    private TiposProgramaAcademico(final String tipo) {
        this.text = tipo;
    }
    
    public String toString() {
        return text;
    }
    
    /**
     * Função que verifica se a string passsada por parâmetro pertence à enum.
     * 
     * Função reusada de: http://stackoverflow.com/a/1509694
     * 
     * @param aName a string para verificação
     * @return true, caso a string seja um valor na enum, false, caso contrário.
     */
    public static boolean isMember(String aName) {
       TiposProgramaAcademico[] values = TiposProgramaAcademico.values();
       for (TiposProgramaAcademico value : values)
           if (value.text.equals(aName))
               return true;
       return false;
    }
}