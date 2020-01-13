package com.quantum.bean;

import com.quantum.dao.ArchivoDAO;
import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.CamposDAO;
import com.quantum.dao.DistribucionDAO;
import com.quantum.dao.FbleadsDAO;
import com.quantum.dao.GestionDAO;
import com.quantum.modelos.Archivo;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Distribucion;
import com.quantum.modelos.Gestion;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Selectequivalencias;
import com.quantum.servicios.formatoDeFechas;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class SubeprospectosBean {

    private org.slf4j.Logger log = LoggerFactory.getLogger(SubeprospectosBean.class);

    private Mensaje message = new Mensaje(false, "none !important", "");
    private Part file;
    private String folder = "/tmp";
    //private String folder = "c:\\tmp";
    SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private formatoDeFechas fechas = new formatoDeFechas();
    private List<Distribucion> lstDistribucion;
    private List<Asesores> lstasesores;
    private String newasesor;
    private List<Gestion> lstprospectosrepetidos;
    private List<String> lstcampos;
    private String prospectospAsignar;
    private Gestion prospectomodal = null;
    private List<String> campos;

    public List<Asesores> getLstasesores() {
        return lstasesores;
    }

    public void setLstasesores(List<Asesores> lstasesores) {
        this.lstasesores = lstasesores;
    }

    public Gestion getProspectomodal() {
        return prospectomodal;
    }

    public void setProspectomodal(Gestion prospectomodal) {
        this.prospectomodal = prospectomodal;
    }

    public List<Distribucion> getLstDistribucion() {
        return lstDistribucion;
    }

    public void setLstDistribucion(List<Distribucion> lstDistribucion) {
        this.lstDistribucion = lstDistribucion;
    }

    public String getProspectospAsignar() {
        return prospectospAsignar;
    }

    public void setProspectospAsignar(String prospectospAsignar) {
        this.prospectospAsignar = prospectospAsignar;
    }

    public List<Gestion> getLstprospectosrepetidos() {
        return lstprospectosrepetidos;
    }

    public void setLstprospectosrepetidos(List<Gestion> lstprospectosrepetidos) {
        this.lstprospectosrepetidos = lstprospectosrepetidos;
    }

    public String getNewasesor() {
        return newasesor;
    }

    public void setNewasesor(String newasesor) {
        this.newasesor = newasesor;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public List<String> getCampos() {
        return campos;
    }

    public void setCampos(List<String> campos) {
        this.campos = campos;
    }

    public List<String> getLstcampos() {
        return lstcampos;
    }

    public void setLstcampos(List<String> lstcampos) {
        this.lstcampos = lstcampos;
    }

    public void cargArchivo() throws IOException, Exception {
        FbleadsDAO dao;
        CamposDAO daocampo;
        ArchivoDAO daoarchivo;
        Mensaje mensaje;
        Archivo archivo;
        try (InputStream input = file.getInputStream()) {
            dao = new FbleadsDAO();
            daocampo = new CamposDAO();
            daoarchivo = new ArchivoDAO();
            String fileName = file.getSubmittedFileName();
            Files.copy(input, new File(folder, fileName).toPath());
            //mensaje = this.manejaArchivoTest(folder + "\\" + fileName);
            this.manejaArchivoTest(folder + "/" + fileName);
            //this.manejaArchivoTest(folder + "\\" + fileName);// local
            //message = mensaje;
            this.lstcampos = daocampo.lstcampos();

            archivo = new Archivo(fileName, fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA), folder + "/" + fileName, "false");
            daoarchivo.registrar(archivo);

        } catch (IOException e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            e.printStackTrace();
        }
    }

    public void validacion(FacesContext context, UIComponent component, Object value) {
        Part file = (Part) value;
        if (file.getSize() > 11) {
            throw new ValidatorException(new FacesMessage("File is too large"));
        }
        if (!file.getContentType().equals("text/plain")) {
            throw new ValidatorException(new FacesMessage("File is not a text file"));
        }
    }

    public int contarCaracteres(String cadena, String caracter) {
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;

    }

    public String formatText(String texto) {
        String new_text = null;
        String caracter = ":";

        String texsin = texto.replaceAll("\"", "");
        if (contarCaracteres(texsin, caracter) == 1) {
            String[] palabra = texsin.split(caracter);
            new_text = "'" + palabra[1] + "'";
        } else {
            new_text = "'" + texsin + "'";
        }
        return new_text;
    }

    public void manejaArchivoTest(String archivo) throws Exception {
        FbleadsDAO dao;
        Mensaje mensaje;
        try {
            dao = new FbleadsDAO();
            FileInputStream file = new FileInputStream(archivo);
            // Crear el objeto que tendra el libro de Excel	
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            /*	
	 * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.	
	 * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator	
	 * que nos permite recorrer cada una de las filas que contiene.	
             */
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            List<String> cabecera = new ArrayList<String>();
            List<String> query = new ArrayList<String>();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // Obtenemos el iterator que permite recorres todas las celdas de una fila	
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (row.getRowNum() == 0) {
                        cabecera.add("\"" + cell.getStringCellValue() + "\"");
                    }
                }
            }

            workbook.close();

            campos = cabecera;

        } catch (FileNotFoundException e) {
            log.info(e.getMessage());

            System.out.println(e.getMessage().toString());
        } catch (IOException e) {
            log.info(e.getMessage());

            System.out.println(e.getMessage().toString());
        }
    }

    public Mensaje procesaArchivo(String archivo, List<Selectequivalencias> Lstselectequivalencia) throws Exception {
        FbleadsDAO dao;
        Mensaje mensaje = null;

        try {
            dao = new FbleadsDAO();
            SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            FileInputStream file = new FileInputStream(archivo);
            // Crear el objeto que tendra el libro de Excel	
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            /*	
	 * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.	
	 * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator	
	 * que nos permite recorrer cada una de las filas que contiene.	
             */
            List<Integer> posiciones = new ArrayList<Integer>();
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            List<String> headerArry = new ArrayList<String>();
            List<String> ArregloCabeceraExcel = new ArrayList<String>();
            List<String> query = new ArrayList<String>();
            StringBuilder headerString = new StringBuilder();
            StringBuilder querys = new StringBuilder();
            int indexcampoid = 0;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // Obtenemos el iterator que permite recorres todas las celdas de una fila	
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (row.getRowNum() == 0) {
                        headerArry.add(cell.getStringCellValue());
                        ArregloCabeceraExcel.add(cell.getStringCellValue());
                    }
                }
            }

            for (int i = 0; i < Lstselectequivalencia.size(); i++) {
                if (Lstselectequivalencia.get(i).getCampo().equals("IGNORAR")) {
                    posiciones.add(i);
                }
                if(Lstselectequivalencia.get(i).getCampo().equals("id")){
                    indexcampoid = i;
                }
            }

            String headerString2 = "";

            if (posiciones != null) {
                for (int i = 0; i < Lstselectequivalencia.size(); i++) {
                    if (!posiciones.contains(i)) {
                        if (i == 0) {
                            headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                        } else {
                            headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                        }
                        headerString2 = headerString.toString() + "fecha_insert, repite";
                    }
                }
            } else {
                for (int i = 0; i < Lstselectequivalencia.size(); i++) {
                    if (i == Lstselectequivalencia.size() - 1) {
                        headerString.append(Lstselectequivalencia.get(i).getCampo() + ", fecha_insert, repite");
                    } else if (i == 0) {
                        headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                    } else {
                        headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                    }
                    headerString2 = headerString.toString();
                }
            }

            for (Row fila : sheet) {

                String recordString2 = "";
                int contador = 0;
                List<String[]> lineadelineas = new ArrayList<String[]>();
                if (fila.getRowNum() != 0) {
                    String[] linea = new String[fila.getLastCellNum()];
                    for (int cn = 0; cn < fila.getLastCellNum(); cn++) {

                        Cell cell = fila.getCell(cn, Row.CREATE_NULL_AS_BLANK);

                        if (cn == fila.getLastCellNum() - 1) {
                            linea[cn] = "'" + this.retornacelda(cell) + "'";
                            lineadelineas.add(linea);
                        } else if (cn == 0) {
                            linea[cn] = "'" + this.retornacelda(cell) + "'";
                        } else {
                            linea[cn] = "'" + this.retornacelda(cell) + "'";
                        }
                    }
                    
                    
                    
                    for (int i = 0; i < lineadelineas.size(); i++) {
                        if (lineadelineas.get(i)[indexcampoid].equals("'NULL'")) {
                            lineadelineas.remove(i);
                        }else{
                            contador++;
                        }
                    }
                    
                    for (int i = 0; i < lineadelineas.size(); i++) {
                        
                        StringBuilder recordString = new StringBuilder();

                        for (int j = 0; j < lineadelineas.get(i).length; j++) {
                            if (j == lineadelineas.get(i).length - 1) {
                                recordString.append(lineadelineas.get(i)[j] + ",'" + fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA_SIN_SEGUNDOS_NI_T) + "','FALSE')");
                            } else if (j == 0) {
                                recordString.append("(" + lineadelineas.get(i)[j] + ",");
                            } else {
                                recordString.append(lineadelineas.get(i)[j] + ",");
                            }

                            recordString2 = recordString.toString().replaceAll("'NULL'", "NULL");
                        }
                      /*  log.info(""+i);
                        if (i == lineadelineas.size() - 1) {
                            query.add(recordString2 + ";");
                        } else if (i == 0) {
                            query.add(recordString2);
                        } else {
                            query.add(recordString2 + ", ");
                        }*/
                      if(i==0){
                        query.add(recordString2);
                      }else if(i == lineadelineas.size()){
                        query.add(recordString2 + ";");
                      }else{
                        query.add(recordString2 + ", ");
                      }
                              
                    }
                }
            }
           
            workbook.close();

            for (String consulta : query) {
                querys.append(consulta);
            }
            
            String nwquerys = querys.toString().replaceAll("\\)\\(", "),(");
            
            mensaje = dao.registrar(headerString2, nwquerys+";");

            if (mensaje.getClase().equals("success")) {
                Mensaje msjanuncio = dao.anunciosfaltantes();
                Mensaje msjconjunto = dao.conjuntosfaltantes();

                Mensaje msjprospectos = dao.insertaraprospectos(fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA_SIN_SEGUNDOS_NI_T));

                mensaje.setMensaje(mensaje.getMensaje() + "</br>" + msjanuncio.getMensaje() + "</br>" + msjconjunto.getMensaje() + "</br>" + msjprospectos.getMensaje());
            }

            Files.deleteIfExists(Paths.get(archivo));

        } catch (FileNotFoundException e) {
            log.info(e.getMessage());

            mensaje = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        } catch (IOException e) {
            log.info(e.getMessage());

            mensaje = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
        return mensaje;
    }

    public String retornacelda(Cell cell) {
        String celda;

        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                celda = sd2.format(cell.getDateCellValue());
            } else {
                DataFormatter formatter = new DataFormatter();
                String fecha = formatter.formatCellValue(cell);
                celda = fecha.toString().replace("-", "").replace("(", "").replace(")", "").replace(" ", "").replace("+", "");
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            celda = cell.toString().replace("'", "").replace("´", "").replace("`", "");
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            celda = "NULL";
        } else {
            celda = "NULL";
        }
        return celda;
    }

    public void listar() throws Exception {
        DistribucionDAO dao;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new DistribucionDAO();
                lstDistribucion = dao.listarsup();
                prospectospAsignar = dao.cantprospectos(usuariosBean.getAsesorVali());
            } catch (Exception e) {
                log.info(e.getMessage());

                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listaradmin() throws Exception {
        DistribucionDAO dao;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;
        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;

            try {
                dao = new DistribucionDAO();
                lstDistribucion = dao.listarsup();
                prospectospAsignar = dao.cantprospectossa(usuariosBean.getAsesorVali());
                lstprospectosrepetidos = dao.listarrepetidos();
            } catch (Exception e) {
                log.info(e.getMessage());

                throw e;
            }
        } else {
            this.finalSession();
        }
    }

    public void operar() throws Exception {
        DistribucionDAO dao;
        Mensaje mensaje;
        try {
            dao = new DistribucionDAO();
            mensaje = dao.asignasuper(lstDistribucion);
            message = mensaje;
            //dao.actualizarprospectos();
        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            throw e;
        }

    }

    public void leerIdtoGestion(Gestion prospecto, HttpSession session) throws Exception, IOException {
        GestionDAO dao;
        AsesoresDAO daoAse;
        FacesContext contex = FacesContext.getCurrentInstance();
        Gestion gestion;
        try {
            dao = new GestionDAO();
            daoAse = new AsesoresDAO();
            gestion = dao.consultagestion(prospecto.getCodigo());

            session.setAttribute("prospectodeasesor", daoAse.buscasesor(gestion.getCod_asesor()));

            session.setAttribute("lstcaptura", lstprospectosrepetidos);
            session.setAttribute("gestionparam", gestion);
            session.setAttribute("viene", "subeprospectos");
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/gestion.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }

    }

    public void pasaProspecto(Gestion prospecto) throws Exception, IOException {
        GestionDAO dao;
        AsesoresDAO daoasesor;

        try {
            dao = new GestionDAO();
            daoasesor = new AsesoresDAO();
            prospectomodal = dao.consultagestion(prospecto.getCodigo());

            lstasesores = daoasesor.listarporasignar(prospectomodal.getCod_asesor());
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }

    }

    public void cambiar() throws Exception {

        GestionDAO dao;
        Mensaje mensaje;
        try {
            dao = new GestionDAO();

            message = dao.actualizasesor(prospectomodal, newasesor);
            newasesor = null;
        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            throw e;
        }

    }

    public void checkok(Gestion prospecto) throws Exception {

        GestionDAO dao;
        Mensaje mensaje;
        try {
            dao = new GestionDAO();

            message = dao.checkokasesor(prospecto);
            newasesor = null;
        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            throw e;
        }

    }

    public void finalSession() throws Exception {
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().invalidateSession();
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath());
    }
    /*public Mensaje procesaArchivo(String archivo, List<Selectequivalencias> Lstselectequivalencia) throws Exception {
        FbleadsDAO dao;
        Mensaje mensaje = null;

        try {
            dao = new FbleadsDAO();
            SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            FileInputStream file = new FileInputStream(archivo);
            // Crear el objeto que tendra el libro de Excel	
            XSSFWorkbook workbook = new XSSFWorkbook(file);
           
            List<Integer> posiciones = new ArrayList<Integer>();
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            List<String> headerArry = new ArrayList<String>();
            List<String> ArregloCabeceraExcel = new ArrayList<String>();
            List<String> query = new ArrayList<String>();
            StringBuilder headerString = new StringBuilder();
            StringBuilder querys = new StringBuilder();

            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // Obtenemos el iterator que permite recorres todas las celdas de una fila	
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (row.getRowNum() == 0) {
                        headerArry.add(cell.getStringCellValue());
                        ArregloCabeceraExcel.add(cell.getStringCellValue());
                    }
                }
            }

            for (int i = 0; i < Lstselectequivalencia.size(); i++) {
                if (Lstselectequivalencia.get(i).getCampo().equals("IGNORAR")) {
                    posiciones.add(i);
                }
            }

            String headerString2 = "";

            if (posiciones != null) {
                for (int i = 0; i < Lstselectequivalencia.size(); i++) {
                    if (!posiciones.contains(i)) {
                        if (i == 0) {
                            headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                        } else {
                            headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                        }
                        headerString2 = headerString.toString() + "fecha_insert, repite";

                    }

                }
            } else {
                for (int i = 0; i < Lstselectequivalencia.size(); i++) {
                    if (i == Lstselectequivalencia.size() - 1) {
                        headerString.append(Lstselectequivalencia.get(i).getCampo() + ", fecha_insert, repite");
                    } else if (i == 0) {
                        headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                    } else {
                        headerString.append(Lstselectequivalencia.get(i).getCampo() + ", ");
                    }
                    headerString2 = headerString.toString();

                }

            }

            for (Row fila : sheet) {

                // Obtenemos el iterator que permite recorres todas las celdas de una fila	
                List<String> listaFilas = new ArrayList<String>();
                String recordString2 = "";
                if (fila.getRowNum() != 0) {
                    for (int cn = 0; cn < fila.getLastCellNum(); cn++) {
                        if (!posiciones.contains(cn)) {
                            // Si falta la celda del archivo, genera una casilla en blanco
                            // (Funciona especificando un MissingCellPolicy)
                            Cell cell = fila.getCell(cn, Row.CREATE_NULL_AS_BLANK);
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    listaFilas.add(sd2.format(cell.getDateCellValue()));
                                } else {
                                    DataFormatter formatter = new DataFormatter();
                                    String celda = formatter.formatCellValue(cell);
                                    listaFilas.add(celda.toString().replace("-","").replace("(", "").replace(")", "").replace(" ", "").replace("+", ""));
                                }
                            }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                listaFilas.add(cell.toString().replace("'","").replace("´", "").replace("`", ""));
                            }else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
                                listaFilas.add("NULL");
                            }else{
                                listaFilas.add("NULL");
                            }
                       }

                    }

                    StringBuilder recordString = new StringBuilder();

                    for (int i = 0; i < listaFilas.size(); i++) {
                        if (i == listaFilas.size() - 1) {
                            recordString.append(listaFilas.get(i) + "','" + fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA_SIN_SEGUNDOS_NI_T) + "','FALSE') ");
                        } else if (i == 0) {
                            recordString.append("('" + listaFilas.get(i) + "','");
                        } else {
                            recordString.append(listaFilas.get(i) + "','");
                        }
                        recordString2 = recordString.toString().replaceAll("'NULL'", "NULL");
                    }
                }

                if (fila.getRowNum() == sheet.getLastRowNum()) {
                    query.add(recordString2 + ";");
                } else if (fila.getRowNum() == 0) {
                    query.add(recordString2);
                } else {
                    query.add(recordString2 + ", ");
                }

            }

            
            workbook.close();

            for (String consulta : query) {
                querys.append(consulta);
            }
            log.info(headerString2);
            log.info(querys.toString());
            mensaje = dao.registrar(headerString2, querys);
            
            if (mensaje.getClase().equals("success")) {
                Mensaje msjanuncio = dao.anunciosfaltantes();
                Mensaje msjconjunto = dao.conjuntosfaltantes();
                
                Mensaje msjprospectos = dao.insertaraprospectos(fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA_SIN_SEGUNDOS_NI_T));
                
                mensaje.setMensaje(mensaje.getMensaje()+ "</br>"+ msjanuncio.getMensaje()+"</br>"+ msjconjunto.getMensaje()+"</br>"+msjprospectos.getMensaje());
            } 
            
            Files.deleteIfExists(Paths.get(archivo));
            
        } catch (FileNotFoundException e) {
            log.info(e.getMessage());

            mensaje = new Mensaje("",e.getMessage(), "mdi-close-circle-outline", "danger");
            
        } catch (IOException e) {
            log.info(e.getMessage());

            mensaje = new Mensaje("",e.getMessage(), "mdi-close-circle-outline", "danger");
        }
        return mensaje;
    }*/
}
