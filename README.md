# log-api
Api do sistema de log que fornece, através de serviços rest, o back and para o sistema log
Para utilizar localmente baixe este repositório
 - Banco de dados postgress
  * altere no arquivo application.properties: 
    spring.datasource.url=jdbc:postgresql://localhost:5432/log (sua porta). 
        Obs caso use docker é a porta do postgres do container
    spring.datasource.username=seu user
    spring.datasource.password=sua password
 - Spring boot 2.7.2
