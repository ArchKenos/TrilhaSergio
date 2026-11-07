# Especificação de Requisitos de Software (SRS)
## Sistema de Gerenciamento de Eventos — Porto Alegre

---

### 1. Introdução

#### 1.1 Objetivo
Definir os requisitos funcionais, não funcionais e regras de negócio para o Sistema de Gerenciamento de Eventos em Java, desenvolvido em console utilizando o padrão arquitetural MVC (Model-View-Controller). A aplicação tem como foco a divulgação, acompanhamento e notificação de eventos culturais, esportivos, gastronômicos e corporativos na cidade de Porto Alegre.

#### 1.2 Escopo
O sistema opera em modo console (terminal) e gerencia informações em tempo de execução via memória (`ArrayList`), sem dependência de banco de dados relacional externo.

---

### 2. Requisitos Funcionais (RF)

| Código | Nome | Descrição |
| :--- | :--- | :--- |
| **RF01** | **Cadastro de Usuário** | O sistema deve permitir o cadastro de usuários contendo no mínimo 4 atributos: Nome completo, E-mail, Telefone/WhatsApp e Categoria de Maior Interesse. |
| **RF02** | **Cadastro de Evento** | O sistema deve permitir o registro de eventos contendo obrigatoriamente: Nome, Endereço em Porto Alegre, Categoria delimitada, Horário (data/hora) e Descrição. |
| **RF03** | **Delimitação de Categorias** | As categorias para registro de eventos devem ser pré-definidas pelo sistema (`Show`, `Festival`, `Teatro`, `Gastronomia`, `Esporte`, `Conferência`). |
| **RF04** | **Listagem Geral de Eventos** | O sistema deve listar todos os eventos cadastrados, ordenados cronologicamente por horário de início. |
| **RF05** | **Consulta por Categoria** | O sistema deve permitir a filtragem de eventos cadastrados de acordo com a categoria selecionada pelo usuário. |
| **RF06** | **Sistema de Notificações** | O sistema deve emitir avisos no console indicando: eventos que estão ocorrendo no momento (tolerância de até 2h) e eventos com início previsto para as próximas 48 horas. |
| **RF07** | **Listagem de Usuários** | O sistema deve permitir a visualização de todos os usuários registrados e suas respectivas categorias de interesse. |

---

### 3. Requisitos Não Funcionais (RNF)

| Código | Categoria | Descrição |
| :--- | :--- | :--- |
| **RNF01** | **Arquitetura** | O sistema deve adotar estritamente o padrão arquitetural MVC (Model-View-Controller) com divisão clara entre dados, interface e controle. |
| **RNF02** | **Robustez** | O sistema deve tratar entradas inválidas no console (como letras em campos numéricos ou formatos incorretos de data) sem encerrar inesperadamente a execução. |
| **RNF03** | **Portabilidade** | A aplicação deve executar em qualquer ambiente compatível com Java Standard Edition (SE) 17 ou superior, sem necessidade de bibliotecas externas adicionais. |
| **RNF04** | **Usabilidade** | Menus e mensagens devem ser intuitivos, claros e apresentados no padrão de terminal em português do Brasil. |

---

### 4. Regras de Negócio (RN)

* **RN01 (Localidade):** Todos os eventos cadastrados devem ter endereço situado no município de Porto Alegre/RS.
* **RN02 (Validação de Horário):** O horário deve respeitar o formato `dd/MM/yyyy HH:mm`. Datas inválidas (ex: 31/02) devem ser rejeitadas.
* **RN03 (Janela de Notificação):** Eventos considerados "próximos" são aqueles com início entre a data/hora atual e até 48 horas no futuro.
* **RN04 (Evento em Andamento):** Considera-se em andamento qualquer evento cujo horário de início já passou, mas ainda está dentro de uma janela de 2 horas.
