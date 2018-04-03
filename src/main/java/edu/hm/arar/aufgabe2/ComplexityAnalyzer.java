package edu.hm.arar.aufgabe2;

/**
 * Created by Sümeyye on 03.04.2018.
 */
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/** Interface fuer alle statischen Analyzer.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-21
 */
public interface ComplexityAnalyzer {
    /** Legt das Startdirectory neu fest.
     * Voreingestellt ist das aktuelle Arbeitsdirectory (System-Property user.dir).
     * @param rootdir Neues Startdirectory.
     * @return Dieser Analyzer.
     * @throws IOException wenn rootdir kein Directory ist.
     */
    ComplexityAnalyzer setRootdir(Path rootdir) throws IOException;

    /** Startet die Analyse und liefert die Ergebnisse als Map zurueck.
     * @return Map von FQCNs auf die zyklomatische Komplexitaet.
     */
    Map<String, Integer> analyzeClassfiles() throws IOException;

}
