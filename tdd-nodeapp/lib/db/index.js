var mongoose = require('mongoose')
  , config = require('../config')
  , connectionString = config.get('mongo:url')
  , options = {};

options = {
    server: {
        auto_reconnect: true,
        poolSize: 10
    }
};

mongoose.connect(connectionString, options, function(err, res){
    if(err) {
        console.log('Error connection to: ' + connectionString + '. ' + err);
    } else {
        console.log('Successfully connected to: ' + connectionString + '.');
    }
});