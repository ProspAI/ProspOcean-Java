# ProspOcean

ProspOcean é uma aplicação voltada para mitigar os impactos ambientais das enchentes e promover a sustentabilidade dos oceanos. Utilizando tecnologias avançadas como sensores IoT e algoritmos de visão computacional, a aplicação monitora níveis e qualidade da água em tempo real, classifica resíduos automaticamente e promove a reciclagem eficiente. Além disso, a ProspOcean foca na conservação de espécies marinhas, educação ambiental e engajamento comunitário.

## Funcionalidades

- **Mapa Interativo**: Visualização das áreas impactadas pelas enchentes em tempo real.
- **Monitoramento em Tempo Real**: Coleta e exibição de dados de sensores IoT.
- **Relatórios de Incidentes**: Relato de incidentes de enchentes com localização e descrição.
- **Classificação de Resíduos**: Identificação e categorização automática de resíduos.
- **Educação e Conscientização**: Disponibilização de materiais educativos sobre reciclagem e gestão de resíduos.
- **Campanhas de Limpeza**: Organização e participação em campanhas de limpeza.
- **Doações**: Plataforma para doações para apoiar as operações da ONG.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring HATEOAS
- Bean Validation
- Docker
- Docker Compose
- Oracle Database
- Swagger (SpringDoc OpenAPI)

## Requisitos

- Docker
- Docker Compose
- JDK 17+
- Gradle

## Como Clonar e Executar o Projeto

1. **Clonagem do Repositório**

```bash
git clone https://github.com/ProspAI/ProspOcean-Java.git
cd ProspOcean-Java
```

2. **Construção da Imagem Docker**

```bash
./gradlew clean build
docker-compose build
```

3. **Inicialização dos Contêineres**

```bash
docker-compose up
```

4. **Acesso à Aplicação**

- A aplicação estará rodando em [http://localhost:8080](http://localhost:8080).
- A documentação da API pode ser acessada em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Endpoints e Suas Funcionalidades

### Centros de Reciclagem

- `POST /api/centros-reciclagem`: Cria um novo centro de reciclagem.
- `GET /api/centros-reciclagem`: Lista todos os centros de reciclagem.
- `GET /api/centros-reciclagem/{id}`: Obtém detalhes de um centro de reciclagem por ID.
- `PUT /api/centros-reciclagem/{id}`: Atualiza um centro de reciclagem por ID.
- `DELETE /api/centros-reciclagem/{id}`: Deleta um centro de reciclagem por ID.

### Conteúdos Educacionais

- `POST /api/conteudos-educacionais`: Cria um novo conteúdo educacional.
- `GET /api/conteudos-educacionais`: Lista todos os conteúdos educacionais.
- `GET /api/conteudos-educacionais/{id}`: Obtém detalhes de um conteúdo educacional por ID.
- `PUT /api/conteudos-educacionais/{id}`: Atualiza um conteúdo educacional por ID.
- `DELETE /api/conteudos-educacionais/{id}`: Deleta um conteúdo educacional por ID.

### Espécies Marinhas

- `POST /api/especies-marinhas`: Cria uma nova espécie marinha.
- `GET /api/especies-marinhas`: Lista todas as espécies marinhas.
- `GET /api/especies-marinhas/{id}`: Obtém detalhes de uma espécie marinha por ID.
- `PUT /api/especies-marinhas/{id}`: Atualiza uma espécie marinha por ID.
- `DELETE /api/especies-marinhas/{id}`: Deleta uma espécie marinha por ID.

### Relatórios de Incidentes

- `POST /api/incident-reports`: Cria um novo relatório de incidente.
- `GET /api/incident-reports`: Lista todos os relatórios de incidentes.
- `GET /api/incident-reports/{id}`: Obtém detalhes de um relatório de incidente por ID.
- `PUT /api/incident-reports/{id}`: Atualiza um relatório de incidente por ID.
- `DELETE /api/incident-reports/{id}`: Deleta um relatório de incidente por ID.

### Notificações

- `POST /api/notificacoes`: Cria uma nova notificação.
- `GET /api/notificacoes`: Lista todas as notificações.
- `GET /api/notificacoes/{id}`: Obtém detalhes de uma notificação por ID.
- `PUT /api/notificacoes/{id}`: Atualiza uma notificação por ID.
- `DELETE /api/notificacoes/{id}`: Deleta uma notificação por ID.

### Resíduos

- `POST /api/residuos`: Cria um novo registro de resíduos.
- `GET /api/residuos`: Lista todos os registros de resíduos.
- `GET /api/residuos/{id}`: Obtém detalhes de um registro de resíduos por ID.
- `PUT /api/residuos/{id}`: Atualiza um registro de resíduos por ID.
- `DELETE /api/residuos/{id}`: Deleta um registro de resíduos por ID.

### Dados de Sensores

- `POST /api/sensordata`: Cria um novo dado de sensor.
- `GET /api/sensordata`: Lista todos os dados de sensores.
- `GET /api/sensordata/{id}`: Obtém detalhes de um dado de sensor por ID.
- `PUT /api/sensordata/{id}`: Atualiza um dado de sensor por ID.
- `DELETE /api/sensordata/{id}`: Deleta um dado de sensor por ID.

### Usuários

- `POST /api/usuarios/registrar`: Registra um novo usuário.
- `GET /api/usuarios`: Lista todos os usuários.
- `GET /api/usuarios/{id}`: Obtém detalhes de um usuário por ID.
- `PUT /api/usuarios/{id}`: Atualiza um usuário por ID.
- `DELETE /api/usuarios/{id}`: Deleta um usuário por ID.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para adicionar novas funcionalidades, corrigir bugs ou melhorar a documentação.


Agradecemos por utilizar a ProspOcean! Vamos juntos promover a sustentabilidade dos oceanos.
