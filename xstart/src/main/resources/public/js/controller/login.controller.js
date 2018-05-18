"use strict";

angular.module('app').controller('loginController', function ($scope, $location) {
    $scope.login = function () {
        var username = document.querySelector("#username").value;
        var password = document.querySelector("#password").value;
        var logData = {
            loginAccount: username,
            password: password
        };
        var logHeaders = new Headers();
        logHeaders.append("Content-Type", "application/json");
        var logRequest = new Request('login', {
            method: 'POST',
            body: JSON.stringify(logData),
            headers: logHeaders
        });

        fetch(logRequest).then(function (response) {
            return response.json();
        }).then(function (json) {
            if (json.status === 200) {
                $location.path('welcome').search({username: 'admin'});
            } else {
                $location.path('error');
            }
        });
    };
});




