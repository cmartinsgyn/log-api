package br.com.system.modulos.log;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

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
