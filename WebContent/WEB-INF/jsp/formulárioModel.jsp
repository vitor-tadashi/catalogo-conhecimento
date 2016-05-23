<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>FormDen Example</title>
</head>
<body>

<!--formden.js communicates with FormDen server to validate fields and submit via AJAX -->
<script type="text/javascript" src="https://formden.com/static/cdn/formden.js"></script>

<!-- Special version of Bootstrap that is isolated to content wrapped in .bootstrap-iso -->
<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

<!-- Inline CSS based on choices in "Settings" tab -->
<style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{font-family: Arial, Helvetica, sans-serif; color: black}.bootstrap-iso form button, .bootstrap-iso form button:hover{color: #ffffff !important;} .asteriskField{color: red;}</style>

<!-- HTML Form (wrapped in a .bootstrap-iso div) -->
<div class="bootstrap-iso">
 <div class="container-fluid">
  <div class="row">
   <div class="col-md-6 col-sm-6 col-xs-12">
    <form action="" method="post">
     <div class="form-group ">
      <label class="control-label requiredField" for="name">
       Name
       <span class="asteriskField">
        *
       </span>
      </label>
      <input class="form-control" id="name" name="name" type="text"/>
     </div>
     <div class="form-group ">
      <label class="control-label requiredField" for="email">
       Email
       <span class="asteriskField">
        *
       </span>
      </label>
      <input class="form-control" id="email" name="email" type="text"/>
     </div>
     <div class="form-group ">
      <label class="control-label requiredField" for="subject">
       Subject
       <span class="asteriskField">
        *
       </span>
      </label>
      <input class="form-control" id="subject" name="subject" type="text"/>
     </div>
     <div class="form-group ">
      <label class="control-label " for="message">
       Message
      </label>
      <textarea class="form-control" cols="40" id="message" name="message" rows="10"></textarea>
     </div>
     <div class="form-group ">
      <label class="control-label " for="url">
       URL
      </label>
      <input class="form-control" id="url" name="url" type="text"/>
     </div>
     <div class="form-group ">
      <label class="control-label " for="subject1">
       Subject
      </label>
      <input class="form-control" id="subject1" name="subject1" type="text"/>
     </div>
     <div class="form-group ">
      <label class="control-label ">
       CheckBox
      </label>
      <div class=" ">
       <div class="checkbox">
        <label class="checkbox">
         <input name="checkbox" type="checkbox" value="Third Choice"/>
         Third Choice
        </label>
       </div>
       <div class="checkbox">
        <label class="checkbox">
         <input name="checkbox" type="checkbox" value="Second Choice"/>
         Second Choice
        </label>
       </div>
       <div class="checkbox">
        <label class="checkbox">
         <input name="checkbox" type="checkbox" value="First Choice"/>
         First Choice
        </label>
       </div>
      </div>
     </div>
     <div class="form-group ">
      <label class="control-label ">
       Select a Choice
      </label>
      <div class="">
       <div class="radio">
        <label class="radio">
         <input name="radio" type="radio" value="Third Choice"/>
         Third Choice
        </label>
       </div>
       <div class="radio">
        <label class="radio">
         <input name="radio" type="radio" value="Second Choice"/>
         Second Choice
        </label>
       </div>
       <div class="radio">
        <label class="radio">
         <input name="radio" type="radio" value="First Choice"/>
         First Choice
        </label>
       </div>
      </div>
     </div>
     <div class="form-group ">
      <label class="control-label " for="select">
       Select a Choice
      </label>
      <select class="select form-control" id="select" name="select">
       <option value="Third Choice">
        Third Choice
       </option>
       <option value="Second Choice">
        Second Choice
       </option>
       <option value="First Choice">
        First Choice
       </option>
      </select>
     </div>
     <div class="form-group ">
      <label class="control-label " for="date">
       Date
      </label>
      <input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>
     </div>
     <div class="form-group ">
      <label class="control-label " for="tel">
       Telephone #
      </label>
      <input class="form-control" id="tel" name="tel" type="text"/>
     </div>
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


<!-- Extra JavaScript/CSS added manually in "Settings" tab -->

</body>

</html>