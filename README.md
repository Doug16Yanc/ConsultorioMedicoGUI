
# Sistema de Consultório Médico

Este é um sistema de consultório médico desenvolvido em Java com interface gráfica Swing e banco de dados MySQL. O sistema permite que pacientes agendem e visualizem consultas, enquanto médicos podem gerenciar essas consultas e visualizar o histórico. Projeto Java realizado para a disciplina de programação orientada a objetos no curso de Análise e Desenvolvimento de Sistemas. O projeto visa automatizar a gestão de um consultório médico, facilitando o acesso e controle das consultas tanto para médicos quanto para pacientes.

## Funcionalidades

### Pacientes
- **Cadastro e Login**: Os pacientes podem se cadastrar e fazer login no sistema.
- **Agendamento de Consultas**: Os pacientes podem agendar consultas com médicos específicos, informando o motivo da consulta.
- **Visualização de Consultas**: Os pacientes podem visualizar suas consultas marcadas, incluindo detalhes como data, horário, médico, motivo e status da consulta (Concluída ou Pendente).

### Médicos
- **Cadastro e Login**: Os médicos podem se cadastrar e fazer login no sistema.
- **Gerenciamento de Consultas**: Os médicos podem visualizar as consultas marcadas para eles.
- **Realização de Consultas**: Os médicos podem marcar uma consulta como realizada ou dispensá-la.
- **Histórico de Consultas**: Os médicos têm acesso ao histórico de todas as consultas realizadas por eles.

## Estrutura do Projeto

### Classes Principais

#### `Paciente`
```java
public class Paciente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
}
```
Classe que representa um paciente no sistema. Cada paciente possui um identificador único (`id`), nome, email, telefone e senha.

#### `Consulta`
```java
public class Consulta {
    private UUID id;
    private String motivo;
    private Timestamp agora;
    private Paciente paciente;
    private Medico medico;
    private boolean status;
}
```
Classe que representa uma consulta médica. Cada consulta possui um identificador (`id`), um motivo, uma data e horário (`agora`) em que a consulta foi marcada, um paciente associado e um médico responsável.

#### `Medico`
```java
public class Medico {
    private int id;
    private String nome;
    private String CRM;
    private String senha;
    private String email;
    private String telefone;
    private String especialidade;
}
```
Classe que representa um médico no sistema. Cada médico possui um identificador (`id`), nome, CRM, senha, email, telefone e especialidade.

### Conexão com o Banco de Dados

A classe `DatabaseConnection` gerencia a conexão com o banco de dados MySQL:

```java
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/healthcare";
    private static final String USER = "USUARIO";
    private static final String PASSWORD = "SENHA";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver JDBC não encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
        return connection;
    }
}
```

### Estrutura do Banco de Dados

O sistema utiliza três tabelas principais:

#### Tabela `paciente`
```sql
CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    senha VARCHAR(255) NOT NULL
);
```

#### Tabela `medico`
```sql
CREATE TABLE medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    CRM VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    especialidade VARCHAR(255) NOT NULL
);
```

#### Tabela `consulta`
```sql
CREATE TABLE consulta (
    id CHAR(36) PRIMARY KEY,
    motivo VARCHAR(255),
    agora TIMESTAMP,
    paciente_id INT,
    medico_id INT,
    status TINYINT(1),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);
```

## Requisitos

- **Java 21 ou superior**
- **MySQL 9.0.1 ou superior**
- **IDE compatível com Java (Eclipse, IntelliJ, NetBeans, etc.)**

## Configuração do Projeto

1. Clone o repositório:
   ```bash
   https://github.com/Doug16Yanc/ConsultorioMedicoGUI.git
   ```
2. Troque a branch para `desenvolvimento`:
   ```bash
   git checkout desenvolvimento
   ```
3. Atualize o repositório local:
   ```bash
   git pull origin desenvolvimento
   ```

4. Configure o banco de dados MySQL:
    - Crie um banco de dados chamado `healthcare`.
    - Coloque as credenciais necessárias do banco de dados.
    - Execute os scripts SQL acima para criar as tabelas necessárias.

5. Abra o projeto na sua IDE Java e configure as bibliotecas:
    - Adicione o driver JDBC (`mysql-connector-java`) no classpath.

6. Execute o projeto.

## Autores
  * [Douglas Holanda](https://github.com/Doug16Yanc)
  * [Wesley Rodrigues](https://github.com/Wesley00s)
