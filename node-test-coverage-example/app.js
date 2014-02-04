#!/usr/bin/env node

var arithmetic = require('./lib/arithmetic')
  , program = require('commander');

program.version('v0.0.1');

program
    .command('add <a> <b>')
    .description('Add two numbers.')
    .action(function(a, b) {

        a = parseInt(a);
        b = parseInt(b);

        console.log(arithmetic.sum(a, b));
    });

program
    .command('sub <a> <b>')
    .description('Subtract two numbers.')
    .action(function(a, b) {
        a = parseInt(a);
        b = parseInt(b);

        console.log(arithmetic.diff(a, b));
    });

program
    .command('mult <a> <b>')
    .description('Multiply two numbers.')
    .action(function(a, b) {
        a = parseInt(a);
        b = parseInt(b);

        console.log(arithmetic.mult(a, b));
    });

program
    .command('div <a> <b>')
    .description('Divide two numbers.')
    .action(function(a, b) {
        a = parseInt(a);
        b = parseInt(b);

        console.log(arithmetic.div(a, b));
    });

program.parse(process.argv);

module.exports = program;