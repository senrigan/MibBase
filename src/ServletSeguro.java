import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletSeguro extends HttpServlet {

private int contador=0;
private boolean apagandose=false;

    protected synchronized void entrandoEnService() {
	contador++;
    }
    protected synchronized void saliendoDeService() {
	contador--;
    }
protected synchronized void numeroDeServicios() {
    return contador;
}
protected void service(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
    entrandoEnService();
    try {
    super.service(req, resp);
    } finally {
    saliendoDeService();
    }
} // fin del método service()
protected void setApagandose(boolean flag){
    apagandose=flag;
}
protected boolean estaApagandose() {
    return apagandose;
}

public void destroy() {
    // Comprobar que hay servicios en ejecución y en caso afirmativo
    // ordernarles que paren la ejecución
    if(numeroDeServicios()>0)
	setApagandose(true);
    // Mientras haya servicios en ejecución, esperar
    while(numServices()>0) {
	try {
	    Thread.sleep(intervalo);
	} catch(InterruptedException e) {
	} // fin del catch
    } // fin del while
} // fin de destroy()

// Servicio
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
     // Comprobación de que el servidor no se está apagando
       /* for (int i=0; ((i<numeroDeCosasAHacer)&& !estaApagandose()); i++) {
    	try {
    	    // Aquí viene el código
    	} catch(Exception e) {
    	    
    	}
        } // fin del for
        */
    } // fin de doPost()
 } // fin de la clase ServletEjemplo
