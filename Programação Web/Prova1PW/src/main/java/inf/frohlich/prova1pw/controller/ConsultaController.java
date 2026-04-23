package inf.frohlich.prova1pw.controller;

import inf.frohlich.prova1pw.dto.ConsultaDTORequest;
import inf.frohlich.prova1pw.dto.ConsultaDTOResponse;
import inf.frohlich.prova1pw.mapper.ConsultaMapper;
import inf.frohlich.prova1pw.repository.ConsultaRepository;
import inf.frohlich.prova1pw.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaRepository consultaRepository, ConsultaService consultaService) {
        this.consultaRepository = consultaRepository;
        this.consultaService = consultaService;
    }


    @PostMapping
    public ResponseEntity<ConsultaDTOResponse> salvar(@RequestBody ConsultaDTORequest consultaDTO) {
        ConsultaDTOResponse response = consultaService.salvar(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<ConsultaDTOResponse> listar() {
        return ConsultaMapper.toResponseList(consultaRepository.findAll());
    }
}
