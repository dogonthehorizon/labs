/**
 * Provides simple integer arithmetic operators such as sum, difference, multiplication, and division.
 *
 * @class Arithmetic
 * @type {Object}
 */
var arithmetic = {

    /**
     * Given two numbers, return their sum.
     *
     * @param a {number} The first number to sum.
     * @param b {number} The second number to sum.
     * @returns {number} The sum of the input parameters.
     */
    sum: function(a, b) {
        return a + b;
    },

    /**
     * Given two numbers, return their difference.
     *
     * @param a {number} The first number to take the difference of.
     * @param b {number} The second number to take the difference of.
     * @returns {number} The difference of the input parameters.
     */
    diff: function(a, b) {
        return a - b;
    },

    /**
     * Given two numbers, return their product.
     *
     * @param a {number} The first number to multiply.
     * @param b {number} The second number to multiply.
     * @returns {number} The product of the input parameters.
     */
    mult: function(a, b) {
        return a * b;
    },

    /**
     * Given two numbers, return their quotient.
     *
     * @param a {number} The first number to divide.
     * @param b {number} The second number to divide.
     * @returns {number} The quotient of the input parameters.
     */
    div: function(a, b){
        return Math.floor(a / b);
    }
};

module.exports = arithmetic;