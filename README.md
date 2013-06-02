## Trabalho das desciplinas LBD, FSI e IHC

1. [Introdução](#introducao)
2. [Requisitos para o desenvolvimento](#requisitos)
3. [Validação](#validacao)
4. [Utilize um banco de dados noSQL](#nosql)
5. [API REST (RESOURCES)](#rest)
6. [Integrantes](#integrantes)

<a name="introducao" />
### Introdução

Trabalho


<a name="requisitos" />
### Requisitos do desenvolvimento

* Banco de Dados Mysql
* Eclipse IDE
* Plugin Maven for eclipse

<a name="validacao" />
### Validações

#### Formulários
* Um formulário deve conter título e ao menos um campo
* Um campo deve ter, obrigatoriamente, label e tipo
* Quando o tipo do campo for radio, obrigatoriamente deve ser preenchido o atributo radio

#### Cadastros
* O cadastro gerado por um formulário deve ser validado de acordo com suas configurações.
  
	Um campo pode ter as seguintes configurações: requerido, tamanho máximo, date, datetime, email, number, etc...

<a name="nosql" />
### Utilize um banco de dados noSQL
Utilize um banco de dados noSQL baseado em documentos e grave tudo num único documento 

Exemplo de documento:
```
{
	title: "Título do formulário",
	fields: [{
		label: "Nome",
		type: "text",
		required: true,
		placeholder: "Nome:"
	},{
		label: "Sexo",
		type: "radio",
		required: true,
		radios: [{
			"label": "M",
			"value": "masculino"
		},{
			"label": "F",
			"value": "feminino"
		}],
		value: 1
	},{
		label: "E-mail",
		type: "email",
		required: false,
		placeholder: "Digite um e-mail"
	},{
		label: "idade",
		type: "number",
		required: false
	}],
	data: [
		{
			nome: "Guilherme",
			sexo: "masculino",
			email: "guilherme@email.com",
			idade: 27
		},
		{
			nome: "Rafael",
			sexo: "masculino"
		}
	]
}
```

<a name="rest" />
### API REST (RESOURCES)

#### Recursos para Template de Formulários
<table width="100%">
	<thead>
		<tr>
			<th width="40%">Recurso</th>
			<th width="60%">Descrição</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><a href="#get-templates">GET /templates<a/></td>
			<td>
				<i>Devolve uma lista de formulários</i>
			</td>
		</tr>

		<tr>
			<td><a href="#get-templates-id">GET /templates/:id</a></td>
			<td><i>Devolve um formulário com todos os campos</i></td>
		</tr>

		<tr>
			<td><a href="#post-templates">POST /templates</a></td>
			<td><i>Adiciona um formulário</i></td>
		</tr>

		<tr>
			<td><a href="#put-templates-id">PUT /templates/:id</a></td>
			<td><i>Atualiza um template</i></td>
		</tr>

		<tr>
			<td><a href="#delete-templates-id">DELETE /templates/:id</a></td>
			<td><i>Remove um template</i></td>
		</tr>
	</tbody>
</table>

#### Recursos para Dados do Formulário
<table width="100%">
	<thead>
		<tr>
			<th width="40%">Recursos</th>
			<th width="60%">Descrição</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><a href="#get-templates-id-data">GET /templates/:id/data/</a></td>
			<td><i>Devolve uma lista dos dados preenchidos em um formulário template</i></td>
		</tr>

		<tr>
			<td><a href="#post-templates-id-data">POST /templates/:id/data/</a></td>
			<td><i>Adiciona um dado num formulário</i></td>
		</tr>
	</tbody>
</table>


<a name="get-templates" />
#### GET /templates

_Devolve uma lista de formulários._

	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates

	Exemplo de requisição

	GET http://<SEU-DOMINIO>:8080/coletor/templates
	```
	[
		{
			"id": "1",
			"title": "Título do form",
			"fields": [
				{
					"label": "nome"
				},
				{
					"label": "email"
				},
				{
					"label": "telefone"
				}
			],
			"dataCount": "1000"
		},
		{
			"id": "2",
			"title": "Título do form",
			"fields": [
				{
					"label": "nome"
				},
				{
					"label": "email"
				},
				{
					"label": "telefone"
				}
			],
			"dataCount": "1000"
		}
	]
	```
<a name="get-templates-id" />
#### GET /templates/:id

_Devolve um formulário com todos os campos_

	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates/:id

	Exemplo de requisição

	GET http://<SEU-DOMINIO>:8080/coletor/templates/519a7d41c38f6b4eb02df70c
	```
	{
		title: "Formulário",
		fields: [{
			label: "nome",
			type: "text",
			required: true
		}]
	}
	```


<a name="post-templates" />
#### POST /templates

_Adiciona um formulário_

##### Parâmetros:

<table width="100%">
	<thead>
		<tr>
			<th width="40%">Atributos</th>
			<th width="60%">Descrição</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>
				title<br/>
				<i>requerido</i>
			</td>
			<td>
				<i>String</i><br/>
				<b>Ex value:</b> Formulário teste
			</td>
		</tr>

		<tr>
			<td>
				fields<br/>
				<i>requerido deve conter ao menos um field</i>
			</td>
			<td>
				<i>Array</i><br/>
			</td>
		</tr>

		<tr>
			<td>
				field.label<br/>
				<i>requerido</i>
			</td>
			<td>
				<i>String</i><br/>
				<b>Ex value:</b> Nome
			</td>
		</tr>

		<tr>
			<td>
				field.type<br/>
				<i>requerido</i>
			</td>
			<td>
				<i>String</i><br/>
				valores: color, date, datetime, datetime-local, email, month, number, tel, time, url, week, checkbox, radio
				<b>Ex value:</b> text
			</td>
		</tr>

		<tr>
			<td>
				field.required<br/>
				<i>opicional</i>
			</td>
			<td>
				<i>booleano</i><br/>
				se omitido por padrão é false.<br/>
				<b>Ex value:</b> true
			</td>
		</tr>

		<tr>
			<td>
				field.readOnly<br/>
				<i>opicional</i>
			</td>
			<td>
				<i>booleano</i><br/>
				se omitido por padrão é false, caso seja true anula o required.<br/>
				<b>Ex value:</b> true
			</td>
		</tr>

		<tr>
			<td>
				field.value<br/>
				<i>opicional</i>
			</td>
			<td>
				<i>string</i><br/>
				<b>Ex value:</b> Caio rolando da rocha
			</td>
		</tr>

		<tr>
			<td>
				field.maxLength<br/>
				<i>opicional</i>
			</td>
			<td>
				<i>number</i><br/>
				<b>Ex value:</b> 200
			</td>
		</tr>

		<tr>
			<td>
				field.placeholder<br/>
				<i>opicional</i>
			</td>
			<td>
				<i>string</i><br/>
				<b>Ex value:</b> Digite o nome:
			</td>
		</tr>

		<tr>
			<td>
				field.radio<br/>
				<i>opicional</i>
			</td>
			<td>
				<i>array</i><br/>
				requerido somente se o type for radio<br/>
				<b>Ex value:</b> <pre>[
    {
        "label": "M",
        "value": "masculino"
    },
    {
        "label": "F",
        "value": "feminino"
    }
]</pre>
			</td>
		</tr>
	</tbody>
</table>
	
	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates

	Exemplo de requisição

	POST http://<SEU-DOMINIO>:8080/coletor/templates

	Post Data
	```
	{
		title: "Nome do formulário",
		fields: [{
			label: "Sexo",
			type: "radio",
			required: true,
			readOnly: false,
			value: "masculino",
			radios: [
				{
					"label": "M", 
				"value": "masculino"
				}, {
					"label": "F", 
					"value": "feminino"
				}
			]
		}]
	}	
	```
	```
	{
		_id: "519a7d41c38f6b4eb02df70c"
		title: "Nome do formulário",
		fields: [{
			label: "Sexo",
			type: "radio",
			required: true,
			readOnly: false,
			value: "masculino",
			radios: [
				{
					"label": "M", 
				"value": "masculino"
				}, {
					"label": "F", 
					"value": "feminino"
				}
			]
		}]
	}
	```

<a name="put-templates-id" />
#### PUT /templates/:id

	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates/:id

	Atualizar um template de formulário

	Exemplo de requisição

	PUT http://<SEU-DOMINIO>:8080/coletor/templates/519a7d41c38f6b4eb02df70c

	Put Data
	```
	{
		_id: "519a7d41c38f6b4eb02df70c",
		title: "Nome do formulário alterado",
		fields: [{
			label: "Sexo",
			type: "radio",
			required: true,
			readOnly: false,
			value: "masculino",
			radios: [
				{
					"label": "M", 
				"value": "masculino"
				}, {
					"label": "F", 
					"value": "feminino"
				}
			]
		},{
			label: "Nome",
			type: "text",
			required: true
		}]
	}
	```
	```
	{
		_id: "519a7d41c38f6b4eb02df70c",
		title: "Nome do formulário alterado",
		fields: [{
			label: "Sexo",
			type: "radio",
			required: true,
			readOnly: false,
			value: "masculino",
			radios: [
				{
					"label": "M", 
				"value": "masculino"
				}, {
					"label": "F", 
					"value": "feminino"
				}
			]
		},{
			label: "Nome",
			type: "text",
			required: true
		}]
	}
	```

<a name="delete-templates-id" />
#### DELETE /templates/:id

	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates/:id

	Remove um template de formulário

	Exemplo de requisição

	DELETE http://<SEU-DOMINIO>:8080/coletor/templates/519a7d41c38f6b4eb02df70c

<a name="get-templates-id-data" />
#### GET /templates/:id/data

	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates/:id

	Exemplo de requisição

	PUT http://<SEU-DOMINIO>:8080/coletor/templates/519a7d41c38f6b4eb02df70c

	```
	{
		fields: [{label: "id"}, {label: "nome"}, {label: "telefone"}, {label: "email"}, {label: "apelido"}],
		data: [{
			id: "519bd0fde4b0a99234c5c6f2",
			nome: "nome1",
			telefone: "99999-999",
			email: "dlbca@gmail.com",
			apelido: "apelido1"
		},
		{
			id: "519bd0fde4b0a99234c5c53f",
			nome: "nome2",
			apelido: "apelido2",
			idade: 23
		}]
	}
	```

<a name="post-templates-id-data" />
#### POST /templates/:id/data

	Url do recurso

	http://<SEU-DOMINIO>:8080/coletor/templates/:id/data

	Exemplo de requisição

	POST http://<SEU-DOMINIO>:8080/coletor/templates/519a7d41c38f6b4eb02df70c/data

	Post Data
	```
	{
		nome: "",
		telefone: "",
		email: "",
		apelido: ""
	}
	```
<a name="build" />
### Compilar o client para utilizar em sua aplicação

* Pré-requisitos

	Nodejs

		npm install -g grunt-cli bower

* Build

	Execute os seguintes comandos:

		npm install && bower install

		grunt
