package dlv;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("sceltaAvversario")
public class sceltaAvversario {

    @Param(0)
    private String name;
    @Param(1)
    private String scelta;

    public sceltaAvversario() {
    }

    public sceltaAvversario(String scelta,String name) {this.name=name;this.scelta = scelta;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScelta() {
        return scelta;
    }

    public void setRaise(String raise) {
        this.scelta = scelta;
    }
}
