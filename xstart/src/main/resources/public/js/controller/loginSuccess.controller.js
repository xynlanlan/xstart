'use strict';

angular.module('app').controller('loginSuccessController', function ($scope, $routeParams) {
    $scope.username = $routeParams.username;
});