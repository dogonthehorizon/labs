var nconf = require('nconf');

var Config = function() {
    var environment;

    nconf.argv().env('_');
    environment = nconf.get('NODE:ENV') || 'development';
    nconf.file(environment, 'config/' + environment + '.json');
    nconf.file('default', 'config/default.json');
};

Config.prototype.get = function(key) {
    return nconf.get(key);
};

module.exports = new Config();