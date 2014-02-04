var mongoose = require('mongoose')
  , Schema = mongoose.Schema
  , RecipeSchema;

RecipeSchema = mongoose.Schema({
    title: {
        type: String,
        required: true
    },

    description: {
        type: String,
        required: true
    },

    readyIn: {
        type: String,
        required: true
    },

    course: {
        type: String
    },

    cuisine: {
        type: String
    },

    ingredients: [{
        name: {
            type: String,
            require: true
        },
        amount: {
            type: String,
            required: true
        }
    }]
});

mongoose.model('Recipe', RecipeSchema);
module.exports = mongoose;