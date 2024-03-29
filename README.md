# Sistema Gerenciador de TGs - API 2° Semestre BD

<br>
<p align="center">
    <a href="#equipe">Equipe</a> |
    <a href="#context">Contextualização</a>  |
    <a href="#requisitos">Requisitos</a>  |
    <a href="#backlog">Product Backlog</a>  |
    <a href="#entregas">Sprints</a>  |
    <a href="#tecnologias">Tecnologias</a> |
    <a href="#burndown">Burndown</a>
</p>

<div align="center"><hr width=70%></div>

<span id="equipe">

## ◻️ Equipe

<div align="left">
  
  | **FUNÇÃO** | **NOME** | **REDES SOCIAIS** |
  | :---: | :--- | :---: |
  | Product Owner | Beatriz Akemi | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/beatriz-bonatto-263530156) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/BeatrizBonatto) |
  | Scrum  Master | André Bernardes | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/andre-oliveira2004) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/Andre-Bernardes200) | 
  | Developer | Naiara Santos | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/naiara-santos-73b83a186) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/NaiaraSantos3) |  
  | Developer | João Matheus Lamão | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/joaomatheuslamao) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/JoaoMatheusLamao) |  
  | Developer | Lucas Henrique de Oliveira | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/lucas-henrique-9a557620b) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/LucasHCOliveira7) |  
  | Developer | Eduardo Farias | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/eduardofariasp/) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/eduardofpaula) |   
  | Developer | Ana Raquel Machado | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/ana-sasaki-19a2031b8/) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/Anaraquely) |      
</div>

<div align="left"><hr width=70%></div>

<span id="context">

## ◻️ Contextualização

> Muitos desafios no gerenciamento de Trabalho de Graduação (TG's) estão sendo enfrentados atualmente no curso de Análise e Desenvolvimento de Sistemas, na unidade da FATEC - São José dos Campos, por não se ter um sistema prático e efciente para realizar a gestão. Com isso, se tem dificuldades na organização e acompanhamentos do progresso dos alunos.

> Para que seja possível sanar esses problemas, foi proposto como ferramenta, a criação de um Sistema de Gerenciamento de Banco de Dados, que será desenvolvido com o foco de simplificar e centralizar toda a administração dos TGs, permitindo o acompanhamento eficiente durante as etapas, prazos de entrega e status dos projetos.

<div align="left"><hr width=70%></div>

<span id="requisitos">

## ◻️ Requisitos Funcionais

- Importação de informações de alunos, orientadores e TGs;
- Lançamento de notas de atividades;
- Gestão de alunos, orientadores, TGs e entregas;
- Geração de relatórios para acompanhamento de entregas, agendamento de bancas de <br>
defesa, fechamento de semestre e transporte de notas.

<div align="left"><hr width=70%></div>

<span id="backlog">

## ◻️ Product Backlog

<table>
    <tr>
            <th>ÉPICO</th>
            <th>USER STORY</th>
            <th>PRIORIDADE</th>
            <th>SPRINT</th>
        </tr>
        <tr>
        <tr>
            <td rowspan="3">Dados</td>
            <td>Como professor, eu quero poder carregar um arquivo CSV (gerado a partir de um formulário preenchido pelos alunos) e ao ser exportado todas as informações de um aluno, ficam atribuídas em uma única linha, para que os dados sejam separados de forma automática, alimentando um Banco de Dados.</td>
            <td>1</td>
            <td>2</td>
        </tr>
        <tr>
            <td>Como professor, quero poder armazenas os dados fornecidos pelos alunos a partir do arquivo CSV, podendo também ler, atualizar e excluir informações.</td>
            <td>2</td>
            <td>2</td>
        </tr>
        <tr>
            <td>Como professor, eu quero poder tratar as informações de alunos, orientadores e os tipos de TG’s importadas do CSV, para que eu possa economizar tempo e esforço.</td>
            <td>3</td>
            <td>2</td>
        </tr>
        <tr>
            <td>Controle de entregas</td>
            <td>Como professor, eu quero poder cadastrar, editar e excluir entregas, para que seja possível gerar um histórico e acompanhar o andamento durante as etapas até serem finalizadas.</td>
            <td>4</td>
            <td>2</td>
        </tr>
        <tr>
            <td rowspan="2">Entregas</td>
            <td>Como professor, eu quero poder lançar notas de atividades para ter um acompanhamento das entregas efetivas, a fim de facilitar o fechamento das notas e contribuir na verificação/possibilidade do aluno defender seu trabalho ao final do semestre.</td>
            <td>5</td>
            <td>3</td>
        </tr>
        <tr>
            <td>Como professor, eu quero realizar feedbacks por entrega, para facilitar nas avaliações e acompanhamento do desenvolvimento do aluno durante o semestre.</td>
            <td>6</td>
            <td>3</td>
        </tr>
        <tr>
            <td>Geração de relatórios</td>
            <td>Como professor quero gerar relatório de fechamento das notas, para que seja possível extrair as médias entre elas, gerando a nota final de cada aluno.</td>
            <td>7</td>
            <td>4</td>
        </tr>
        <tr>
            <td>Gestão de orientadores</td>
            <td>Como professor, eu quero ter a relação dos orientadores de cada aluno matriculado, para me certificar que esse acompanhamento, entre as partes envolvidas,está sendo feito.</td>
            <td>8</td>
            <td>4</td>
        </tr>
        <tr>
            <td>Geração de relatórios</td>
            <td>Como professor, quero gerar relatório com dados de entregas, notas e se o aluno está apto a defender o seu trabalho, para assim poder realizar o agendamento com a banca.</td>
            <td>9</td>
            <td>3</td>
        </tr>
        <tr>
            <td>Gestão de alunos</td>
            <td>Como professor, desejo poder visualizar os alunos matriculados nos TG’s para monitorar a execução das atividades.</td>
            <td>10</td>
            <td>4</td>
        </tr>
        <tr>
            <td>Geração de relatórios</td>
            <td>Como professor quero gerar relatório de acompanhamento das entregas, para auxiliar na avaliação de desempenho do aluno.</td>
            <td>11</td>
            <td>3</td>
        </tr>
        <tr>
            <td>Documentação</td>
            <td>Como professor, quero ter um meio de consulta  e requisitos de funcionamento sobre o Sistema de Gerenciamento de TGs, para que eu tenha total independência para tratar possíveis dificuldades.</td>
            <td>12</td>
            <td>4</td>
        </tr>
</table>

<div align="left"><hr width=70%></div>

<span id="entregas">

## ◻️ Sprints

| SPRINTS | PERÍODOS | DESCRIÇÃO |
|:-------:|:-----:|:---------:|
| [Sprint 1](https://github.com/iNineBD/SGTG-2Sem2023/wiki/Sprint-1) | 04/09/2023 à 24/09/2023 | Planejamento, modelagem e prototipação. |
| [Sprint 2](https://github.com/iNineBD/SGTG-2Sem2023/wiki/Sprint-2) | 25/09/2023 à 15/10/2023 | Upload, tratamento dos dados e controle de entregas. |
| [Sprint 3](https://github.com/iNineBD/SGTG-2Sem2023/wiki/Sprint-3) | 16/10/2023 à 05/11/2023 | Feedbacks e notas. |
| [Sprint 4](https://github.com/iNineBD/SGTG-2Sem2023/wiki/Sprint-4) | 06/11/2023 à 26/11/2023 | Relatórios e documentação |

<div align="left"><hr width=70%></div>

<span id="tecnologias">

## ◻️ Tecnologias

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)
![Scene Builder](https://img.shields.io/badge/scene_builder-orange.svg?style=for-the-badge&logo=&logoColor=orange)
![ClickUp](https://img.shields.io/badge/clickup-%237B68EE.svg?&style=for-the-badge&logo=clickup&logoColor=white)

<div align="left"><hr width=70%></div>



