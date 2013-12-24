/**
 * main.js
 *
 * The main definition file for our project.
 *
 * @author Fernando Freire
 * @since 09/23/13
 */

/**
 * config
 *
 * Define the configuration options for RequireJS.
 *
 */
require.config({
    baseUrl: 'public/js',
    paths: {
        'jquery':'vendor/jquery',
        'sammy': 'vendor/sammy',
        'knockout': 'vendor/knockout'
    }
});

/**
 * main
 *
 * This is the main entry point for our JS app. All global
 * dependencies should be defined here.
 *
 */
require([
    'jquery',
    'sammy',
    'knockout'
], function($, sammy, ko){

    // When the page has finished loading...
    $(document).ready(function(){

        //Initialize the routes for our JS app using Sammy...
        var app = sammy(function(){

            // Define the default route
            this.get('#/', function(){
                $('#content').text('Welcome!');
            });

            // Define a route for articles
            this.get('#/articles/:slug/:tags', function(){
                var content = $('#content');
                content.text(this.params['slug']);
                content.append('<br />');
                content.append(this.params['tags']);
            });
        });

        // And finally run the app!
        app.run('#/');
    });
});
