package br.com.system.modulos.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/arquivoLog", path = "/arquivoLog")
public class ArquivoController {

    @Autowired
    private LogService logService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void salvar(@RequestParam("arquivo") MultipartFile file) {
        try {
            logService.processarArquivo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}//end class
