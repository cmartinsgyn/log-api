package br.com.system.modulos.log;

import br.com.system.exception.MyRunTimeException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository repository;

    private InputStream in;


    @Transactional(rollbackFor = Throwable.class)
    public void processarArquivo(MultipartFile file) throws IOException {
        try{
            Log log = new Log();
            //aqui vai anexar o arquivo na entidade log
            in = file.getInputStream();
            if (in != null) {

                /**faz uma busca no arquivo e reparte nas colunas na seguinte ordem:
                 * 1 - Sigma, 2 - Arma */
                List<String> arquivo = IOUtils.readLines(in, "UTF-8");

                List<Log> lista = new ArrayList<>();

                arquivo.stream().forEach(t -> {final String[] partes = t.replaceAll(";", ",")
                            .replaceAll("\"", "").split(",");
                    Log logLista = new Log();
                    logLista.setConteudo(partes[0].toUpperCase().concat("").concat(partes[3]).concat("")
                            .concat(partes[4]));

                    lista.add(logLista);
                });
                System.out.println(lista);

            }

        } catch (MyRunTimeException e) {
            throw new MyRunTimeException("Ops! Ocorreu o seguinte erro ao processar o arquivo: "
                    + e.getMessage());
        }

    }


}
