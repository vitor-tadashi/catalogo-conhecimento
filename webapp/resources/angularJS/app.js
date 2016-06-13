// app.js
// criar angular app
var validationApp = angular.module('validationApp', []);

// criar angular controller
validationApp.controller('mainController', function($scope) {

	$scope.regexString = "\([A-Za-z+/s])+{1,50}";
    $scope.regexNumber = "\d{2,10}";
    $scope.regexChar = "([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ+/s])+{3,10}";//Não aceita espaço
    $scope.regexEmail = "[A-Za-z_.0-9-]+@{1}[a-z]+([.]{1}[a-z]{2,4})+";
    $scope.regexPhone = "/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/";
    
});

						