<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catálogo de Conhecimentos</title>
</head>

<body>

<!--formden.js communicates with FormDen server to validate fields and submit via AJAX -->
<script type="text/javascript" src="https://formden.com/static/cdn/formden.js"></script>

<!-- Special version of Bootstrap that is isolated to content wrapped in .bootstrap-iso -->
<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

<!-- Inline CSS based on choices in "Settings" tab -->
<style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{font-family: Arial, Helvetica, sans-serif; color: black}.bootstrap-iso form button, .bootstrap-iso form button:hover{color: #ffffff !important;} .asteriskField{color: red;}</style>

	<header>
		<h3>Alterar Catálogo de Conhecimentos</h3>
	</header>
	
<!-- HTML Form (wrapped in a .bootstrap-iso div) -->
<div class="bootstrap-iso">
 <div class="container-fluid">
  <div class="row">
   <div class="col-md-6 col-sm-6 col-xs-12">
    <form action="mvc?logica=AtualizarProjetoLogica" method="post">
     <div class="form-group ">
      <label class="control-label requiredField" for="name">
       Nome do Projeto
       <span class="asteriskField">
        *
       </span>
      </label>
      <input class="form-control" id="nome" name="nome" type="text" value="${projeto.nomeProjeto}"/>
     </div>
     <div class="form-group ">
      <label class="control-label " for="message">
       Observações:
      </label>
      <textarea class="form-control" cols="40" id="observacao" name="observacao" rows="10" value="${projeto.observacao}"></textarea>
     </div>
     
     <input	type="hidden" name="id" value="${projeto.idProjeto}" />
     
     <div class="form-group">
      <div>
       <input name="_honey" style="display:none" type="text"/>
       <button class="btn btn-primary " name="submit" type="submit">
        Submit
       </button>
      </div>
     </div>
    </form>
   </div>
  </div>
 </div>
</div>
</body>
</html>