package integ1.trab5.importBD.model;

import java.util.ArrayList;

/**
 * Classe que deve haver um único registro tipo 1 para cada curso que um dado 
 * egresso concluiu na UFG. Pode haver zero ou vários registros tipo 2 para 
 * esse egresso em um curso.
 *
 * @author gustavosotnas
 */
public class CursoImport {
    
    private final RegistroTipo1 regEgressoT1;
    private final ArrayList<RegistroTipo2> regEgressoT2;
    
    public CursoImport
        (RegistroTipo1 registroTipo1, ArrayList<RegistroTipo2> registrosTipo2) {

        this.regEgressoT1 = registroTipo1;

        if (registrosTipo2 != null)
            this.regEgressoT2 = registrosTipo2;
        else
            this.regEgressoT2 = new ArrayList<>();
    }

    public RegistroTipo1 getRegEgressoT1() {
        return regEgressoT1;
    }

    public ArrayList<RegistroTipo2> getRegEgressoT2() {
        return regEgressoT2;
    }
}
