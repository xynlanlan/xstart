'use strict';

angular.module('app').config(['$locationProvider', '$routeProvider', '$translateProvider',
    function config($locationProvider, $routeProvider, $translateProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider
            .when('/', {
                templateUrl: '../view/login.html',
                controller: 'loginController'
            })
            .when('/welcome', {
                templateUrl: '../view/welcome.html',
                controller: 'loginSuccessController'
            })
            .when('/error', {templateUrl: '../view/error.html', controller: 'loginFailedController'})
            .otherwise('/');

        $translateProvider.useStaticFilesLoader({
            files: [{
                prefix: '../../i18n/',
                suffix: '.json'
            }]
        });
    }
]);