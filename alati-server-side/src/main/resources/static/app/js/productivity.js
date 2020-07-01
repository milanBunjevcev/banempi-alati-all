var baneApp = angular.module("baneApp");

baneApp.controller("ProductivityInputCtrl", function ($scope, $http, Excel, $timeout) {

	var urlProductions = "/api/productions"

	$scope.productions = [];

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.rowsPerPage = "20";

	$scope.datumUcinka1 = new Date();
	$scope.datumUcinka2 = $scope.datumUcinka1;

	$scope.period = false;

	$scope.hideInputForm = false;

	var getProductions = function () {
		var config = { params: {} };

		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;

		config.params.datumUcinka1 = $scope.datumUcinka1;
		config.params.datumUcinka2 = $scope.datumUcinka2;

		$http.get(urlProductions, config).then(
			function success(result) {
				$scope.productions = result.data;
				$scope.totalPages = result.headers("totalPages");
			},
			function error(result) {
				alert("neuspesno dobavljanje");
			}
		);
	};

	getProductions();

	$scope.doPage = function (x) {
		$scope.pageNum += x;
		getProductions();
	};

	$scope.doSearch = function () {
		$scope.pageNum = 0;

		if (!$scope.period) {
			$scope.datumUcinka2 = $scope.datumUcinka1;
		}
		getProductions();
	};	

	$scope.doDelete = function (id) {
		$http.delete(urlProductions + "/" + id).then(
			function success(result) {
				getProductions();
			},
			function error(result) {
				alert("neuspesno brisanje");
			}
		);
	};

	$scope.exportToExcel = function (tableId) { // ex: '#my-table'
		var exportHref = Excel.tableToExcel(tableId, 'WireWorkbenchDataExport');
		$timeout(function () { location.href = exportHref; }, 100); // trigger download
	}

});




baneApp.factory('Excel', function ($window) {
	var uri = 'data:application/vnd.ms-excel;base64,',
		template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
		base64 = function (s) { return $window.btoa(unescape(encodeURIComponent(s))); },
		format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) };
	return {
		tableToExcel: function (tableId, worksheetName) {
			var table = $(tableId),
				ctx = { worksheet: worksheetName, table: table.html() },
				href = uri + base64(format(template, ctx));
			return href;
		}
	};
});
