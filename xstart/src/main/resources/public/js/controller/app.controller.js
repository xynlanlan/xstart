"use strict";

angular.module('app').controller('appController', function ($scope, $translate) {
    $scope.cur_lan = window.localStorage.lan || 'en';
    $translate.use($scope.cur_lan);
    $scope.switching = function (lan) {
        $translate.use(lan);
        window.localStorage.lan = lan;
    };
});




