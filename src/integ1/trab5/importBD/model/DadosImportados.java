package integ1.trab5.importBD.model;

import java.util.ArrayList;

/**
 * Classe que representa os dados importados do arquivo de texto passado pelo
 * usuário por argumento. Esses dados devem estar previamente validados e 
 * prontos para serem colocados no banco de dados do SempreUFG.
 *
 * @author gustavosotnas
 */
public class DadosImportados {

    private final ArrayList<CursoImport> conjuntoDeRegistros;
    
    public DadosImportados(ArrayList<CursoImport> conjuntoDeRegistros) {
        this.conjuntoDeRegistros = conjuntoDeRegistros;
    }

    public ArrayList<CursoImport> getConjuntoDeRegistros() {
        return conjuntoDeRegistros;
    }
}
