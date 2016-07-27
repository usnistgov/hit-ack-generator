'use strict';

describe('Service: matrixHandler', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var matrixHandler;
  beforeEach(inject(function (_matrixHandler_) {
    matrixHandler = _matrixHandler_;
  }));

  it('should do something', function () {
    expect(!!matrixHandler).toBe(true);
  });

});
