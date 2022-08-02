package br.com.system.modulos.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "log")
@Getter
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LogDTO {

    private Long id;

    private String conteudo;

    private Long vezes;
}
