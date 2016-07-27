'use strict';

describe('Controller: IntegrationcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var IntegrationcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    IntegrationcontrollerCtrl = $controller('IntegrationcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(IntegrationcontrollerCtrl.awesomeThings.length).toBe(3);
  });
});
