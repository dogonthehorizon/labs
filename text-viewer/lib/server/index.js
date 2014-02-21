var express = require('express'),
    http = require('http'),
    path = require('path'),
    app = express(),
    routes = require('../routes');

app.configure(function() {
    app.set('port', process.env.PORT || 1337);
    app.set('views', 'resources/views');
    app.set('view engine', 'jade');
    app.use(express.favicon());
    app.use(express.urlencoded());
    app.use(express.logger('dev'));
    app.use(express.methodOverride());
    // Static assets like CSS/JS
    app.use(express.static('resources/public'));

    app.locals.pretty = true;
});

app.get('/', routes.index);

http.createServer(app).listen(app.get('port'), function() {
    console.log('Server is listening on port ' + app.get('port'));
});

module.exports = app;