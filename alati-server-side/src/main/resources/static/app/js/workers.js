var baneApp = angular.module("baneApp");

baneApp.controller("WorkersListCtrl", function ($scope, $http, Excel, $timeout) {

	var urlWorkersApi = "/api/workers"

	$scope.workers = [];

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.rowsPerPage = "20";

	$scope.searchWorker = {};
	$scope.searchWorker.nameOrLastName = "";

	$scope.ugovori = [];
	$scope.newWorker = {};
	$scope.newWorker.name = "";
	$scope.newWorker.lastName = "";
	$scope.newWorker.contractType = "";

	$scope.prikaziSamoAktivne = true;

	var getContractTypes = function () {
		$http.get(urlWorkersApi + "/contracts").then(
			function success(result) {
				$scope.ugovori = result.data;
			},
			function error(result) {
				alert("neuspesno dobavljanje ugovora");
			}
		);
	}

	getContractTypes();

	var getWorkers = function () {
		var config = { params: {} };
		if ($scope.searchWorker.nameOrLastName != "") {
			config.params.nameOrLastName = $scope.searchWorker.nameOrLastName;
		}
		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;
		config.params.active = $scope.prikaziSamoAktivne;

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

	$scope.doSave = function () {
		$http.post(urlWorkersApi, $scope.newWorker).then(
			function success(result) {
				$scope.doResetNewWorkerFields();
				$scope.doSearch(true);
				alert("uspesno dodavanje");
				$('#dodavanjeRadnika').modal('hide');
			},
			function error(result) {
				alert("neuspesno dodavanje");
			}
		);
	}

	$scope.changeActive = function (id) {
		var editedWorker = {};

		$http.get(urlWorkersApi + "/" + id).then(
			function success(result) {
				editedWorker = result.data;

				editedWorker.active = !(editedWorker.active);

				$http.put(urlWorkersApi + "/" + id, editedWorker).then(
					function success(result) {
						$scope.doSearch(true);
					},
					function error(result) {
						alert("neuspesna izmena");
					});
			},
			function error(result) {
				alert("neuspesna izmena");
			}
		);
	}

	$scope.doResetNewWorkerFields = function () {
		$scope.newWorker.name = "";
		$scope.newWorker.lastName = "";
		$scope.newWorker.contractType = "";
	}

	$scope.exportToExcel = function (tableId) { // ex: '#my-table'
		var exportHref = Excel.tableToExcel(tableId, 'WireWorkbenchDataExport');
		$timeout(function () { location.href = exportHref; }, 100); // trigger download
	}

});

baneApp.controller("WorkersPresenceCtrl", function ($scope, $http, Excel, $timeout) {

	var urlWorkersApi = "/api/workers"
	var urlPresenceApi = "/api/workers/presence"

	$scope.datum = new Date();

	$scope.workers = [];

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.rowsPerPage = "20";

	$scope.searchWorker = {};
	$scope.searchWorker.nameOrLastName = "";

	$scope.prikaziSamoAktivne = true;

	var updatePresences = async function (i) {
		if (i < $scope.workers.length) {
			var worker = $scope.workers[i];

			worker.presence = {};
			worker.presence.hours = "";
			worker.presence.date = "";
			worker.presence.workerId = "";

			getPresence(worker).then(function () {
				console.log(worker);
				updatePresences(i + 1);
			});
		}
	};

	var getPresence = async function (worker) {
		var id = worker.id;
		var url = urlPresenceApi + "/" + $scope.datum.getFullYear() + "-" + ($scope.datum.getMonth() + 1) + "-" + $scope.datum.getDate();
		$http.get(url + "/" + id).then(
			function success(result) {
				worker.presence = result.data;
			},
			function error(result) {
				worker.presence.date = $scope.datum.getFullYear() + "-" + ($scope.datum.getMonth() + 1) + "-" + $scope.datum.getDate();
				worker.presence.workerId = worker.id;
			});
	};

	var getWorkers = function () {
		var config = { params: {} };
		if ($scope.searchWorker.nameOrLastName != "") {
			config.params.nameOrLastName = $scope.searchWorker.nameOrLastName;
		}
		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;
		config.params.active = $scope.prikaziSamoAktivne;

		$http.get(urlWorkersApi, config).then(
			function success(result) {
				$scope.workers = result.data;
				$scope.totalPages = result.headers("totalPages");
				updatePresences(0);
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

	$scope.doSave = function (workerIndex) {
		var forSave = $scope.workers[workerIndex].presence;
		$http.post(urlPresenceApi, forSave).then(
			function success(result) {
				getPresence($scope.workers[workerIndex]);
			},
			function error(result) {
				alert("neuspesno cuvanje");
			}
		);
	}

	$scope.doSave8 = function (workerIndex) {
		var forSave = $scope.workers[workerIndex].presence;
		forSave.hours = 8;
		$scope.doSave(workerIndex);
	}

	$scope.doDelete = function (workerIndex) {
		var forDelete = $scope.workers[workerIndex].presence.id;
		$http.delete(urlPresenceApi + "/" + forDelete).then(
			function success(result) {
				$scope.workers[workerIndex].presence = {};
				getPresence($scope.workers[workerIndex]);
			},
			function error(result) {
				alert("neuspesno brisanje");
			}
		);
	}

	$scope.exportToExcel = function (tableId) { // ex: '#my-table'
		var exportHref = Excel.tableToExcel(tableId, 'WireWorkbenchDataExport');
		$timeout(function () { location.href = exportHref; }, 100); // trigger download
	}

});

baneApp.controller("WorkersPresenceWeekCtrl", function ($scope, $http, Excel, $timeout) {

	$scope.prikaziTipUgovora = false;

	var urlWorkersApi = "/api/workers"
	var urlPresenceApi = "/api/workers/presence"

	$scope.datum1 = new Date();
	$scope.datum2 = new Date();
	$scope.datum2.setDate($scope.datum1.getDate() + 6);

	$scope.dani = [];

	$scope.workers = [];

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.rowsPerPage = "20";

	$scope.searchWorker = {};
	$scope.searchWorker.nameOrLastName = "";

	$scope.prikaziSamoAktivne = true;

	$scope.azurirajDatume = function () {
		var razlika = ($scope.datum2 - $scope.datum1) / 86400000;
		$scope.dani = [];
		for (i = 0; i <= razlika; i++) {
			var datum = new Date($scope.datum1.getFullYear(), $scope.datum1.getMonth(), $scope.datum1.getDate() + i);
			$scope.dani.push(datum);
		}
	}

	$scope.brojKolona = function () {
		var n = $scope.dani.length;
		n += 3;
		if ($scope.prikaziTipUgovora) {
			n += 1;
		}
		return n;
	}

	var updatePresences = async function (i) {
		if (i < $scope.workers.length) {
			var worker = $scope.workers[i];

			worker.presences = [];
			worker.sumHours = 0;

			updatePresencesHelper(worker, 0).then(function () {
				updatePresences(i + 1);
			});


		}
	};

	var updatePresencesHelper = async function (worker, j) {
		if (j < $scope.dani.length) {
			var datum = $scope.dani[j];
			getPresence(worker, datum).then(function () {
				updatePresencesHelper(worker, j + 1);
			});
		}
	}

	var getPresence = async function (worker, datum) {
		var id = worker.id;
		var url = urlPresenceApi + "/" + datum.getFullYear() + "-" + (datum.getMonth() + 1) + "-" + datum.getDate();
		$http.get(url + "/" + id).then(
			function success(result) {
				var presence = result.data;
				worker.presences.push(presence);
				worker.sumHours += presence.hours;
			},
			function error(result) {
				var presence = {};
				presence.hours = "";
				presence.date = datum.getFullYear() + "-" + (datum.getMonth() + 1) + "-" + datum.getDate();
				presence.workerId = worker.id;
				worker.presences.push(presence);
			});
	};

	var getWorkers = function () {
		$scope.azurirajDatume();
		var config = { params: {} };
		if ($scope.searchWorker.nameOrLastName != "") {
			config.params.nameOrLastName = $scope.searchWorker.nameOrLastName;
		}
		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;
		config.params.active = $scope.prikaziSamoAktivne;

		$http.get(urlWorkersApi, config).then(
			function success(result) {
				$scope.workers = result.data;
				$scope.totalPages = result.headers("totalPages");
				updatePresences(0);
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
