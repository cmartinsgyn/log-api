package br.com.system.modulos.log;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/log", path = "/log")
public class LogController {

    @Autowired
    private LogRepository repository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Log salvar(@Valid @RequestBody Log log) {
        return repository.save(log);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> list() {
        var targetListType = new TypeToken<Collection<LogDTO>>(){}.getType();
        return new ModelMapper().map(repository.findAll(), targetListType);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LogDTO findById(@PathVariable Long id) {
        var targetListType = new TypeToken<LogDTO>() {}.getType();
        var log = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Log de código " + id + " não localizada"));
        return new ModelMapper().map(log, targetListType);
    }

}//end class
