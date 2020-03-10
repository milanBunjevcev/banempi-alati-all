var baneApp = angular.module("baneApp", ["ngRoute"]);

angular.module('baneApp')
.directive('bsActiveLink', ['$location', function ($location) {
return {
    restrict: 'A', //use as attribute 
    replace: false,
    link: function (scope, elem) {
        //after the route has changed
        scope.$on("$routeChangeSuccess", function () {
            var hrefs = ['/#!' + $location.path(),
                         '#!' + $location.path(), //html5: false
                         $location.path()]; //html5: true
            angular.forEach(elem.find('a'), function (a) {
                a = angular.element(a);
                if (-1 !== hrefs.indexOf(a.attr('href'))) {
                    a.addClass('active');
                } else {
                    a.removeClass('active');   
                };
            });     
        });
    }
}
}]);

baneApp.controller("Ctrl", function ($scope) {

});

baneApp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: '/app/html/home.html',
			controller: "HomeCtrl"
		})
		.when('/workers', {
			templateUrl: '/app/html/workers/workers.html'
		})
		.when('/production', {
			templateUrl: '/app/html/production/production.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
