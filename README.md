# Projeto Faculdade — Spring Boot com Arquitetura em Camadas

> **Guia escrito para desenvolvedores C#/.NET** que estão tendo o primeiro contato com Java e Spring Boot.
> Cada conceito novo é comparado com o equivalente que você já conhece do mundo .NET.

---

## Sumário

1. [O que é Spring Boot?](#1-o-que-é-spring-boot)
2. [Ferramentas necessárias](#2-ferramentas-necessárias)
3. [Como rodar o projeto](#3-como-rodar-o-projeto)
4. [O arquivo pom.xml — o "csproj" do Java](#4-o-arquivo-pomxml--o-csproj-do-java)
5. [O application.properties — o "appsettings.json" do Java](#5-o-applicationproperties--o-appsettingsjson-do-java)
6. [Lombok — eliminando código repetitivo](#6-lombok--eliminando-código-repetitivo)
7. [Arquitetura em Camadas](#7-arquitetura-em-camadas)
   - [Model (Entidade)](#71-camada-model--entidade)
   - [Repository](#72-camada-repository)
   - [DTO](#73-camada-dto)
   - [Service](#74-camada-service)
   - [Controller](#75-camada-controller)
   - [Exception](#76-camada-exception)
   - [Config](#77-camada-config)
8. [Anotações Spring — o equivalente dos Attributes do C#](#8-anotações-spring--o-equivalente-dos-attributes-do-c)
9. [Injeção de Dependência](#9-injeção-de-dependência)
10. [Banco de dados H2](#10-banco-de-dados-h2)
11. [Fluxo completo de uma requisição](#11-fluxo-completo-de-uma-requisição)
12. [Comparativo geral Java vs C#](#12-comparativo-geral-java-vs-c)

---

## 1. O que é Spring Boot?

**Spring Boot** é para Java o que **ASP.NET Core** é para C#. É um framework que permite criar aplicações web / APIs REST de forma rápida, com configuração mínima e um servidor embutido (Tomcat, equivalente ao Kestrel do .NET).

| Conceito | C# / .NET | Java / Spring Boot |
|---|---|---|
| Framework web | ASP.NET Core | Spring Boot |
| Servidor HTTP embutido | Kestrel | Tomcat |
| ORM | Entity Framework Core | Hibernate (via Spring Data JPA) |
| Injeção de Dependência | Microsoft.Extensions.DI | Spring IoC Container |
| Gerenciador de pacotes | NuGet (`.csproj`) | Maven (`pom.xml`) |
| Arquivo de configuração | `appsettings.json` | `application.properties` |
| Ponto de entrada | `Program.cs` | `ProjetoFaculdadeApplication.java` |

---

## 2. Ferramentas necessárias

Antes de rodar o projeto, certifique-se de ter instalado:

- **Java 17+** → [https://adoptium.net](https://adoptium.net) (equivalente ao SDK do .NET)
- **Maven 3.8+** → [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) (equivalente ao `dotnet CLI`)
- **IntelliJ IDEA** (recomendado) ou VS Code com extensão Java

Para verificar se está tudo instalado, abra o terminal e execute:

```bash
java -version
mvn -version
```

---

## 3. Como rodar o projeto

```bash
# Equivalente a: dotnet run
mvn spring-boot:run
```

A API ficará disponível em: `http://localhost:8080`

Para compilar e gerar o `.jar` (equivalente ao `.exe` / `.dll` do .NET):

```bash
# Equivalente a: dotnet build / dotnet publish
mvn clean package
java -jar target/projeto-faculdade-0.0.1-SNAPSHOT.jar
```

---

## 4. O arquivo `pom.xml` — o "csproj" do Java

No .NET você usa o arquivo `.csproj` para gerenciar dependências (pacotes NuGet) e configurações do projeto. Em Java com Maven, esse papel é do `pom.xml`.

**Comparação direta:**

```xml
<!-- C# — .csproj -->
<PackageReference Include="Microsoft.EntityFrameworkCore" Version="8.0.0" />

<!-- Java — pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Dependências adicionadas neste projeto

| Dependência | Equivalente C# / .NET |
|---|---|
| `spring-boot-starter-web` | `Microsoft.AspNetCore.App` |
| `spring-boot-starter-data-jpa` | `Microsoft.EntityFrameworkCore` |
| `spring-boot-starter-validation` | `FluentValidation` ou `DataAnnotations` |
| `h2` | Banco SQLite em memória para dev |
| `lombok` | Geradores de código (Records, source generators) |
| `spring-boot-starter-test` | `xUnit` / `MSTest` |

> **Detalhe importante:** no Maven, as versões das dependências do Spring Boot são gerenciadas automaticamente pelo bloco `<parent>` no topo do `pom.xml`. Você não precisa especificar a versão de cada uma individualmente — é como herdar de um `Directory.Build.props` no .NET.

---

## 5. O `application.properties` — o "appsettings.json" do Java

O arquivo `src/main/resources/application.properties` centraliza todas as configurações da aplicação, exatamente como o `appsettings.json` no ASP.NET Core.

```properties
# Equivalente a: "Urls": "http://localhost:8080" no appsettings.json
server.port=8080

# Equivalente a: ConnectionStrings.DefaultConnection
spring.datasource.url=jdbc:h2:mem:projetofaculdade

# Equivalente a: configurações do DbContext / migrations
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

A opção `ddl-auto=update` faz o Hibernate criar/atualizar as tabelas automaticamente com base nas suas entidades — similar ao `EnsureCreated()` ou ao `dotnet ef database update` do Entity Framework.

---

## 6. Lombok — eliminando código repetitivo

Em C#, você usa `record` ou propriedades auto-implementadas para evitar escrever getters, setters e construtores manualmente. Em Java, isso costumava ser verboso demais — o **Lombok** resolve isso com anotações.

```java
// Sem Lombok (Java puro — verboso)
public class Exemplo {
    private Long id;
    private String nome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    // construtor, equals, hashCode, toString...
}

// Com Lombok
@Data               // gera getters, setters, equals, hashCode, toString
@Builder            // gera o padrão Builder: Exemplo.builder().nome("x").build()
@NoArgsConstructor  // gera construtor vazio
@AllArgsConstructor // gera construtor com todos os campos
public class Exemplo {
    private Long id;
    private String nome;
}
```

| Lombok | Equivalente C# |
|---|---|
| `@Data` | `record` ou propriedades com `{ get; set; }` |
| `@Builder` | Padrão Builder manual ou `with` em records |
| `@NoArgsConstructor` | `new Exemplo()` |
| `@AllArgsConstructor` | `new Exemplo(id, nome)` |
| `@RequiredArgsConstructor` | Construtor apenas com campos `readonly` |

---

## 7. Arquitetura em Camadas

O projeto segue a arquitetura em camadas clássica, muito comum tanto em .NET quanto em Java. Cada camada tem uma responsabilidade única:

```
[ Controller ]  ←→  Recebe requisições HTTP e retorna respostas
      ↓
[  Service   ]  ←→  Contém a lógica de negócio
      ↓
[ Repository ]  ←→  Acessa o banco de dados
      ↓
[   Model    ]  ←→  Representa as tabelas do banco (Entidades)

[ DTO        ]  ←→  Objetos de transferência de dados (entrada/saída da API)
[ Exception  ]  ←→  Tratamento centralizado de erros
[ Config     ]  ←→  Configurações da aplicação (CORS, Swagger, etc.)
```

---

### 7.1 Camada Model / Entidade

**Arquivo:** `model/Exemplo.java`

Equivalente ao seu **modelo/entidade do Entity Framework Core**. As anotações mapeiam a classe para uma tabela no banco.

```java
@Entity                        // equivalente a: [Table("exemplos")] no EF
@Table(name = "exemplos")
public class Exemplo {

    @Id                                          // equivalente a: [Key]
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // equivalente a: ValueGeneratedOnAdd()
    private Long id;

    @Column(nullable = false)   // equivalente a: [Required]
    private String nome;

    @Column
    private String descricao;
}
```

| Java / JPA | C# / Entity Framework |
|---|---|
| `@Entity` | `[Table]` / configuração no `DbContext` |
| `@Id` | `[Key]` |
| `@GeneratedValue(IDENTITY)` | `ValueGeneratedOnAdd()` |
| `@Column(nullable = false)` | `[Required]` |
| `@OneToMany` | `.HasMany()` no Fluent API |
| `@ManyToOne` | `.HasOne()` no Fluent API |

---

### 7.2 Camada Repository

**Arquivo:** `repository/ExemploRepository.java`

Equivalente ao seu **`DbSet<T>` dentro do `DbContext`**, mas de forma muito mais direta. Você cria uma interface que herda de `JpaRepository` e ganha todos os métodos CRUD automaticamente — sem precisar implementar nada.

```java
@Repository
public interface ExemploRepository extends JpaRepository<Exemplo, Long> {
    // findAll(), findById(), save(), deleteById(), existsById()
    // tudo já está disponível sem escrever uma linha!
}
```

Em C# com EF Core, você precisaria do `DbContext` e chamar `_context.Exemplos.ToListAsync()`. Aqui o Spring já injeta e expõe tudo via interface.

Você também pode criar queries customizadas apenas pelo nome do método:

```java
// Spring gera o SQL automaticamente pelo nome!
List<Exemplo> findByNome(String nome);
List<Exemplo> findByNomeContaining(String texto);
```

Isso é equivalente ao **LINQ** no EF Core, mas usando convenção de nomes.

---

### 7.3 Camada DTO

**Arquivos:** `dto/ExemploRequestDTO.java` e `dto/ExemploResponseDTO.java`

Idêntico ao que você já conhece em C#. DTOs são objetos simples usados para separar o que chega na API do que está no banco.

- **RequestDTO** → o que o cliente envia (corpo do `POST`/`PUT`)
- **ResponseDTO** → o que a API devolve

```java
// ExemploRequestDTO.java — entrada
public class ExemploRequestDTO {

    @NotBlank(message = "O nome é obrigatório")  // equivalente a: [Required]
    private String nome;

    private String descricao;
}

// ExemploResponseDTO.java — saída
@Builder
public class ExemploResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
}
```

| Java (Bean Validation) | C# (DataAnnotations) |
|---|---|
| `@NotBlank` | `[Required]` |
| `@NotNull` | `[Required]` para tipos de referência |
| `@Size(min=2, max=50)` | `[StringLength(50, MinimumLength = 2)]` |
| `@Min(1)` | `[Range(1, int.MaxValue)]` |
| `@Email` | `[EmailAddress]` |

---

### 7.4 Camada Service

**Arquivos:** `service/ExemploService.java` e `service/impl/ExemploServiceImpl.java`

Equivalente ao seu padrão de **Interface + Implementação** (ex.: `IExemploService` + `ExemploService`) que você já usa em C#.

A interface define o **contrato**:

```java
public interface ExemploService {
    List<ExemploResponseDTO> listarTodos();
    ExemploResponseDTO buscarPorId(Long id);
    ExemploResponseDTO criar(ExemploRequestDTO dto);
    ExemploResponseDTO atualizar(Long id, ExemploRequestDTO dto);
    void deletar(Long id);
}
```

A implementação contém a lógica:

```java
@Service               // registra a classe no container de DI do Spring
@RequiredArgsConstructor  // Lombok: gera construtor com os campos final (injeção via construtor)
public class ExemploServiceImpl implements ExemploService {

    private final ExemploRepository exemploRepository;  // injetado automaticamente

    @Override
    public List<ExemploResponseDTO> listarTodos() {
        return exemploRepository.findAll()
                .stream()                        // equivalente ao .AsQueryable() / LINQ
                .map(this::toResponseDTO)        // equivalente ao .Select(x => ToDto(x))
                .collect(Collectors.toList());   // equivalente ao .ToList()
    }

    // ...
}
```

> **`@Service`** diz ao Spring: "registre esta classe como um serviço no container de DI".
> É o equivalente a chamar `builder.Services.AddScoped<IExemploService, ExemploService>()` no `Program.cs`.

---

### 7.5 Camada Controller

**Arquivo:** `controller/ExemploController.java`

É o equivalente exato do seu **`ApiController`** no ASP.NET Core. Expõe os endpoints REST.

```java
@RestController               // equivalente a: [ApiController]
@RequestMapping("/api/exemplos")  // equivalente a: [Route("api/exemplos")]
@RequiredArgsConstructor
public class ExemploController {

    private final ExemploService exemploService;

    @GetMapping               // equivalente a: [HttpGet]
    public ResponseEntity<List<ExemploResponseDTO>> listarTodos() {
        return ResponseEntity.ok(exemploService.listarTodos());
    }

    @GetMapping("/{id}")      // equivalente a: [HttpGet("{id}")]
    public ResponseEntity<ExemploResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(exemploService.buscarPorId(id));
    }

    @PostMapping              // equivalente a: [HttpPost]
    public ResponseEntity<ExemploResponseDTO> criar(@RequestBody @Valid ExemploRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exemploService.criar(dto));
    }

    @PutMapping("/{id}")      // equivalente a: [HttpPut("{id}")]
    public ResponseEntity<ExemploResponseDTO> atualizar(
            @PathVariable Long id,            // equivalente a: [FromRoute]
            @RequestBody @Valid ExemploRequestDTO dto) {  // equivalente a: [FromBody]
        return ResponseEntity.ok(exemploService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")   // equivalente a: [HttpDelete("{id}")]
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        exemploService.deletar(id);
        return ResponseEntity.noContent().build();  // retorna HTTP 204
    }
}
```

| Java Spring | C# ASP.NET Core |
|---|---|
| `@RestController` | `[ApiController]` |
| `@RequestMapping("/rota")` | `[Route("rota")]` |
| `@GetMapping` | `[HttpGet]` |
| `@PostMapping` | `[HttpPost]` |
| `@PutMapping` | `[HttpPut]` |
| `@DeleteMapping` | `[HttpDelete]` |
| `@PathVariable` | `[FromRoute]` |
| `@RequestBody` | `[FromBody]` |
| `@RequestParam` | `[FromQuery]` |
| `ResponseEntity.ok(x)` | `Ok(x)` |
| `ResponseEntity.status(201).body(x)` | `Created(uri, x)` |
| `ResponseEntity.noContent().build()` | `NoContent()` |

---

### 7.6 Camada Exception

**Arquivos:** `exception/RecursoNaoEncontradoException.java`, `exception/ErroDetalhe.java`, `exception/GlobalExceptionHandler.java`

Equivalente ao seu **middleware de tratamento de erros** no ASP.NET Core (o `UseExceptionHandler` ou um `IExceptionFilter`).

```java
// Exceção customizada — equivalente a criar uma Exception customizada em C#
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
```

O `GlobalExceptionHandler` captura exceções de toda a aplicação e devolve respostas JSON padronizadas:

```java
@RestControllerAdvice   // equivalente a um filtro global de exceções
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroDetalhe> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        // monta e retorna um JSON de erro padronizado com status 404
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDetalhe> handleValidacao(MethodArgumentNotValidException ex) {
        // captura erros de validação dos DTOs e retorna 400 com detalhes
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDetalhe> handleGenerico(Exception ex) {
        // fallback para qualquer erro não tratado — retorna 500
    }
}
```

Quando um recurso não é encontrado, a API devolve automaticamente:

```json
{
  "status": 404,
  "erro": "Recurso não encontrado",
  "mensagem": "Exemplo não encontrado com id: 99",
  "timestamp": "2026-03-19T10:30:00"
}
```

---

### 7.7 Camada Config

**Arquivo:** `config/WebConfig.java`

Equivalente ao que você configura no `Program.cs` com `builder.Services.AddCors(...)`.

```java
@Configuration   // equivalente a uma classe de configuração / extension method no Program.cs
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
```

---

## 8. Anotações Spring — o equivalente dos Attributes do C#

Em C# você usa `[Attribute]` para metadados. Em Java, isso se chama **Annotation** e a sintaxe é `@NomeDaAnotacao`. Funcionalmente são a mesma coisa.

| Anotação Java | Attribute C# equivalente | O que faz |
|---|---|---|
| `@SpringBootApplication` | (combina vários) | Habilita auto-configuração, component scan, etc. |
| `@RestController` | `[ApiController]` | Marca a classe como controller REST |
| `@Service` | `[Scoped]` / `AddScoped` | Registra como serviço no container de DI |
| `@Repository` | `[Scoped]` / `AddScoped` | Registra como repositório no container de DI |
| `@Configuration` | `IServiceCollection` extension | Classe de configuração |
| `@Entity` | Configuração do EF | Mapeia classe para tabela |
| `@Transactional` | `[TransactionScope]` | Envolve o método em uma transação |
| `@Valid` | Ativa validação automática | `ModelState.IsValid` automático |

---

## 9. Injeção de Dependência

Em ASP.NET Core você registra no `Program.cs`:

```csharp
builder.Services.AddScoped<IExemploService, ExemploService>();
```

No Spring, **não existe registro manual**. Você apenas anota a classe com `@Service`, `@Repository` ou `@Component`, e o Spring encontra e registra automaticamente todos os beans ao iniciar — isso se chama **Component Scan**.

A injeção acontece via construtor (forma recomendada):

```java
// Em vez de:
// [Inject] / services.AddScoped no Program.cs

// Você usa:
@RequiredArgsConstructor  // Lombok gera o construtor
public class ExemploServiceImpl {
    private final ExemploRepository exemploRepository;  // injetado automaticamente
}
```

O `private final` em Java + `@RequiredArgsConstructor` é equivalente ao `readonly` em C# com injeção via construtor.

---

## 10. Banco de dados H2

O projeto está configurado com o banco **H2**, que é um banco relacional em memória — perfeito para desenvolvimento e testes, sem precisar instalar nada.

- **Os dados são perdidos ao reiniciar a aplicação** (comportamento esperado em dev)
- O console visual do H2 fica em: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:projetofaculdade`
  - Usuário: `sa`
  - Senha: (deixar em branco)

Para trocar para um banco real (PostgreSQL, MySQL, etc.) em produção, basta alterar o `application.properties` e adicionar o driver correspondente no `pom.xml` — sem mudar nenhuma linha de código Java.

---

## 11. Fluxo completo de uma requisição

Veja como funciona um `POST /api/exemplos`:

```
Cliente (Postman / Frontend)
        │
        │  POST /api/exemplos
        │  Body: { "nome": "Teste", "descricao": "..." }
        ▼
┌─────────────────────┐
│   ExemploController  │  @PostMapping — recebe e valida o DTO com @Valid
└─────────┬───────────┘
          │  chama exemploService.criar(dto)
          ▼
┌─────────────────────┐
│  ExemploServiceImpl  │  converte DTO → Model, aplica regras de negócio
└─────────┬───────────┘
          │  chama exemploRepository.save(exemplo)
          ▼
┌─────────────────────┐
│  ExemploRepository   │  Hibernate gera o SQL e persiste no banco H2
└─────────┬───────────┘
          │  retorna Exemplo salvo (com id gerado)
          ▼
┌─────────────────────┐
│  ExemploServiceImpl  │  converte Model → ResponseDTO
└─────────┬───────────┘
          │  retorna ExemploResponseDTO
          ▼
┌─────────────────────┐
│   ExemploController  │  retorna ResponseEntity com status 201 Created
└─────────────────────┘
        │
        ▼
   { "id": 1, "nome": "Teste", "descricao": "..." }
```

---

## 12. Comparativo geral Java vs C#

| Aspecto | C# | Java |
|---|---|---|
| Tipo `string` | `string` (alias) | `String` (classe) |
| Tipo numérico inteiro longo | `long` | `Long` (wrapper) ou `long` (primitivo) |
| Nullable | `string?` | `Optional<String>` |
| LINQ | `.Where()`, `.Select()`, `.ToList()` | `.stream().filter().map().collect()` |
| `async/await` | `async Task<T>` | `CompletableFuture<T>` (não usado aqui) |
| Herança | `: ClasseBase` | `extends ClasseBase` |
| Interface | `: IInterface` | `implements Interface` |
| Namespace | `namespace` | `package` |
| Modificador padrão de acesso | `internal` | `package-private` |
| Convenção de nomenclatura | `PascalCase` métodos | `camelCase` métodos |
| `var` | inferência de tipo | `var` (Java 10+) |

---

*Projeto gerado com Spring Boot 3.2.3 e Java 17.*
