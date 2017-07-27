var app = angular.module('app', []);

app.controller('HypervisorController', ['$scope', '$http', function($scope, $http) {
  $scope.memoria = 512;
  $scope.nucleos = 2;
  $scope.armazenamento = 3;
  $scope.nome = "";

  $scope.criarMaquina = function() {

  	var obj = {
  		'nome': $scope.nome,
  		'ram': $scope.memoria,
  		'nucleos': $scope.nucleos,
  		'armazenamento': $scope.armazenamento
  	};

  	$http.post('http://localhost:8080/vm/create', obj).then(function(data) {
		alert('A máquina foi criada com sucesso!')
		$scope.memoria = 512;
		$scope.nucleos = 2;
		$scope.armazenamento = 3;
		$scope.nome = "";
	},
	function(data) {
		console.log(data)
	});
	

  }
}]);