var express = require('express')
  , http = require('http')
  , models = require('../models')
  , db = require('../db')
  , app = express()
  , recipe = require('../routes/recipe');

app.configure(function() {
    app.set('port', process.env.PORT || 3000);
    app.use(express.logger('dev'));
    app.use(express.json());
    app.use(express.urlencoded());
    app.use(express.methodOverride());
});

app.get('/recipes', recipe.all);

http.createServer(app).listen(app.get('port'), function() {
    console.log('Server is listening on port ' + app.get('port'));
});

module.exports = app;


