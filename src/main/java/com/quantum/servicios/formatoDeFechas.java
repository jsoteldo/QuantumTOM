package com.quantum.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class formatoDeFechas {
    //Formartos de fecha

    public final SimpleDateFormat FORMATO_FECHA_HORA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final SimpleDateFormat FORMATO_FECHA_HORA_T = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public final SimpleDateFormat FORMATO_FECHA_HORA_SIN_SEGUNDOS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    public final SimpleDateFormat FORMATO_FECHA_HORA_SIN_SEGUNDOS_NI_T = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");
    public final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm:ss");
    public final SimpleDateFormat FORMATO_FECHA_MUESTRA = new SimpleDateFormat("dd-MM-yyyy");
    /**
     * Transforma un tipo Date a String
     *
     * @param fecha cualquier cadena de fecha
     * @param formatoinicial cualquier formato
     * @param formatofinal cualquier formato
     * @return una cadena de texto en el formato que desees de yyyy-mm-dd a
     * yyyy-mm-dd hh:mm:ss
     */
    public String cambiarFormatoFechas(String fecha, SimpleDateFormat formatoinicial, SimpleDateFormat formatofinal) {
        String fechafinal = "";
        System.out.println(fecha);
        try {
            
            fechafinal = formatofinal.format(formatoinicial.parse(fecha));
        } catch (ParseException ex) {
            System.out.println("error en el metodo : cambiarFormatoFechas " + ex);
        }
        return fechafinal;
    }

    /**
     * Transforma un tipo Date a String
     *
     * @param date
     * @param dateFormat
     * @return una cadena de texto en el formato que deseees , yyyy-mm-dd
     * hh:mm:ss
     */
    public String convertirFechaString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public Date convertirFechaDate(String date, SimpleDateFormat dateFormat) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * Calculan el numero de dias que hay entre ambas fechas
     *
     * @param fechafinal
     * @param fechainicial
     * @return dias cantida de dias de diferencia entre la fecha inicial y final
     */
    public int restadefechas(String fechafinal, String fechainicial) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicial = dateFormat.parse(fechainicial);
        Date fechaFinal = dateFormat.parse(fechafinal);
        int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        return dias;
    }

    public String restadehoras(String horafinal, String horainicial) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("H:m:s");

        Date fechaInicial = dateFormat.parse(horainicial);
        Date fechaFinal = dateFormat.parse(horafinal);

        int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);

        int horas = 0;
        String H = "";
        int minutos = 0;
        String m = "";
        String s = "";

        if (diferencia > 3600) {
            horas = (int) Math.floor(diferencia / 3600);
            if (horas < 10) {
                H = "0" + Integer.toString(horas);
            } else {
                H = Integer.toString(horas);
            }
            diferencia = diferencia - (horas * 3600);
        }else{
            H="00";
        }
        if (diferencia > 60) {
            minutos = (int) Math.floor(diferencia / 60);
            if (minutos < 10) {
                m = "0" + Integer.toString(minutos);
            } else {
                m = Integer.toString(minutos);
            }
            diferencia = diferencia - (minutos * 60);
        }else{
            m="00";
        }
        if (diferencia < 10) {
            s = "0" + Integer.toString(diferencia);
        } else {
            s = Integer.toString(diferencia);
        }

        return H + ":" + m + ":" + s;
    }
    
    
    public String sumadehoras(String horafinal, String horainicial) throws ParseException {
        
        String[] tiempo1 = horafinal.split(":");
        String[] tiempo2 = horainicial.split(":");
        float[] total = new float[3];

        for (int i = 0; i < 3; i++) {
            total[i] = Float.parseFloat(tiempo1[i]) + Float.parseFloat(tiempo2[i]);
            System.out.println("total " + total[i]);
        }

        float hora_seg, minuto_seg, seg, horas = 0, adicion;
        float minutos = 0;
        String Hora = "", minuto = "", segundos = "";
        hora_seg = total[0] * 3600;
        minuto_seg = total[1] * 60;
        seg = total[2];
        adicion = hora_seg + minuto_seg + seg;

        if (adicion > 3600) {

            horas = (float) Math.floor(adicion / 3600);
            adicion = adicion - (horas * 3600);
        } else {
            Hora = "00";

        }
        if (adicion > 60) {
            minutos = (float) Math.floor(adicion / 60);
            adicion = adicion - (minutos * 60);
        } else {
            minuto = "00";

        }
        if (adicion == 60) {
            adicion = 0;
            minutos = minutos + 1;
        }
        if (adicion < 10) {
            segundos = "0" + String.valueOf((int) adicion);
        } else {
            segundos = String.valueOf((int) adicion);
        }

        if (horas < 10) {
            Hora = "0" + String.valueOf((int) horas);
        } else {
            Hora = String.valueOf((int) horas);
        }
        if (minutos < 10) {
            minuto = "0" + String.valueOf((int) minutos);
        } else {
            minuto = String.valueOf((int) minutos);
        }

        return Hora + ":" + minuto + ":" + segundos;
        
    }

    /**
     * suma un numero de dias a una fecha
     *
     * @param fecha
     * @param dias numero de dias a incrementar
     * @return fechaComoCadena la fecha con los dias aumentados
     */
    public String sumaDeFechas(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        String fechaComoCadena = FORMATO_FECHA.format(calendar.getTime());
        return fechaComoCadena;
    }

    public Date ultimodiames() {

        Date diaActual = new Date();
        int mes = this.obtenerMes(diaActual);
        int anio = this.obtenerAnio(diaActual);
        int numDias = 0;
        Date ultimodiames = null;

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDias = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDias = 30;
                break;
            case 2:
                if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
                    numDias = 29;
                } else {
                    numDias = 28;
                }
                break;

        }

        try {

            ultimodiames = this.convertirFechaDate(String.valueOf(anio) + "-"
                    + String.valueOf(mes) + "-"
                    + String.valueOf(numDias), FORMATO_FECHA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimodiames;

    }

    public Date primerdiames() throws ParseException {

        Date diaActual = new Date();
        int numDias = 0;
        Date ultimodiames;

        ultimodiames = this.convertirFechaDate(String.valueOf(this.obtenerAnio(diaActual)) + "-"
                + String.valueOf(this.obtenerMes(diaActual)) + "-"
                + String.valueOf(1), FORMATO_FECHA);
        return ultimodiames;
    }

    //Retorna el mes de una fecha
    public int obtenerMes(Date fechactual) {

        if (null == fechactual) {
            return 0;
        } else {
            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(fechactual));
        }
    }

    //Retorna el año de una fecha
    public static int obtenerAnio(Date fecactual) {

        if (null == fecactual) {
            return 0;
        } else {
            String formato = "YYYY";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(fecactual));
        }
    }

}
