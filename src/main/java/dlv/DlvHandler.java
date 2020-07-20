package dlv;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.io.File;
import java.util.List;
import java.util.Set;

public class DlvHandler {
    protected String encodingResource;
    protected Handler handler;

    // TODO: 14/07/2020 mettero il  tramite una booleana
    public DlvHandler()
    {
        handler = new DesktopHandler(new DLV2DesktopService("src/main/resources/dlv2.exe"));
        setProgram("src/main/resources/conservativo/preflop.txt");

        try{
            ASPMapper.getInstance().registerClass(Scelta.class);
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
    }

    public void setProgram(String string )
    {
        handler.removeAll();
        encodingResource=string;
        InputProgram program=new ASPInputProgram();
        program.addFilesPath(encodingResource);
        handler.addProgram(program);

    }
    public String runProgram()
    {
        Output o=handler.startSync();
        AnswerSets answers=(AnswerSets)o;
        System.out.println(o.getErrors());
        int n=0;
        for(AnswerSet a: answers.getAnswersets())
        {
            try
            {
                for(Object obj:a.getAtoms())
                {
                    if(obj instanceof Scelta)
                    {
                        Scelta s=(Scelta)obj;
                        return  s.getScelta();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }
    public String runProgram1()
    {
        Output o=handler.startSync();
        AnswerSets answers=(AnswerSets)o;
        System.out.println(o.getErrors());
        int n=0;
        for(AnswerSet a: answers.getAnswersets())
        {
            try
            {
                for(Object obj:a.getAtoms())
                {
                    if(obj instanceof profiling)
                    {
                        profiling s=(profiling) obj;
                        return  s.getValue();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }
}
