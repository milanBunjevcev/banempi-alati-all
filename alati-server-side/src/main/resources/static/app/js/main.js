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
                            scope.trenutnaStrana = a.text();
                        } else {
                            a.removeClass('active');
                        };
                        if ($location.path() == "/") {
                            scope.trenutnaStrana = "MENI";
                        }
                    });
                });
            }
        }
    }]);

baneApp.controller("Ctrl", function ($scope, $rootScope) {

    $rootScope.trenutnaStrana = ""; 
   
});

baneApp.config(['$routeProvider', function ($routeProvider, $scope) {
    $routeProvider
        .when('/', {
            templateUrl: '/app/html/home.html',
            controller: "HomeCtrl"
        })
        .when('/workers-list', {
            templateUrl: '/app/html/workers/workers-list.html'
        })
        .when('/production', {
            templateUrl: '/app/html/production/production.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);
