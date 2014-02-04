var request = require('supertest')
    , assert = require('assert')
    , mongoose = require('mongoose')
    , app = require('../app');

describe('GET /recipes', function() {
    var id, recipe;

    recipe = {
        title: 'Pancakes',
        description: 'The best pancakes!',
        readyIn: '20min',
        method: 'To make the best pancakes do this...',
        ingredients: [
            { name: 'eggs', amount: '2' },
            { name: 'flour', amount: '100g' },
            { name: 'milk', amount: '300ml' }
        ]
    };

    beforeEach(function(done) {
        mongoose.connection.collections['recipes'].drop(function(err) {
            mongoose.connection.collections['recipes'].insert(recipe, function(err, docs) {
                id = docs[0]._id;
                done();
            });
        });
    });

    describe('when requesting resource /recipes', function() {
        it('should return an array of recipes', function(done) {
            request(app)
                .get('/recipes')
                .expect('Content-Type', /json/)
                .expect(200)
                .end(function(err, res) {
                    var result = JSON.parse(res.text)[0];
                    assert.equal(result._id, id);
                    assert.equal(recipe.title, result.title);
                    assert.equal(recipe.description, result.description);
                    assert.equal(recipe.readyIn, result.readyIn);
                    assert.equal(recipe.method, result.method);
                    assert.equal(recipe.ingredients.length, result.ingredients.length);
                    done();
                });
        });
    });
});