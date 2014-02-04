var blanket = require("blanket")({
    "pattern": "/lib/",
    "data-cover-never": "node_modules"
});

var arithmetic = require('../lib/arithmetic')
  , assert = require('chai').assert;

describe('Testing arithmetic library:', function() {
    var a, b;

    beforeEach(function(done){
        a = 9;
        b = 3;
        done();
    });

    describe('Adding two numbers', function() {
        it('should return the sum of the inputs.', function(done) {
            assert.equal(arithmetic.sum(a, b), a + b);
            done();
        });
    });

    describe('Subtracting two numbers', function(){
        it('should return the difference of the inputs.', function(done){
            assert.equal(arithmetic.diff(a, b), a - b);
            done();
        });
    });

    describe('Dividing two numbers', function() {
        it('should return the quotient of the inputs.', function(done) {
            assert.equal(arithmetic.div(a, b), a / b);
            done();
        });
    });

    describe('Multiplying two numbers', function() {
        it('should return the product of the inputs.', function(done) {
            assert.equal(arithmetic.mult(a, b), a * b);
            done();
        });
    });
});