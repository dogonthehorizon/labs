var mongoose = require('mongoose')
  , RecipeModel = mongoose.model('Recipe');

function Recipe() {};

Recipe.prototype.all = function(callback){
    RecipeModel.find({}, function(err, recipes) {
        if (err) {
            return(err, null);
        }

        return callback(null, recipes);
    });
};

module.exports = Recipe;