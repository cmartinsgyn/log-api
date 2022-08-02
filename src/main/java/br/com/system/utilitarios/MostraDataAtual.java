package br.com.system.utilitarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MostraDataAtual {

    /**utilizado para mostrar a data atual em um campo data somente leitura*/
    public static Date getDataDeHoje(){
        Date dataAtual = new Date();
        return dataAtual;
    }

    public static LocalDateTime getDataHoraAtual(){
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        return dataHoraAtual;
    }

    public static String getDataHoraDeHojeFormatada() {
      // data/hora atual
      LocalDateTime agora = LocalDateTime.now();

      // formatar a data
      DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String dataFormatada = formatterData.format(agora);

      // formatar a hora
      DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
      String horaFormatada = formatterHora.format(agora);

      String data = dataFormatada.concat(" ").concat(horaFormatada);

      return data;
    }


}
