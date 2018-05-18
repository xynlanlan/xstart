'use strict';

angular.module('app').controller('loginFailedController', function ($scope, $interval, $location) {
    $scope.clock = 5;
    var updateClock = function () {
        var clock = $scope.clock;
        clock = clock - 1;
        if (clock === 0) {
            $location.path('login');
        }
        $scope.clock = clock;
    };
    $interval(function () {
        updateClock();
    }, 1000);
});