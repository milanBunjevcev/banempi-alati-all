var baneApp = angular.module("baneApp");

baneApp.controller("WorkersCtrl", function ($scope, $http) {

	var urlWorkersApi = "/api/workers"

	$scope.workers = [];

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.rowsPerPage = "20";

	$scope.searchWorker = {};
	$scope.searchWorker.nameOrLastName = "";

	var getWorkers = function () {
		var config = { params: {} };
		if ($scope.searchWorker.nameOrLastName != "") {
			config.params.nameOrLastName = $scope.searchWorker.nameOrLastName;
		}
		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;
		$http.get(urlWorkersApi, config).then(
			function success(result) {
				$scope.workers = result.data;
				$scope.totalPages = result.headers("totalPages");
			},
			function error(result) {
				alert("neuspesno dobavljanje");
			}
		);
	};

	getWorkers();

	$scope.doPage = function (x) {
		$scope.pageNum += x;
		getWorkers();
	};

	$scope.doSearch = function (bool) {
		$scope.pageNum = 0;
		if (bool == false) {
			$scope.searchWorker.nameOrLastName = "";
		}
		getWorkers();
	};

	$scope.doDelete = function (id) {
		$http.delete(urlWorkersApi + "/" + id).then(
			function success(result) {
				getWorkers();
			},
			function error(result) {
				alert("neuspesno brisanje");
			}
		);
	};

});