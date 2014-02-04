var RecipeService = require('../recipe')
  , Recipe = new RecipeService();

exports.all = function(req, res) {
    Recipe.all(function(err, recipes){
        if(err) {
            return res.json(500, 'Internal Server Error');
        }

        if(recipes === null) {
            recipes = {};
        }

        return res.json(200, recipes);
    });
};