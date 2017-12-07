package it.minoranza.minorserver.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.Socket;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author razva
 */
public class RunVirtualCommunication extends Thread {

    private final Socket s;
    private volatile boolean finish;

    public RunVirtualCommunication(final Socket s) {
        this.s = s;
        finish=true;
        System.out.println(" * CONNESSIONE ACCETTATA da "+s.getInetAddress()+":"+s.getPort()+" * ");
    }

    @Override
    public final void run() {

        //int i=0;

        while(finish){
            try {
                InputStream is = s.getInputStream();
                StringWriter buffer = new StringWriter();
                IOUtils.copy(is, buffer);

                System.out.println(buffer.toString());
                buffer.close();
                is.close();
                s.close();

                //sleep(7000);

                //finish=i++<3;
                finish=false;

                //join();
                //interrupt();

            } catch (IOException exc) {
                System.err.println("Something went wrong during InputStream");
                exc.printStackTrace();
                finish=false;
            }/* catch (InterruptedException exc){
                exc.printStackTrace();
            }*/
        }

    }

    /*public final void setFinish(final boolean finish) {
    this.finish = finish;
    }*/

}
