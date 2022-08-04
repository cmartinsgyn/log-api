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

                /**faz uma leitura do inputStream e insere as linhas num array de string */
                List<String> arquivo = IOUtils.readLines(in, "UTF-8");
                List<Log> lista = new ArrayList<>();

                /**seta cada linha do arquivo nos campos de log e monta uma lista de LOG*/
                arquivo.stream().forEach(t -> {
                    String linha = t;
                    /*retira data e hora do inicio*/
                    int de = t.indexOf("|");
                    int ate = 31;
                    if(de != -1) {
                        String remove = t.substring(de, ate);
                        linha = t.replace(remove, "");
                    }
                    /**/
                    /*seta na lista de log*/
                    Log logLista = new Log();
                    logLista.setConteudo(linha);
                    logLista.setVezes(1L);
                    /**/

                    /*verificar item repetido*/
                    if(lista.contains(logLista)) {
                        int indice = lista.indexOf(logLista);
                        lista.remove(indice);
                        logLista.setVezes(logLista.getVezes() + 1);
                    }
                    /**/

                    lista.add(logLista);

                });

                salvarListaDeArquivo(lista);

            }

        } catch (MyRunTimeException e) {
            throw new MyRunTimeException("Ops! Ocorreu o seguinte erro ao processar o arquivo: "
                    + e.getMessage());
        }

    }

    public void salvarListaDeArquivo(List<Log> lista){
        try{

            for (Log log: lista)
                repository.save(log);

        }catch (MyRunTimeException e){
            throw new MyRunTimeException("Ops! Ocorreu o seguinte erro ao salvar o arquivo: "
                    + e.getMessage());

        }

    }


}
