import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ServletOpinion extends HttpServlet {
    // Declaración de variables miembro correspondientes a
    //los campos del formulario
    private String nombre=null;
    private String apellidos=null;
    private String opinion=null;
    private String comentarios=null;
    // Este método se ejecuta una única vez (al ser inicializado el servlet)
    // Se suelen inicializar variables y realizar operaciones costosas en
    //tiempo de ejecución (abrir ficheros, bases de datos, etc)
    public void init(ServletConfig config) throws ServletException {
    // Llamada al método init() de la superclase (GenericServlet)
    // Así se asegura una correcta inicialización del servlet
    super.init(config);
    System.out.println("Iniciando ServletOpinion...");
    } // fin del método init()
    // Este método es llamado por el servidor web al "apagarse" (al hacer
    //shutdown). Sirve para proporcionar una correcta desconexión de una
    //base de datos, cerrar ficheros abiertos, etc.
    public void destroy() {
    System.out.println("No hay nada que hacer...");
    } //fin del método destroy()
    //Método llamado mediante un HTTP POST. Este método se llama
    //automáticamente al ejecutar un formulario HTML

    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Adquisición de los valores del formulario a través del objeto req
        nombre=req.getParameter("nombre");
        apellidos=req.getParameter("apellidos");
        opinion=req.getParameter("opinion");
        comentarios=req.getParameter("comentarios");
        //Devolver al usuario una página HTML con los valores adquiridos
        devolverPaginaHTML(resp);
    } // fin del método doPost()
    public void devolverPaginaHTML(HttpServletResponse resp) {
    //En primer lugar se establece el tipo de contenido MIME de la respuesta
    resp.setContentType("text/html");
    //Se obtiene un PrintWriter donde escribir (sólo para mandar texto)
    PrintWriter out = null;
    try {
    out=resp.getWriter();
    } catch (IOException io) {
    System.out.println("Se ha producido una excepcion");
    }
    //Se genera el contenido de la página HTML
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Valores recogidos en el formulario</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<b><font size=+2>Valores recogidos del ");
    out.println("formulario: </font></b>");
    out.println("<p><font size=+1><b>Nombre: </b>"+nombre+"</font>");
    out.println("<br><fontsize=+1><b>Apellido: </b>"
    +apellidos+"</font><b><font size=+1></font></b>");
    out.println("<p><font size=+1> <b>Opini&oacute;n: </b><i>" + opinion +
    "</i></font>");
    out.println("<br><font size=+1><b>Comentarios: </b>" + comentarios
    +"</font>");
    out.println("</body>");
    out.println("</html>");
    //Se fuerza la descarga del buffer y se cierra el PrintWriter,
    //liberando recursos de esta forma. IMPORTANTE
    out.flush();
    out.close();
    } // fin de devolverPaginaHTML()
    //Función que permite al servidor web obtener una pequeña descripción del
    //servlet, qué cometido tiene, nombre del autor, comentarios
    //adicionales, etc.

    public String getServletInfo() {
        return "Este servlet lee los datos de un formulario" +
        " y los muestra en pantalla";
    } // fin del método getServletInfo()
}